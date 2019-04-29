package greedc.actions;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import ecustom.Log;
import ecustom.entities.HrmDepartment;
import ecustom.entities.HrmJobTitle;
import ecustom.entities.HrmSubCompany;
import ecustom.services.HrmDepartmentService;
import ecustom.services.HrmJobTitleService;
import ecustom.services.HrmResourceService;
import ecustom.services.HrmSubCompanyService;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import greedc.GreedcUtil;
import greedc.eas.vo.EASResignItemVo;
import greedc.ws.client.eas.WSContext;
import greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxy;
import greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxyServiceLocator;
import org.apache.axis.client.Stub;
import weaver.file.Prop;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;

/**
 * 生成EAS离职单Action
 * @author William
 */
public class GenerateEASResignAction implements Action {

	private Log log = new Log(GenerateEASResignAction.class);
	private static final String EAS_GENERATE_DATA_URL = Prop.getPropValue("eas", "eas.generate.data.url");;
	
	private int requestId;
	
	private Map<String, String> cache;
	
	@Override
	public String execute(RequestInfo requestInfo) {
		log.info("enter Action, [requestId=" + requestInfo.getRequestid() + "]");
		this.requestId = Integer.parseInt(requestInfo.getRequestid());
		this.cache = new HashMap<String, String>();
		
		try {
			WSContext wsContext = new GreedcUtil().wsLoginEAS();
			
			WSGenerateEASDateFacadeSrvProxy generateProxy = new WSGenerateEASDateFacadeSrvProxyServiceLocator()
					.getWSGenerateEASDateFacade(new URL(EAS_GENERATE_DATA_URL));
			((Stub)(generateProxy)).setHeader("http://login.webservice.bos.kingdee.com","SessionId", wsContext.getSessionId());

			String easResult = generateProxy.generateResign(getInputParam(requestInfo));
			log.info("easResult = " + easResult);
			Map result = (Map)GsonUtil.toMapList(easResult).get(0);
			if (!result.get("state").toString().equals("0")) {
				throw new Exception(result.get("msg").toString());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			requestInfo.getRequestManager().setMessageid("Action Error: 服务创建失败");
			requestInfo.getRequestManager().setMessagecontent(e.toString());
			log.error("服务创建失败[requestId=" + requestId + "]");
			return Action.FAILURE_AND_CONTINUE;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			requestInfo.getRequestManager().setMessageid("Action Error: URL地址解析失败");
			requestInfo.getRequestManager().setMessagecontent(e.toString());
			log.error("URL地址解析失败[requestId=" + requestId + "]");
			return Action.FAILURE_AND_CONTINUE;
		} catch (RemoteException e) {
			e.printStackTrace();
			requestInfo.getRequestManager().setMessageid("Action Error: 远程调用失败");
			requestInfo.getRequestManager().setMessagecontent(e.toString());
			log.error("远程调用失败[requestId=" + requestId + "]");
			return Action.FAILURE_AND_CONTINUE;
		} catch (Exception e) {
			e.printStackTrace();
			requestInfo.getRequestManager().setMessageid("Action Error: 调用失败");
			requestInfo.getRequestManager().setMessagecontent(e.toString());
			return Action.FAILURE_AND_CONTINUE;
		}
		
		log.info("leave Action, [requestId=" + requestId + "]");
		return SUCCESS;
	}

	private String getInputParam(RequestInfo requestInfo) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(data);

//		data.put("proposer", "001916");	// 员工编码
//		data.put("hrOrgUnit", "01");	// HR组织编码
//		data.put("adminOrg", "01");	// 行政单位编号
//		data.put("applyDate", "2016-11-12");	// 申请时间
		
		Property[] mainFields = requestInfo.getMainTableInfo().getProperty();
		for(Property field : mainFields) {
			String name = field.getName();
			String value = field.getValue();
			//员工编码
			if("shenqr".equalsIgnoreCase(name)) {
				data.put("proposer", getEmployeeCode(CustomUtil.getInt(value)));
			} else if("suosgs".equalsIgnoreCase(name)) {
				data.put("hrOrgUnit", getCompanyCode(CustomUtil.getInt(value)));
				data.put("adminOrg", getCompanyCode(CustomUtil.getInt(value)));
			} else if("baogrq".equalsIgnoreCase(name)) {
				data.put("applyDate", value);
			} 
			// 表单编号
			else if("biaodbh".equalsIgnoreCase(name)) {
				data.put("number", value);	// 审批编号
			}
		}
		
		data.put("approveState", "0");	// 审批方式编号
		data.put("billstate", "3");	// EAS单据状态
		data.put("entries", getEntries(requestInfo.getDetailTableInfo().getDetailTable()[0]));
		String jsonStr = GsonUtil.toJSONString(list);
		
		log.debug("jsonStr = " + jsonStr);
		return jsonStr;
	}

