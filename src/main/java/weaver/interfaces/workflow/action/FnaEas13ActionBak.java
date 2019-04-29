package weaver.interfaces.workflow.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import weaver.fna.eas.EasOrmrpcHandle;
import weaver.fna.eas.FnaEasHandler;
import weaver.fna.general.RecordSet4Action;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

/**
 * 银行转账付款申请流程
 * @author Administrator
 *
 */
public class FnaEas13ActionBak implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		FnaEasHandler fnaEasHandler = new FnaEasHandler();
		DecimalFormat df = new DecimalFormat("#####################0.00");
		try {
			bb.writeLog("FnaEas13Action:"+request.getRequestid());
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
			
			String sql1 = "select b."+number+" as eas_number,"
							  + " b."+actPayAmt+" as eas_actPayAmt,"
							  + " a."+company+" as eas_company,"
							  + " b."+payeeName+" as eas_payeeName, "
							  + " b."+payeeBank+" as eas_payeeBank, "
							  + " b."+payeeBankAcct+" as eas_payeeBankAcct, "
							  + " a."+billDate+" as eas_billDate "
							  + " from "+miantable+" a "
							  + " join "+detailTable+" b on a.id = b.mainid "
							  + " where a.requestid ="+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaEas13Action-001");
			}
			String number_val = "";
			double actPayAmt_val = 0d;
			String company_val = "";
			String payeeName_val = "";//收款单位
			String payeeBank_val = "";//收款单位开户行
			String payeeBankAcct_val = "";//收款方帐号
			String billDate_val = "";
			
			
			List<Map<String,String>> detailList = new ArrayList<Map<String,String>>();
			
			
			while(rs.next()){
				
				Map<String,String> mainMap = new HashMap<String,String>();
				
				number_val = Util.null2String(rs.getString("eas_number"));
				actPayAmt_val = Util.getDoubleValue(rs.getString("eas_actPayAmt"),0.00);
				company_val = Util.null2String(rs.getString("eas_company"));
				payeeName_val = Util.null2String(rs.getString("eas_payeeName"));
				payeeBank_val = Util.null2String(rs.getString("eas_payeeBank"));
				payeeBankAcct_val = Util.null2String(rs.getString("eas_payeeBankAcct"));
				billDate_val = Util.null2String(rs.getString("eas_billDate"));
				
				
				mainMap.put("number_val", number_val);
				mainMap.put("actPayAmt_val", df.format(actPayAmt_val));
				mainMap.put("company_val", company_val);
				mainMap.put("payeeName_val", payeeName_val);
				mainMap.put("payeeBank_val", payeeBank_val);
				mainMap.put("payeeBankAcct_val", payeeBankAcct_val);
				mainMap.put("billDate_val", billDate_val);
				
				detailList.add(mainMap);
			
			}
			//获取所属公司编码(即分部编码)
//			String subcompanycode = fnaEasHandler.getSubcompanycode(company_val)[1];
			
			
			for(int i=0;i<detailList.size();i++){
				Map<String,String> rowMap = detailList.get(i);
//			String billDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			bb.writeLog("mian:"+ "name:"+detailList.get(i).get("number_val"));
			Map<String,Object> map1 = new LinkedHashMap<String,Object>();
			map1.put("number", rowMap.get("number_val"));
			map1.put("actPayAmt", rowMap.get("actPayAmt_val"));
			map1.put("company", "49");
			map1.put("payeeNum", "");//收款单位对应编码,无需传
			map1.put("payeeName",rowMap.get("payeeName_val"));
			map1.put("payeeBank",rowMap.get("payeeBank_val"));
			map1.put("payeeBankAcct",rowMap.get("payeeBankAcct_val"));
			map1.put("billDate",rowMap.get("billDate_val"));
			map1.put("currency","BB01");
			map1.put("payBillType", "213");
			map1.put("description","付"+rowMap.get("actPayAmt_val")+"转账款");
			
			Map<String,Object> map2 = new LinkedHashMap<String,Object>();
			map2.put("costType", "");
			map2.put("remark", "");
			map2.put("amount", rowMap.get("actPayAmt_val"));
			map2.put("localAmt", rowMap.get("actPayAmt_val"));
			map2.put("rebateLocAmt", 0);
			map2.put("actualAmt", rowMap.get("actPayAmt_val"));
			
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
