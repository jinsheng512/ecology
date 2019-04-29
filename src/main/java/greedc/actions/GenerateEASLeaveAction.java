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
import ecustom.entities.HrmSubCompany;
import ecustom.services.HrmResourceService;
import ecustom.services.HrmSubCompanyService;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import greedc.GreedcUtil;
import greedc.eas.vo.EASLeaveItemVo;
import greedc.ws.client.eas.WSContext;
import greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxy;
import greedc.ws.client.eas.WSGenerateEASDateFacadeSrvProxyServiceLocator;
import org.apache.axis.client.Stub;
import weaver.file.Prop;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 生成EAS请假单Action
 * @author William
 */
public class GenerateEASLeaveAction implements Action {

	private static BaseBean baseBean=new BaseBean();
	private static final Log log = new Log(GenerateEASLeaveAction.class);
	private static final String EAS_GENERATE_DATA_URL = Prop.getPropValue("eas", "eas.generate.data.url");
	private Map<String, String> cache;
	
	private int requestId;
	
	@Override
	public String execute(RequestInfo requestInfo) {
		log.info("enter Action, [requestId=" + requestInfo.getRequestid() + "]");
		baseBean.writeLog("enter Action, [requestId=" + requestInfo.getRequestid() + "]");
		this.requestId = Integer.parseInt(requestInfo.getRequestid());
		this.cache = new HashMap<String, String>();
		
		try {
			WSContext wsContext = new GreedcUtil().wsLoginEAS();
			
			// 在EAS系统中生成请假单
			WSGenerateEASDateFacadeSrvProxy generateProxy = new WSGenerateEASDateFacadeSrvProxyServiceLocator()
					.getWSGenerateEASDateFacade(new URL(EAS_GENERATE_DATA_URL));

			((Stub)(generateProxy)).setHeader("http://login.webservice.bos.kingdee.com","SessionId", wsContext.getSessionId());

			// 获取接口输入参数
			String input = getInputParam(requestInfo);
			String easResult = generateProxy.generateLeave(input);
			
			// 处理EAS返回结果
			baseBean.writeLog("input = " + input);
			baseBean.writeLog("easResult = " + easResult);
			log.info("easResult = " + easResult);
			Map result = (Map)GsonUtil.toMapList(easResult).get(0);
			// 如果状态不等于0，则处理失败
			if (!result.get("state").toString().equals("0")) {
				throw new Exception("EAS Result - " + result.get("msg").toString());
			}
			
		} catch (ServiceException e) {
			requestInfo.getRequestManager().setMessageid("Action Error");
			requestInfo.getRequestManager().setMessagecontent("ServiceException - " + e.getMessage());
			log.error("服务创建失败[requestId=" + requestId + "]");
			e.printStackTrace();
			return Action.FAILURE_AND_CONTINUE;
		} catch (MalformedURLException e) {
			requestInfo.getRequestManager().setMessageid("Action Error");
			requestInfo.getRequestManager().setMessagecontent("MalformedURLException - " + e.getMessage());
			log.error("URL地址解析失败[requestId=" + requestId + "]");
			e.printStackTrace();
			return Action.FAILURE_AND_CONTINUE;
		} catch (RemoteException e) {
			requestInfo.getRequestManager().setMessageid("Action Error");
			requestInfo.getRequestManager().setMessagecontent("RemoteException - " + e.getMessage());
			log.error("远程调用失败[requestId=" + requestId + "]");
			e.printStackTrace();
			return Action.FAILURE_AND_CONTINUE;
		} catch (Exception e) {
			requestInfo.getRequestManager().setMessageid("Action Error");
			requestInfo.getRequestManager().setMessagecontent("Exception - " + e.getMessage());
			log.error("接口调用异常[" + requestId + "] " + e.toString());
			e.printStackTrace();
			return Action.FAILURE_AND_CONTINUE;
		}
		
		log.info("leave Action, [requestId=" + requestId + "]");
		return SUCCESS;
	}

	/**
	 * 获取接口输入参数。
	 * @param requestInfo
	 * @return
	 */
	private String getInputParam(RequestInfo requestInfo) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(data);