	private List<EASResignItemVo> getEntries(DetailTable detailTable) {
		List<EASResignItemVo> list = new ArrayList<EASResignItemVo>();
		for(int i = 0; i < detailTable.getRowCount(); i++) {
			EASResignItemVo vo = new EASResignItemVo();
			vo.setReasonType("sys0000");
			Row row = detailTable.getRow(i);
			for(int j = 0; j < row.getCellCount(); j++) {
				Cell cell = row.getCell(j);
				String name = cell.getName();
				String value = cell.getValue();
				if("xingm".equalsIgnoreCase(name)) {
					vo.setName(name);
					vo.setPerson(getEmployeeCode(CustomUtil.getInt(value)));
				} else if("yuangzt".equalsIgnoreCase(name)) {
					String propName = "flow97.form.yuangzt." + value + ".eascode";
					value = Prop.getPropValue("ecustom", propName);
					if (CustomUtil.isBlank(value)) {
						throw new RuntimeException("员工状态未配置 ecustom:" + propName);
					}
					vo.setOldemptype(value);
				} else if("mubygzt".equalsIgnoreCase(name)) {
					String propName = "flow97.form.mubygzt." + value + ".eascode";
					value = Prop.getPropValue("ecustom", propName);
					if (CustomUtil.isBlank(value)) {
						throw new RuntimeException("目标员工状态未配置 ecustom:" + propName);
					}
					vo.setEmptype(value);
				} else if("suosxzz".equalsIgnoreCase(name)) {
					vo.setOldAdminOrg(getDepartmentCode(CustomUtil.getInt(value)));
					vo.setEnAdminOrg(vo.getOldAdminOrg());
				} else if("zhiw".equalsIgnoreCase(name)) {
					vo.setPosition(getJobTitleCode(CustomUtil.getInt(value)));
				} else if("lizyy".equalsIgnoreCase(name)) {
					String propName = "flow97.form.lizyy." + value + ".eascode";
					value = Prop.getPropValue("ecustom", propName);
					if (CustomUtil.isBlank(value)) {
						throw new RuntimeException("离职原因未配置 ecustom:" + propName);
					}
					vo.setReason(value);
				} else if("shengxrq".equalsIgnoreCase(name)) {
					vo.setBizDate(value);
				} else if("suggest".equalsIgnoreCase(name)) {
					vo.setSuggest(value);
				}
			}
			list.add(vo);
		}
		
		return list;
	}
	
	/**
	 * 根据公司id获取公司编码。
	 * @param id
	 * @return
	 */
	private String getCompanyCode(int id) {
		if(cache.containsKey("compid-code:" + id)) {
			return cache.get("compid-code:" + id);
		}
		
		HrmSubCompanyService service = new HrmSubCompanyService();
		HrmSubCompany entity = service.getById(id);
		String code = null;
		if(entity != null) {
			code = entity.getSubCompanyCode();
		}
		cache.put("compid-code:" + id, code);
		return code;
	}
	
	/**
	 * 根据部门id获取部门编码。
	 * @param id
	 * @return
	 */
	private String getDepartmentCode(int id) {
		if(cache.containsKey("deptid-code:" + id)) {
			return cache.get("deptid-code:" + id);
		}
		
		HrmDepartmentService service = new HrmDepartmentService();
		HrmDepartment entity = service.getById(id);
		String code = null;
		if(entity != null) {
			code = entity.getDepartmentCode();
		}
		cache.put("deptid-code:" + id, code);
		return code;
	}
	
	/**
	 * 根据岗位ID获取岗位编码。
	 * @param id
	 * @return
	 */
	private String getJobTitleCode(int id) {
		if(cache.containsKey("jobid-code:" + id)) {
			return cache.get("jobid-code:" + id);
		}
		HrmJobTitleService service = new HrmJobTitleService();
		HrmJobTitle entity = service.getById(id);
		String code = null;
		if(entity != null) {
			code = entity.getJobTitleCode();
		}
		if (CustomUtil.isBlank(code)) {
			code = "01.00.04.097";	// 如果找不到岗位编码，则默认一个编码，确保可以通过接口的必填验证，客户要求的。
		}
		cache.put("jobid-code:" + id, code);
		return code;
	}
	
	/**
	 * 根据人员ID获取人员编码。
	 * @param id
	 * @return
	 */
	private String getEmployeeCode(int id) {
		if(cache.containsKey("empid-code:" + id)) {
			return cache.get("empid-code:" + id);
		}
		HrmResourceService service = new HrmResourceService();
		String code = service.getWorkCodeById(id);
		cache.put("empid-code:" + id, code);
		return code;
	}
}
