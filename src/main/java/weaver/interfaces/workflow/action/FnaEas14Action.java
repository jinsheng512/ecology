package weaver.interfaces.workflow.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import weaver.fna.eas.EasOrmrpcHandle;
import weaver.fna.eas.FnaEasHandler;
import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 保证金抵扣审批单
 * @author Administrator
 *
 */
public class FnaEas14Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		FnaEasHandler fnaEasHandler = new FnaEasHandler();
		DecimalFormat df = new DecimalFormat("#####################0.00");
		try {
			bb.writeLog("FnaEas14Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			String workflowid = request.getWorkflowid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			
			bb.writeLog("formId:"+ formId);
			
			String number = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_number"));//明细表字段,表单编号
			String actPayAmt = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_actPayAmt"));//明细表字段,融资总金额
			String company = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_company"));//主表字段,所属公司
			String payeeName = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_payeeName"));//明细表字段,收款单位
			String payeeBank = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_payeeBank"));//明细表字段,收款单位开户行
			String payeeBankAcct = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_payeeBankAcct"));//明细表字段,收款方帐号
			String billDate = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_billDate"));//主表字段,申请日期
			
			
			String miantable = "formtable_main_"+formIdInt;
			String detailTable = miantable+"_dt1";
			
			String sql1 = "select a."+number+" as eas_number,"
							  + " a."+actPayAmt+" as eas_actPayAmt,"
							  + " a."+company+" as eas_company,"
							  + " a."+payeeName+" as eas_payeeName, "
							  + " a."+payeeBank+" as eas_payeeBank, "
							  + " a."+payeeBankAcct+" as eas_payeeBankAcct, "
							  + " a."+billDate+" as eas_billDate "
							  + " from "+miantable+" a "
//							  + " join "+detailTable+" b on a.id = b.mainid "
							  + " where a.requestid ="+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaEas14Action-001");
			}
			String number_val = "";
			double actPayAmt_val = 0d;
			String company_val = "";
			String payeeName_val = "";//收款单位
			String payeeBank_val = "";//收款单位开户行
			String payeeBankAcct_val = "";//收款方帐号
			String billDate_val = "";
			
			
			while(rs.next()){
				
				number_val = Util.null2String(rs.getString("eas_number"));
				actPayAmt_val = Util.getDoubleValue(rs.getString("eas_actPayAmt"),0.00);
				company_val = Util.null2String(rs.getString("eas_company"));
				payeeName_val = Util.null2String(rs.getString("eas_payeeName"));
				payeeBank_val = Util.null2String(rs.getString("eas_payeeBank"));
				payeeBankAcct_val = Util.null2String(rs.getString("eas_payeeBankAcct"));
				billDate_val = Util.null2String(rs.getString("eas_billDate"));
				
			}
				
			//获取所属公司编码(即分部编码)
//			String subcompanycode = fnaEasHandler.getSubcompanycode(company_val)[1];
			
			
//			String billDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Map<String,Object> map1 = new LinkedHashMap<String,Object>();
			map1.put("number", number_val);
			map1.put("actPayAmt", df.format(actPayAmt_val));
			map1.put("company", "49");
			map1.put("payeeNum", "");//收款单位对应编码,无需传
			map1.put("payeeName",payeeName_val);
			map1.put("payeeBank",payeeBank_val);
			map1.put("payeeBankAcct",payeeBankAcct_val);
			map1.put("billDate",billDate_val);
			map1.put("currency","BB01");
			map1.put("payBillType", "213");
			map1.put("payerAccountBankID", "03.05.10");
			map1.put("description","付骑呗项目保证金-"+ number_val);
			
			Map<String,Object> map2 = new LinkedHashMap<String,Object>();
			map2.put("costType", "");
			map2.put("remark", "");
			map2.put("amount", df.format(actPayAmt_val));
			map2.put("localAmt", df.format(actPayAmt_val));
			map2.put("rebateLocAmt", 0);
			map2.put("actualAmt", df.format(actPayAmt_val));
			
			String main = fnaEasHandler.changeToJson(map1, 1);
			String entries = fnaEasHandler.changeToJson(map2, 2);
			String params = "["+main + "["+entries+"]}]";
			bb.writeLog("params:", params);
			String message = new EasOrmrpcHandle().createVoucher(params);
			bb.writeLog("message:", message);
			message = message.substring(1, message.length()-1);
			JSONObject json = new JSONObject(message);
			String number1 = json.getString("number");
			String state = json.getString("state");
			String msg = json.getString("msg");
			if("1".equals(state)){//失败
				throw new Exception("number:"+number1+";errorMsg:"+msg);
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
