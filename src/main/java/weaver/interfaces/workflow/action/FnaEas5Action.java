package weaver.interfaces.workflow.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import weaver.conn.RecordSet;
import weaver.fna.eas.EasOrmrpcHandle;
import weaver.fna.eas.FnaEasHandler;
import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 日常费用报销流程
 * @author Administrator
 *
 */
public class FnaEas5Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		FnaEasHandler fnaEasHandler = new FnaEasHandler();
		DecimalFormat df = new DecimalFormat("#####################0.00");
		try {
			bb.writeLog("FnaEas5Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			String workflowid = request.getWorkflowid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			RecordSet rs2 = new RecordSet();
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			String tableApplicate = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_applicate"));//主表字段,申请人
			String tableNumber = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_number"));//主表字段,表单编号
			String tableCompany = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_company"));//主表字段,费用支付公司
			String tablePayee = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_payee"));//主表字段,收款人
			String tablePayeeBank = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_payeeBank"));//主表字段,收款银行
			String tablePayeeBankAccount = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_payeeBankAccount"));//主表字段,收款帐号
			String tableAmount = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_amount"));//明细表,审定金额
			String tableCostType = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_costType"));//明细表,费用类型
			String tableRemark = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_remark"));//明细表,备注
			String miantable = "formtable_main_"+formIdInt;
			String detailTable = miantable+"_dt1";
			String sql1 = "select c.lastname,"
							  + " a."+tableNumber+" as tableNumber,"
							  + " a."+tableCompany+" as tableCompany,"
							  + " a."+tablePayee+" as tablePayee,"
							  + " a."+tablePayeeBank+" as tablePayeeBank,"
							  + " a."+tablePayeeBankAccount+" as tablePayeeBankAccount,"
							  + " b."+tableAmount+" as tableAmount, "
							  + " b."+tableCostType+" as tableCostType, "
							  + " b."+tableRemark+" as tableRemark "
							  + " from "+miantable+" a "
							  + " join "+detailTable+" b on a.id = b.mainid "
							  + " join hrmresource c on a."+tableApplicate+" = c.id "
							  + " where a.requestid ="+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaEas5Action-001");
			}
			String billDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String lastname = "";
			String tableNumber_val = "";
			int tableCompany_val = 0;
			int tablePayee_val = 0;
			int tablePayeeBank_val = 0;
			String tablePayeeBankAccount_val = "";
			double tableAmount_val = 0d;
			String tableCostType_val = "";
			String tableRemark_val = "";
			String main = "";
			String entries = "";
			double total = 0d;
			while(rs.next()){
				lastname = Util.null2String(rs.getString("lastname"));
				tableNumber_val = Util.null2String(rs.getString("tableNumber"));
				tableCompany_val = Util.getIntValue(rs.getString("tableCompany"));
				tablePayee_val = Util.getIntValue(rs.getString("tablePayee"));
				tablePayeeBank_val = Util.getIntValue(rs.getString("tablePayeeBank"));
				tablePayeeBankAccount_val = Util.null2String(rs.getString("tablePayeeBankAccount"));
				tableAmount_val = Util.getDoubleValue(rs.getString("tableAmount"),0.00);
				tableCostType_val = Util.null2String(rs.getString("tableCostType"));
				tableRemark_val = Util.null2String(rs.getString("tableRemark"));
				String sql02 = "select bianm from uf_feiylb where id="+tableCostType_val;
				sqlFlag = rs2.executeSql(sql02);
				if(!sqlFlag){
					throw new Exception("sql出错，位置FnaEas5Action-002");
				}
				String bianm = "";
				while(rs2.next()){
					bianm = Util.null2String(rs2.getString("bianm"));
				}
				Map<String,Object> map2 = new LinkedHashMap<String,Object>();
				map2.put("costType", bianm);
				map2.put("remark", tableRemark_val);
				map2.put("amount", df.format(tableAmount_val));
				map2.put("localAmt", df.format(tableAmount_val));
				map2.put("rebateLocAmt", 0);
				map2.put("actualAmt", df.format(tableAmount_val));
				entries += fnaEasHandler.changeToJson(map2, 2)+",";
				total += tableAmount_val;
			}
			//获取费用支付公司的编码(即分部编码)
			String subcompanycode = fnaEasHandler.getSubcompanycode(tableCompany_val)[1];
			//获取收款人对应编码,名称
			String workcode = fnaEasHandler.getWorkcode(tablePayee_val)[0];
			String lastnamePayee = fnaEasHandler.getWorkcode(tablePayee_val)[1];
			//获取开户行名称
			String payeeBank = fnaEasHandler.getPayeeBank(tablePayeeBank_val);
			
			Map<String,Object> map1 = new LinkedHashMap<String,Object>();
			map1.put("number", tableNumber_val);
			map1.put("actPayAmt", df.format(total));
			map1.put("company", subcompanycode);
			map1.put("payeeNum", workcode);
			map1.put("payeeName", lastnamePayee);
			map1.put("payeeBank", payeeBank);
			map1.put("payeeBankAcct", tablePayeeBankAccount_val);
			map1.put("billDate", billDate);
			map1.put("currency", "BB01");
			map1.put("payBillType", "211");
			map1.put("description", "付"+lastname+"报销款/差旅费");
			main = fnaEasHandler.changeToJson(map1, 1);
			entries = entries.substring(0,entries.length()-1);
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
