package greedc.actions;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import greedc.ws.client.eas.WSContext;
import org.apache.axis.client.Stub;
import weaver.file.Prop;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import ecustom.Log;
import ecustom.services.HrmResourceService;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import greedc.GreedcUtil;
import greedc.eas.vo.EASBillStatusVo;
import greedc.services.GreedcProExpBudgetService;
import greedc.ws.client.eas.WSUpdeteEASStatusFacadeSrvProxy;
import greedc.ws.client.eas.WSUpdeteEASStatusFacadeSrvProxyServiceLocator;

/**
 * 更新EAS单据状态。
 * @author William
 */
public class UpdeteEASStatusAction implements Action {
	
	private static final Log log = new Log(UpdeteEASStatusAction.class);
	private static final String EAS_UPDETE_STATUS_URL = Prop.getPropValue("eas", "eas.update.status.url");
	
	private static final String ACTION_SUBMIT = "submit";
	// private static final String ACTION_REJECT = "reject";
	
	private int requestId;
	private int workflowId;
	private String src;

	@Override
	public String execute(RequestInfo requestInfo) {
		log.info("enter Action, [requestId=" + requestInfo.getRequestid() + "]");
		this.requestId = Integer.parseInt(requestInfo.getRequestid());
		this.workflowId = Integer.parseInt(requestInfo.getWorkflowid());
		this.src = requestInfo.getRequestManager().getSrc();
		log.debug("src - " + this.src);
		
		boolean isSubmit = src.equalsIgnoreCase(ACTION_SUBMIT);
		if (!isSubmit) {
			// updateLcthzt(requestInfo);
		}

		try {
			WSContext wsContext = new GreedcUtil().wsLoginEAS();
			WSUpdeteEASStatusFacadeSrvProxy updeteEASStatusProxy = 
					new WSUpdeteEASStatusFacadeSrvProxyServiceLocator().getWSUpdeteEASStatusFacade(new URL(EAS_UPDETE_STATUS_URL));
			((Stub)(updeteEASStatusProxy)).setHeader("http://login.webservice.bos.kingdee.com","SessionId", wsContext.getSessionId());

			String easResult = updeteEASStatusProxy.updateEasBill(getInputParam(requestInfo));
			log.info("easResult = " + easResult);
			
			Map<String, Object> result = GsonUtil.toMap(easResult);
			if (!result.get("status").toString().equals("0")) {
				throw new Exception(result.get("msg").toString());
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
		return Action.SUCCESS;
	}

	private String getInputParam(RequestInfo requestInfo) {
		String easCodeFN = Prop.getPropValue("ecustom", "flow" + this.workflowId + ".form.eascode.field");
		String easCodeFV = null;
		Property[] fields = requestInfo.getMainTableInfo().getProperty();
		for (Property field : fields) {
			if (easCodeFN.equalsIgnoreCase(field.getName())) {
				easCodeFV = field.getValue();
			}
		}
		
		// 项目付款计划会签单
		if (this.workflowId == 221) {
			return getInputParam221(requestInfo, easCodeFV);
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", Prop.getPropValue("ecustom", "flow" + this.workflowId + ".action.eas.fn"));
		params.put("number", easCodeFV);
		params.put("status", src.equalsIgnoreCase(ACTION_SUBMIT) ? "1" : "0");	// 0 拒绝，1 通过
		params.put("auditNumber", getAuditNumber(requestInfo.getLastoperator()));
		String jsonStr = GsonUtil.toJSONString(params);
		log.debug("jsonStr = " + jsonStr);
		return jsonStr;
	}

	/**
	 * 项目付款计划会签单。
	 * @param requestInfo
	 * @return
	 */
	private String getInputParam221(RequestInfo requestInfo, String easCodeFV) {
		GreedcProExpBudgetService service = new GreedcProExpBudgetService();
		List<Map<String, String>> rows = service.listGoux(requestId);
		Map<String, List<Map<String, String>>> numsMap = new HashMap<String, List<Map<String, String>>>();
		for (Map<String, String> row : rows) {
			String projectId = row.get("PROJECT_ID");
			if (!numsMap.containsKey(projectId)) {
				numsMap.put(projectId, new ArrayList<Map<String, String>>());
			}
			Map<String, String> detailRow = new HashMap<String, String>();
			detailRow.put("num", row.get("HETBM"));	// 合同编码
			detailRow.put("state", row.get("GOUX"));	// 状态
			numsMap.get(projectId).add(detailRow);
		}

		log.debug("rows: " + GsonUtil.toJSONString(rows));
		
		Set<String> projectIds = numsMap.keySet();
		List<EASBillStatusVo> statusVos = new ArrayList<EASBillStatusVo>();
		for (String projectId : projectIds) {
			EASBillStatusVo statusVo = new EASBillStatusVo();
			statusVo.setProject(projectId);
			statusVo.setDetail(numsMap.get(projectId));
			statusVos.add(statusVo);
		}
		
		log.debug("statusVos: " + GsonUtil.toJSONString(statusVos));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", Prop.getPropValue("ecustom", "flow" + this.workflowId + ".action.eas.fn"));
		params.put("number", easCodeFV);
		params.put("status", src.equalsIgnoreCase(ACTION_SUBMIT) ? "1" : "0");	// 0 拒绝，1 通过
		params.put("auditNumber", getAuditNumber(requestInfo.getLastoperator()));
		params.put("entry", statusVos);
		String jsonStr = GsonUtil.toJSONString(params);
		log.debug("jsonStr = " + jsonStr);
		return jsonStr;
	}
	
	/**
	 * 获取最后操作者工号。
	 * @param lastoperator
	 * @return
	 */
	private String getAuditNumber(String lastoperator) {
		HrmResourceService resService = new HrmResourceService();
		String code = resService.getWorkCodeById(CustomUtil.getInt(lastoperator));
		if (CustomUtil.isNotBlank(code) && code.contains("_")) {
			code = code.split("_")[0];
		}
		return code;
	}
}
