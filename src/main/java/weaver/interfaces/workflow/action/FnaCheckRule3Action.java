package weaver.interfaces.workflow.action;

import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 根据所属公司，借款支付公司是否相同，赋值是否是本公司字段，用来控制流程的走向(放在借款申请流程的申请节点之后)
 * @author Administrator
 *
 */
public class FnaCheckRule3Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		try {
			bb.writeLog("FnaCheckRule3Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			String tableCompany = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_company"));//主表字段,借款支付公司
			String tableSsgs = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_ssgs"));//主表字段,所属公司
			String tableSfbgs = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_sfbgs"));//主表字段,是否本公司
			String miantable = "formtable_main_"+formIdInt;
			String sql1 = "select a."+tableCompany+" tableCompany,"
							  + " a."+tableSsgs+" tableSsgs "
							  + " from "+miantable+" a "
							  + " where a.requestid ="+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaCheckRule3Action-001");
			}
			int tableCompany_val = 0;
			int tableSsgs_val = 0;
			while(rs.next()){
				tableCompany_val = Util.getIntValue(rs.getString("tableCompany"));
				tableSsgs_val = Util.getIntValue(rs.getString("tableSsgs"));
			}
			String update = "update "+miantable+" set "+tableSfbgs+" = ";
			if(tableCompany_val == tableSsgs_val){//所属公司和借款支付公司相同
				update += "0 where requestid = "+requestid;
			}else{
				update += "1 where requestid = "+requestid;
			}
			bb.writeLog("update>>>>", update);
			sqlFlag = rs.executeSql(update);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaCheckRule3Action-002");
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
