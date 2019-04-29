package weaver.interfaces.workflow.action;

import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * （勾选）暂估价：勾选，允许小写金额大于合同金额；否，不能大于合同金额
 * @author Administrator
 *
 */
public class FnaCheckRule1Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		try {
			bb.writeLog("FnaCheckRule1Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			String workflowid = request.getWorkflowid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			String tableFlag = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_flag"));//主表字段,暂估价
			String tableContractAmount = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_contractAmount"));//主表字段,合同总金额
			String tableAmount = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_amount"));//主表字段,小写金额
			String miantable = "formtable_main_"+formIdInt;
			String sql1 = "select a."+tableFlag+" as tableFlag,"
							  + " a."+tableContractAmount+" as tableContractAmount,"
						      + " a."+tableAmount+" as tableAmount "
							  + " from "+miantable+" a "
							  + " where a.requestid ="+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaCheckRule1Action-001");
			}
			int tableFlag_val = 0;
			double tableContractAmount_val = 0d;
			double tableAmount_val = 0d;
			while(rs.next()){
				tableFlag_val = Util.getIntValue(rs.getString("tableFlag"),0);
				tableContractAmount_val = Util.getDoubleValue(rs.getString("tableContractAmount"),0.00);
				tableAmount_val = Util.getDoubleValue(rs.getString("tableAmount"),0.00);
			}
			if(tableFlag_val == 1 && tableAmount_val > tableContractAmount_val){//勾选且小写金额大于合同金额
				throw new Exception("小写金额不得大于合同金额!");
			}
		} catch (Exception e) {
			bb.writeLog(e);
			request.getRequestManager().setMessageid("11111"+request.getRequestid()+"22222");
			request.getRequestManager().setMessagecontent(e.getMessage());
			return Action.FAILURE_AND_CONTINUE;
		}
		return Action.SUCCESS;
	}
}
