package weaver.interfaces.workflow.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import weaver.fna.eas.EasOrmrpcHandle;
import weaver.fna.eas.FnaEasHandler;
import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 内部推荐奖励金发放申请
 * @author Administrator
 *
 */
public class FnaEas7Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		FnaEasHandler fnaEasHandler = new FnaEasHandler();
		DecimalFormat df = new DecimalFormat("#####################0.00");
		try {
			bb.writeLog("FnaEas7Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			String workflowid = request.getWorkflowid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			String tableNumber = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_number"));//主表字段,表单编号
			String tableAmount = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_amount"));//明细表,推荐奖金
			String tableCompany = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_company"));//主表字段,所属公司
			String tableName = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_name"));//明细表,被推荐人姓名
			String miantable = "formtable_main_"+formIdInt;
			String detailTable = miantable+"_dt2";
			String sql1 = "select a."+tableNumber+" as tableNumber,"
							  + " b."+tableAmount+" as tableAmount,"
							  + " a."+tableCompany+" as tableCompany,"
							  + " b."+tableName+" as tableName "
							  + " from "+miantable+" a "
							  + " join "+detailTable+" b on a.id = b.mainid "
							  + " where a.requestid ="+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaEas7Action-001");
			}
			String billDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String tableNumber_val = "";
			double tableAmount_val = 0d;
			int tableCompany_val = 0;
			int tableName_val = 0;
			double total = 0d;
			while(rs.next()){
				tableNumber_val = Util.null2String(rs.getString("tableNumber"));
				tableAmount_val = Util.getDoubleValue(rs.getString("tableAmount"),0.00);
				tableCompany_val = Util.getIntValue(rs.getString("tableCompany"));
				tableName_val = Util.getIntValue(rs.getString("tableName"));
				total += tableAmount_val;
			}
			//获取所属公司编码(即分部编码)
			String subcompanycode = fnaEasHandler.getSubcompanycode(tableCompany_val)[1];
			//获取被推荐人姓名编码,姓名
			String workcode = fnaEasHandler.getWorkcode(tableName_val)[0];
			String lastname = fnaEasHandler.getWorkcode(tableName_val)[1];
			
			Map<String,Object> map1 = new LinkedHashMap<String,Object>();
			map1.put("number", tableNumber_val);
			map1.put("actPayAmt", df.format(total));
			map1.put("company", subcompanycode);
			map1.put("payeeNum", workcode);
			map1.put("payeeName", lastname);
			map1.put("payeeBank","");
			map1.put("payeeBankAcct","");
			map1.put("billDate", billDate);
			map1.put("currency", "BB01");
			map1.put("payBillType", "999");
			map1.put("description", "付"+lastname+"内部推荐奖励金");
			
			Map<String,Object> map2 = new LinkedHashMap<String,Object>();
			map2.put("costType", "");
			map2.put("remark", "");
			map2.put("amount", df.format(tableAmount_val));
			map2.put("localAmt", df.format(tableAmount_val));
			map2.put("rebateLocAmt", 0);
			map2.put("actualAmt", df.format(tableAmount_val));
			
			String main = fnaEasHandler.changeToJson(map1, 1);
			String entries = fnaEasHandler.changeToJson(map2, 2);
			String params = "["+main + "["+entries+"]}]";
			bb.writeLog("params:", params);
			String message = new EasOrmrpcHandle().createVoucher(params);
			bb.writeLog("message:", message);
			message = message.substring(1, message.length()-1);
			JSONObject json = new JSONObject(message);
			String number = json.getString("number");
			String state = json.getString("state");
			String msg = json.getString("msg");
			if("1".equals(state)){//失败
				throw new Exception("number:"+number+";errorMsg:"+msg);
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
