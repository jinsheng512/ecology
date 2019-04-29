package greedc.servlets;

import java.util.List;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import greedc.budgetplan.entities.CVExternalPayDt1;
import greedc.budgetplan.entities.UFBPAccountDt1;
import greedc.budgetplan.entities.UFBPConfig;
import greedc.budgetplan.entities.UFBPExternalPay;

/**
 * 外部经营性支出请求服务类。
 * @author William
 */
public class BPBussExpServlet extends BaseServlet {
	
	/**
	 * 获取外部经营性支出科目信息。
	 */
	public List<UFBPAccountDt1> listAccount() {
		
		UFBPConfig config = RecordUtil.find(UFBPConfig.class,
				"ISOPEN=? ORDER BY DATAORDER", 1);
		
		List<UFBPAccountDt1> accountList = RecordUtil.search(UFBPAccountDt1.class,
				"MAINID=?", config.getBussExpAccount());
		
		return accountList;
	}
	
	public Response active() throws Exception {
		int billId = getParameterIntegerCK("billid");
		UFBPExternalPay pay = RecordUtil.findById(UFBPExternalPay.class, billId);
		pay.setStatus(1);	// 生效
		RecordUtil.updateExact(pay, pay.getId(), "STATUS");
		return new Response(true, "保存成功！");
	}
	
	/**
	 * 查询外部经营性支出指定公司的所有数据
	 */
	public List<CVExternalPayDt1> listExternalPay() {
		int company = getParameterIntegerCK("company");
		int year = getParameterIntegerCK("year");
		List<CVExternalPayDt1> eList = RecordUtil.search(CVExternalPayDt1.class,
				"COMPANY=? AND YEAR=?", company, year);
		
		return eList;
	}
	
	/**
	 * 生效
	 */
	public Response effectiveBussExpServlet(){
		int id = getParameterIntegerCK("id"); 
		String sql = "UPDATE UF_BPEXTERNALPAY SET STATUS=1 WHERE ID=?";
		RecordUtil.executeUpdate(sql, id);
		return new Response(true, "生效成功！");
	}
	
	/**
	 * 取消生效
	 */
	public Response unEffectiveBussExpServlet(){
		int id = getParameterIntegerCK("id"); 
		String sql = "UPDATE UF_BPEXTERNALPAY SET STATUS=0 WHERE ID=?";
		RecordUtil.executeUpdate(sql, id);
		return new Response(true, "取消成功！");
	}
	
	
}
