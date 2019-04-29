package greedc.actions;

import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.workflow.entities.WorkflowRequestBase;

public class BP09PlanFinishAction implements Action{

	@Override
	public String execute(RequestInfo requestInfo) {
		int requestId = requestInfo.getRequestManager().getRequestid();
		String src = requestInfo.getRequestManager().getSrc();
		WorkflowRequestBase requestBase = RecordUtil.findById(WorkflowRequestBase.class, requestId);
		
		String auditDate = requestBase.getLastOperateDate();
		Integer auditName = requestInfo.getRequestManager().getUserId();
		// 已审核 3/ 未通过4
		RecordUtil.executeUpdate("UPDATE UF_BPCOMPANYPLAN SET STATUS=?, AUDITDATE=?, AUDITNAME=? WHERE REQUESTID=?",
				"submit".equals(src) ? 3 : 4, auditDate, auditName, requestId);
		
		return Action.SUCCESS;
	}

}
