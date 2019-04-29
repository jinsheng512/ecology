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
 * 非工程付款流程
 * @author Administrator
 *
 */
public class FnaEas1Action implements Action{

	@Override
	public String execute(RequestInfo request) {
		BaseBean bb = new BaseBean();
		FnaEasHandler fnaEasHandler = new FnaEasHandler();
		DecimalFormat df = new DecimalFormat("#####################0.00");
		try {
			bb.writeLog("FnaEas1Action:"+request.getRequestid());
			String requestid = request.getRequestid();
			String workflowid = request.getWorkflowid();
			RecordSet4Action rs = new RecordSet4Action(request.getRsTrans());
			String formId = request.getRequestManager().getFormid()+"";
			int formIdInt = Math.abs(Util.getIntValue(formId, 0));
			String tableNumber = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_number"));//主表字段,表单编号
			String tableCurrMoney = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_currMoney"));//主表字段,本次申请金额
			String tablePaymentCompany = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_paymentCompany"));//主表字段,付款公司
			String tableCollectionCompany = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_collectionCompany"));//主表字段,收款单位
			String tableCollectionCompanyKHH = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_collectionCompanyKHH"));//主表字段,收款单位开户行
			String tableCollectionCompanyZH = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_collectionCompanyZH"));//主表字段,收款方帐号
			String tableContractNo = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_contractNo"));//主表字段,合同编号
			String tableContractName = Util.null2String(bb.getPropValue("QC228542", "table_"+formIdInt+"_contractName"));//主表字段,合同名称
			String miantable = "formtable_main_"+formIdInt;
			String sql1 = "select a."+tableNumber+" as tableNumber,"
								  + " a."+tableCurrMoney+" as tableCurrMoney,"
								  + " a."+tablePaymentCompany+" as tablePaymentCompany,"
								  + " a."+tableCollectionCompany+" as tableCollectionCompany,"
								  + " a."+tableCollectionCompanyKHH+" as tableCollectionCompanyKHH,"
								  + " a."+tableCollectionCompanyZH+" as tableCollectionCompanyZH,"
								  + " a."+tableContractNo+" as tableContractNo, "
								  + " a."+tableContractName+" as tableContractName "
								  + " from "+miantable+" a where a.requestid = "+requestid;
			bb.writeLog("sql1>>>>", sql1);
			boolean sqlFlag = rs.executeSql(sql1);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaEas1Action-001");
			}
			String billDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String tableNumber_val = "";
			double tableCurrMoney_val = 0d;
			int tablePaymentCompany_val = 0;
			String tableCollectionCompany_val = "";//收款单位
			String tableCollectionCompanyKHH_val = "";//收款单位开户行
			String tableCollectionCompanyZH_val = "";//收款方帐号
			int tableContractNo_val = 0;
			String tableContractName_val = "";
			while(rs.next()){
				tableNumber_val = Util.null2String(rs.getString("tableNumber"));
				tableCurrMoney_val = Util.getDoubleValue(rs.getString("tableCurrMoney"),0.00);
				tablePaymentCompany_val = Util.getIntValue(rs.getString("tablePaymentCompany"));
				tableCollectionCompanyKHH_val = Util.null2String(rs.getString("tableCollectionCompanyKHH"));
				tableCollectionCompanyZH_val = Util.null2String(rs.getString("tableCollectionCompanyZH"));
				tableCollectionCompany_val = Util.null2String(rs.getString("tableCollectionCompany"));
				tableContractNo_val = Util.getIntValue(rs.getString("tableContractNo"));
				tableContractName_val = Util.null2String(rs.getString("tableContractName"));
			}
			//获取付款公司编码(即分部编码)
			String subcompanycode = fnaEasHandler.getSubcompanycode(tablePaymentCompany_val)[1];
			String sql2 = "select hetbh from uf_het where id="+tableContractNo_val;
			sqlFlag = rs.executeSql(sql2);
			if(!sqlFlag){
				throw new Exception("sql出错，位置FnaEas1Action-002");
			}
			String ContractNo = "";
			while(rs.next()){
				ContractNo = Util.null2String(rs.getString("htebh"));
			} 
			
			Map<String,Object> map1 = new LinkedHashMap<String,Object>();
			map1.put("number", tableNumber_val);
			map1.put("actPayAmt", df.format(tableCurrMoney_val));
			map1.put("company", subcompanycode);
			map1.put("payeeNum", "");//收款单位对应编码,无需传
			map1.put("payeeName", tableCollectionCompany_val);
			map1.put("payeeBank", tableCollectionCompanyKHH_val);
			map1.put("payeeBankAcct", tableCollectionCompanyZH_val);
			map1.put("billDate",billDate);
			map1.put("currency","BB01");
			map1.put("payBillType", "999");
			map1.put("description",ContractNo+":付"+tableCollectionCompany_val+"<"+tableContractName_val+">");
			
			Map<String,Object> map2 = new LinkedHashMap<String,Object>();
			map2.put("costType", "");
			map2.put("remark", "");
			map2.put("amount", df.format(tableCurrMoney_val));
			map2.put("localAmt", df.format(tableCurrMoney_val));
			map2.put("rebateLocAmt", 0);
			map2.put("actualAmt", df.format(tableCurrMoney_val));
			map2.put("contractNum", tableContractNo_val);
			
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
