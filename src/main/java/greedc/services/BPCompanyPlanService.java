package greedc.services;

import ecustom.ecology8.commons.RecordUtil;
import greedc.budgetplan.entities.UFBPCompanyPlan;

public class BPCompanyPlanService {

	public void cancelAudit(int billId) throws Exception {
		UFBPCompanyPlan companyPlan = RecordUtil.findById(UFBPCompanyPlan.class, billId);
		
		if (companyPlan  == null) {
			throw new RuntimeException("填报数据不存在，UF_BPCOMPANYPLAN.ID = " + billId);
		}
		
		companyPlan.setStatus(0);	// 设置为未提交
		companyPlan.setRequestId(null);	// RequestId置空
		companyPlan.setAuditDate(null);	// 审核日期置空
		companyPlan.setAuditName(null);	// 审核人置空
		
		RecordUtil.updateExact(companyPlan, billId, "STATUS,REQUESTID,AUDITDATE,AUDITNAME");
	}
}
