package weaver.interfaces.workflow.action;

import weaver.conn.RecordSet;
import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 2.8 同一个合同，不允许同时发起多条“补充合同”
 * @author Administrator
 *
 */
public class FnaCheckRule2Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		try {
			bb.writeLog("FnaCheckRule2Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			String workflowid = request.getWorkflowid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			RecordSet rs2 = new RecordSet();
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			String tableContractType = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_contractType"));//合同性质
			String tableContractId = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_contractId"));//合同id
			String miantable = "formtable_main_"+formIdInt;
			String sql = "select a."+tableContractType+" as tableContractType, "
								+ " a."+tableContractId+" as tableContractId, "
								+ " a.id "
								+ " from "+miantable+" a "
								+ " where a.requestid = "+requestid;
			boolean sqlFlag = rs.executeSql(sql);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaCheckRule2Action-001");
			}
			int tableContractType_val = 0;
			int tableContractId_val = 0;
			int id = 0;
			while(rs.next()){
				tableContractType_val = Util.getIntValue(rs.getString("tableContractType"));
				tableContractId_val = Util.getIntValue(rs.getString("tableContractId"));
				id = Util.getIntValue(rs.getString("id"));
			}
			StringBuffer errorMsg = new StringBuffer();
			bb.writeLog("tableContractType_val:", tableContractType_val);
			bb.writeLog("tableContractId_val:", tableContractId_val);
			if(tableContractType_val == 1){//如果是补充合同
				String sql2 = "select b.requestname from "+miantable+" a "
								+ "	join workflow_requestbase b on a.requestid = b.requestid"
								+ " where a."+tableContractId+"="+tableContractId_val
								+ " and a.id !="+id
								+ " and b.currentnodetype != 3";
				bb.writeLog("sql2:", sql2);
				sqlFlag = rs.executeSql(sql2);
				if(!sqlFlag){
					throw new Exception("sql出错，位置FnaCheckRule2Action-002");
				}
				String requestname = "";
				while(rs.next()){
					requestname = Util.null2String(rs.getString("requestname"));
					errorMsg.append("存在流程："+requestid+":"+requestname+"：已经发起此补充合同,不允许同时发起多条");
				}
			}
			if(errorMsg.length()>0){
				throw new Exception(errorMsg.toString());
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
