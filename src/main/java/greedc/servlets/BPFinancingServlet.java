package greedc.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import greedc.budgetplan.entities.CBPFinancingDt1;
import greedc.budgetplan.entities.CBPFinancingDt2;
import greedc.budgetplan.entities.UFBPAccountDt1;
import greedc.budgetplan.entities.UFBPConfig;
import greedc.budgetplan.entities.UFBPFinancing;
import greedc.budgetplan.entities.UFBPFinancingDt1;

public class BPFinancingServlet extends BaseServlet {

	/**
	 * 生效
	 */
	public Response effectiveFinancing(){
		int id = getParameterIntegerCK("id"); 
		String sql = "UPDATE UF_BPFINANCING SET STATUS=1 WHERE ID=?";
		RecordUtil.executeUpdate(sql, id);
		return new Response(true, "生效成功！");
	}
	
	/**
	 * 获取信息。
	 * @return
	 */
	public List<UFBPFinancingDt1> getUFBPFinancingDt1Info() {

		List<UFBPFinancingDt1> lists = RecordUtil.searchAll(UFBPFinancingDt1.class);

		return lists;
	}

	public List<UFBPFinancingDt1> getUFBPFinancingDt1InfoById() {

		String id = getParameter("id");
		String whereSql = "id=? ";
		List<UFBPFinancingDt1> lists = RecordUtil.search(UFBPFinancingDt1.class,whereSql,id);

		return lists;
	}

	public Map<String, Object> getYearMonth() {

		String yearMonth = getParameter("yearMonth");
		String whereSql = "yearMonth=? ";
		UFBPFinancing fna = RecordUtil.find(UFBPFinancing.class, whereSql, yearMonth);
		
		List<CBPFinancingDt1> dt1List1 = RecordUtil.search(CBPFinancingDt1.class,
				"MAINID=?", fna.getId());
		
		List<CBPFinancingDt2> dt1List2 = RecordUtil.search(CBPFinancingDt2.class,
				"MAINID=?", fna.getId());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("main", fna);
		resultMap.put("dt1", dt1List1);
		resultMap.put("dt2", dt1List2);
		
		return resultMap;
	}
	
	 /**
		 * 获取科目。
		 * @return
		 */
		public Map<String, Object> getCompanyAccount() {
			
			UFBPConfig config = RecordUtil.find(UFBPConfig.class,
					"ISOPEN=? ORDER BY DATAORDER", 1);
			
			List<CBPFinancingDt1> dt1List1 = null;
			List<CBPFinancingDt2> dt1List2 = null ;
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			String yearMonth = getParameter("yearMonth");
			String whereSql = "yearmonth=? ";
			String whereSql2 = "mainId=?";
			List<UFBPAccountDt1> dt1_account = RecordUtil.search(
					UFBPAccountDt1.class, whereSql2,config.getFinanceIncAccount()); //收入
			
			List<UFBPAccountDt1> dt2_account = RecordUtil.search(
					UFBPAccountDt1.class, whereSql2,config.getFinanceExpAccount());
		
			UFBPFinancing fna = RecordUtil.find(UFBPFinancing.class, whereSql, yearMonth);
			
			if(fna!=null){
			 dt1List1 = RecordUtil.search(CBPFinancingDt1.class,
					"MAINID=?", fna.getId());
			
			 dt1List2 = RecordUtil.search(CBPFinancingDt2.class,
					"MAINID=?", fna.getId());
			}
			resultMap.put("dt1_account", dt1_account);
			resultMap.put("dt2_account", dt2_account);
			resultMap.put("dt1", dt1List1);
			resultMap.put("dt2", dt1List2);
			resultMap.put("main", fna);
			
			return resultMap;
		}
}