		Property[] mainFields = requestInfo.getMainTableInfo().getProperty();
		for (Property field : mainFields) {
			String name = field.getName();
			String value = field.getValue();
			// 行政单位编号
			if ("suosgs".equalsIgnoreCase(name)) {
				data.put("adminOrg", getCompanyCode(CustomUtil.getInt(value)));
			}
			// 申请时间
			else if ("baogrq".equalsIgnoreCase(name)) {
				data.put("applyDate", value);
			}
			// 员工编码
			else if ("shenqr".equalsIgnoreCase(name)) {
				data.put("proposer", getEmployeeCode(CustomUtil.getInt(value)));
			}
			// 电话
			else if ("lianxifs".equalsIgnoreCase(name)) {
				data.put("phone", value);
			}
			// 预留字段
			else if ("beiz".equalsIgnoreCase(name)) {
				data.put("remark", value);	
			}
			// 	表单编号
			else if ("biaodbh".equalsIgnoreCase(name)) {
				data.put("number", value);	// 审批编号
			}
		}
		data.put("approveState", "1");	// 审批方式编号
		data.put("billstate", "3");	// EAS单据状态
		
		data.put("entries", getEntries(mainFields));
		String jsonStr = GsonUtil.toJSONString(list);
		log.debug("jsonStr = " + jsonStr);
		
		return jsonStr;
	}

	/**
	 * 获取请假明细信息。
	 * @param mainFields	表数据
	 * @return
	 */
	private List<EASLeaveItemVo> getEntries(Property[] mainFields) {
		List<EASLeaveItemVo> list = new ArrayList<EASLeaveItemVo>();
		EASLeaveItemVo vo = new EASLeaveItemVo();
		String startDate = null, startTime = null;
		String endDate = null, endTime = null;
		for (Property field : mainFields) {
			String name = field.getName();
			String value = field.getValue();
			// 员工编号
			if ("shenqr".equalsIgnoreCase(name)) {
				vo.setPerson(getEmployeeCode(CustomUtil.getInt(value)));
			}
			// 行政组织编码
			else if ("suosgs".equalsIgnoreCase(name)) {
				vo.setOrg(getCompanyCode(CustomUtil.getInt(value)));
			}
			// 请假类型编码
			else if ("qingjlb".equalsIgnoreCase(name)) {
				if (CustomUtil.isBlank(value)) {
					throw new RuntimeException("请假类别不能为空");
				}
				String propName = "flow96.form.qingjlb." + value + ".eascode";
				value = Prop.getPropValue("ecustom", propName);
				if (CustomUtil.isBlank(value)) {
					throw new RuntimeException("请假类别未配置 ecustom:" + propName);
				}
				vo.setType(value);
			}
			// 请假原因
			else if ("qingjyy".equalsIgnoreCase(name)) {
				value = value.replaceAll("&nbsp;", "");
				vo.setReason(value);
			}
			// 请假时间长度
			else if ("qingjsjcd".equalsIgnoreCase(name)) {
				if (CustomUtil.isBlank(value)) {
					throw new RuntimeException("请假时长不能为空");
				}
				vo.setLeaveLength(new Double(value));
			}
			// 请假开始日期
			else if ("qingjksrq".equalsIgnoreCase(name)) {
				startDate = value;
			}
			// 请假开始时间
			else if ("qingjkssj".equalsIgnoreCase(name)) {
				startTime = value;
			}
			// 请假结束日期
			else if ("qingjjsrq".equalsIgnoreCase(name)) {
				endDate = value;
			}
			// 请假结束时间
			else if ("qingjjssj".equalsIgnoreCase(name)) {
				endTime = value;
			}
		}
		vo.setBeginTime(startDate + " " + startTime);	// 开始时间
		vo.setEndTime(endDate + " " + endTime);	// 结束时间
		list.add(vo);
		
		return list;
	}
	
	/**
	 * 根据公司id获取公司编码。
	 * @param id
	 * @return
	 */
	private String getCompanyCode(int id) {
		if (cache.containsKey("deptid-code:" + id)) {
			return cache.get("deptid-code:" + id);
		}
		HrmSubCompanyService service = new HrmSubCompanyService();
		HrmSubCompany entity = service.getById(id);
		String code = null;
		if (entity != null) {
			code = entity.getSubCompanyCode();
		}
		cache.put("deptid-code:" + id, code);
		return entity.getSubCompanyCode();
	}
	
	/**
	 * 根据人员ID获取人员编码。
	 * @param id
	 * @return
	 */
	private String getEmployeeCode(int id) {
		if (cache.containsKey("empid-code:" + id)) {
			return cache.get("empid-code:" + id);
		}
		HrmResourceService service = new HrmResourceService();
		String code = service.getWorkCodeById(id);
		cache.put("empid-code:" + id, code);
		return code;
	}
}
