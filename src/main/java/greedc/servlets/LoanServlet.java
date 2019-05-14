package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.util.CustomUtil;

import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.java2d.SunGraphicsEnvironment.T1Filter;
import ecustom.ecology8.commons.RecordUtil;
import greedc.budgetplan.entities.CBPCompanyAccount;
import greedc.budgetplan.entities.LoanBank;
import greedc.budgetplan.entities.LoanBankJg;
import greedc.budgetplan.entities.LoanBlock;
import greedc.budgetplan.entities.LoanDaiktype;
import greedc.budgetplan.entities.LoanFinancing;
import greedc.budgetplan.entities.LoanFinancingDt1;
import greedc.budgetplan.entities.LoanGuarantee;
import greedc.budgetplan.entities.LoanInteresttype;
import greedc.budgetplan.entities.LoanJkcompany;
import greedc.budgetplan.entities.LoanLetters;
import greedc.budgetplan.entities.LoanMortgage;
import greedc.budgetplan.entities.LoanProject;
import greedc.budgetplan.entities.Loankjsubject;
import greedc.budgetplan.entities.UFBPCompanyPlan;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1;
import greedc.budgetplan.entities.UFBPCompanyPlanDt2;
import greedc.budgetplan.entities.UFBPCompanyPlanDt3;
import greedc.budgetplan.entities.UFBPCompanyPlanDt4;
import greedc.budgetplan.entities.UFBPCompanyPlanDt5;

public class LoanServlet extends BaseServlet {

	private final static Logger log = Logger.getLogger(LoanServlet.class);

	public Map<String, Object> dataGridPage() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String key = getParameterCK("key");
		String sort = getParameter("sort");
		int page = CustomUtil.getInt(getParameterCK("page"), 1);
		int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		
		String orderSqlT2 = "";
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT2 = " ORDER BY " + sort;
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
		}
		String totalSql = "SELECT COUNT(1) FROM " + objectName + whereSql;
		log.debug("totalSql = " + totalSql);
		Map<String, Object> pageResult = new HashMap<String, Object>();
		pageResult.put("total", RecordUtil.getInt(totalSql));

		String listSql = "SELECT DISTINCT " + field + " FROM " + objectName + " T1"
				+ " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM " + objectName + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
				+ " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")")
				+ orderSqlT1;
		log.debug("listSql = " + listSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
		pageResult.put("rows", records);
		
		return pageResult;
	}
	
	// 借款
	
	public List<Map<String, String>> dataGrid() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String sort = getParameter("sort");
		String q =   URLDecoder.decode(getParameter("q"),"utf-8");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		String startDate = getParameter("startDate", true);
		String endDate = getParameter("endDate", true);
		
		
		String jkdwmcid = getParameter("jkdwmcid");
		String daiklxid = getParameter("daiklxid");
		String kuaijkmid = getParameter("kuaijkmid");
		String xiangmmcid = getParameter("xiangmmcid");
		String danbhtid = getParameter("danbhtid");
		String shouxhthid = getParameter("shouxhthid");
		String jinrjgid = getParameter("jinrjgid");
		String fuxfsid = getParameter("fuxfsid");
		String diyhtbhid = getParameter("diyhtbhid");
		
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		
		if (CustomUtil.isNotBlank(jkdwmcid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "companyId="  + jkdwmcid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(daiklxid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "daiklxid="  + daiklxid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(kuaijkmid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "kuaijkmid="  + kuaijkmid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(danbhtid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "danbhtid="  + danbhtid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(xiangmmcid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "xiangmmcid="  + xiangmmcid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(shouxhthid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "shouxhthid="  + shouxhthid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(jinrjgid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "bankId="  + jinrjgid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(fuxfsid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "fuxfsid="  + fuxfsid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(diyhtbhid)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "diywmcid="  + diyhtbhid + ")" ;
					
		}
		
		if (CustomUtil.isNotBlank(q)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "companyName" + " LIKE '%" + q + "%')" + "or" + "(" + "bankName" + " LIKE '%" + q + "%')" +"or" + "(" + "danbht" + " LIKE '%" + q + "%')"+"or" + "(" + "shouxhth" + " LIKE '%" + q + "%')";
					
		}

		String listSql = "SELECT DISTINCT " + field
				+ " FROM " + objectName + " T1"
				+ orderSqlT1;
		
		whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(TO_DATE (T1.lurrq , 'yyyy-MM-dd')>=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd') AND TO_DATE (T1.lurrq , 'yyyy-MM-dd') <=TO_DATE ("+ "\'" +endDate + "\'"+ ", 'yyyy-MM-dd'))";
		log.debug("listSql = " + listSql+whereSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql+whereSql, fieldArr);
		
		return records;
	}
	
	//还款
	
	public List<Map<String, String>> dataGridRepay() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String sort = getParameter("sort");
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		String startDate = getParameter("startDate", true);
		String endDate = getParameter("endDate", true);
		
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
		}

		String listSql = "SELECT DISTINCT " + field
				+ " FROM " + objectName + " T1"
				+ orderSqlT1;
		
		whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(TO_DATE (T1.lurrq , 'yyyy-MM-dd')>=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd') AND TO_DATE (T1.qisrq , 'yyyy-MM-dd') <=TO_DATE ("+ "\'" +endDate + "\'"+ ", 'yyyy-MM-dd')) order by t1.yujhkrq asc";
		log.debug("listSql = " + listSql+whereSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql+whereSql, fieldArr);
		
		return records;
	}
	
	//利息台账
	public List<Map<String, String>> dataGridInterest() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String sort = getParameter("sort");
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		String year = getParameter("year", true);
		
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
		}

		String listSql = "SELECT DISTINCT " + field
				+ " FROM " + objectName + " T1"
				;
		
		whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "qijiann="+year + orderSqlT1;
		log.debug("listSql = " + listSql+whereSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql+whereSql, fieldArr);
		
		return records;
	}
	
	
	//授信明细表，余额统计表
	
	public Map<String, Object> dataGridShou() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String key = getParameterCK("key");
		String sort = getParameter("sort");
		int page = CustomUtil.getInt(getParameterCK("page"), 1);
		int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		
		String orderSqlT2 = "";
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT2 = " ORDER BY " + sort;
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
		}
		String totalSql = "SELECT COUNT(1) FROM " + objectName + whereSql;
		log.debug("totalSql = " + totalSql);
		Map<String, Object> pageResult = new HashMap<String, Object>();
		pageResult.put("total", RecordUtil.getInt(totalSql));

		String listSql = "SELECT DISTINCT " + field + " FROM " + objectName + " T1"
				+ " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM " + objectName + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
				+ " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")")
				+ orderSqlT1;
		log.debug("listSql = " + listSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
		pageResult.put("rows", records);
		
		return pageResult;
	}
	
	
	//利息台账1
	
	public Map<String, Object> dataGridInterest1() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String key = getParameterCK("key");
		String sort = getParameter("sort");
		int page = CustomUtil.getInt(getParameterCK("page"), 1);
		int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		String year = getParameter("year", true);
		
		String orderSqlT2 = "";
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT2 = " ORDER BY " + sort;
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
		}
		String totalSql = "SELECT COUNT(1) FROM " + objectName + whereSql;
		log.debug("totalSql = " + totalSql);
		Map<String, Object> pageResult = new HashMap<String, Object>();
		pageResult.put("total", RecordUtil.getInt(totalSql));

		String listSql = "SELECT DISTINCT " + field +",t5.total"+ " FROM " + objectName + " T1"
				+ " left JOIN(select distinct(mainid)mainid ,sum(lixje)total from uf_borrowing_dt2 where qijiann=" + year + "group by mainid) t5 ON t5.mainid = t1.id"
				+ " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM " + objectName + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
				+ " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")")
				+ " AND " + year +">=Substr(T1.qisrq, 0,4) AND " + year +"<=Substr (T1.zhongzrq,0,4)" 
				+ orderSqlT1;
		log.debug("listSql = " + listSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
		pageResult.put("rows", records);
		
		return pageResult;
	}
	
	
	//借还

	public Map<String, Object> getBPCompanyPlan() {
		String year = getParameterCK("year");
		
		String whereSql = "substr(lurrq,1,7)=to_char(to_date(?,'yyyymm'),'yyyy-mm')";
		String whereSql2 = "substr(shijhkrq,1,7)=to_char(to_date(?,'yyyymm'),'yyyy-mm')";
		
		List<LoanFinancing> dt1 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"01");
		
		List<LoanFinancing> dt2 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"02");
		
		List<LoanFinancing> dt3 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"03");
		
		List<LoanFinancing> dt4 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"04");
		
		List<LoanFinancing> dt5 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"05");
		
		List<LoanFinancing> dt6 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"06");
		
		List<LoanFinancing> dt7 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"07");
		
		List<LoanFinancing> dt8 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"08");
		
		List<LoanFinancing> dt9 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"09");
		
		List<LoanFinancing> dt10 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"10");
		
		List<LoanFinancing> dt11 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"11");
		
		List<LoanFinancing> dt12 = RecordUtil.search(LoanFinancing.class,
				whereSql, year+"12");
		
		
		List<LoanFinancingDt1> dt_back1 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"01");
		
		List<LoanFinancingDt1> dt_back2 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"02");
		
		List<LoanFinancingDt1> dt_back3 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"03");
		
		List<LoanFinancingDt1> dt_back4 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"04");
		
		List<LoanFinancingDt1> dt_back5 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"05");
		
		List<LoanFinancingDt1> dt_back6 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"06");
		
		List<LoanFinancingDt1> dt_back7 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"07");
		
		List<LoanFinancingDt1> dt_back8 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"08");
		
		List<LoanFinancingDt1> dt_back9 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"09");
		
		List<LoanFinancingDt1> dt_back10 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"10");
		
		List<LoanFinancingDt1> dt_back11 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"11");
		
		List<LoanFinancingDt1> dt_back12 = RecordUtil.search(LoanFinancingDt1.class,
				whereSql2, year+"12");
		
		
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("dt1", dt1);
		resultMap.put("dt2", dt2);
		resultMap.put("dt3", dt3);
		resultMap.put("dt4", dt4);
		resultMap.put("dt5", dt5);
		resultMap.put("dt6", dt6);
		resultMap.put("dt7", dt7);
		resultMap.put("dt8", dt8);
		resultMap.put("dt9", dt9);
		resultMap.put("dt10", dt10);
		resultMap.put("dt11", dt11);
		resultMap.put("dt12", dt12);
		
		resultMap.put("dt_back1", dt_back1);
		resultMap.put("dt_back2", dt_back2);
		resultMap.put("dt_back3", dt_back3);
		resultMap.put("dt_back4", dt_back4);
		resultMap.put("dt_back5", dt_back5);
		resultMap.put("dt_back6", dt_back6);
		resultMap.put("dt_back7", dt_back7);
		resultMap.put("dt_back8", dt_back8);
		resultMap.put("dt_back9", dt_back9);
		resultMap.put("dt_back10", dt_back10);
		resultMap.put("dt_back11", dt_back11);
		resultMap.put("dt_back12", dt_back12);
		
		return resultMap;
	}
	
	 /**
		 * 获取指定金融机构。
		 * @return
		 */
	
	public List<LoanBank> getLoanBank() {
		List<LoanBank> loanBank = RecordUtil.searchAll(
				LoanBank.class);
		return loanBank;
	}
	
	
	 /**
		 * 获取指定项目。
		 * @return
		 */
	
	public List<LoanProject> getLoanProject() {
	
		List<LoanProject> loanProject = RecordUtil.searchAll(
				LoanProject.class);
		return loanProject;
	}
	
	 /**
	 * 获取借款信息。
	 * @return
	 */

    public List<LoanFinancing> getLoanInfo() {

	List<LoanFinancing> loanFinancing = RecordUtil.searchAll(
			LoanFinancing.class);
	return loanFinancing;
}
    
	 /**
	 * 借款条件查询
	 * @return
	 */
    
	public Map<String, Object> getSearchData() {
//		String year = getParameterCK("year");
		
		
		List<LoanJkcompany> jkcompanyList = RecordUtil.searchAll(LoanJkcompany.class);
		List<LoanDaiktype> daiktypeList = RecordUtil.searchAll(LoanDaiktype.class);
		List<Loankjsubject> kjsubjectList = RecordUtil.searchAll(Loankjsubject.class);
		List<LoanProject> projectList = RecordUtil.searchAll(LoanProject.class);
		List<LoanGuarantee> guaranteeList = RecordUtil.searchAll(LoanGuarantee.class);
		List<LoanLetters> lettersList = RecordUtil.searchAll(LoanLetters.class);
		List<LoanBank> bankList = RecordUtil.searchAll(LoanBank.class);
		List<LoanInteresttype> interesttypeList = RecordUtil.searchAll(LoanInteresttype.class);
		List<LoanMortgage> mortgageList = RecordUtil.searchAll(LoanMortgage.class);
		
	
		
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("jkcompanyList", jkcompanyList);
		resultMap.put("daiktypeList", daiktypeList);
		resultMap.put("kjsubjectList", kjsubjectList);
		resultMap.put("projectList", projectList);
		resultMap.put("guaranteeList", guaranteeList);
		resultMap.put("lettersList", lettersList);
		resultMap.put("bankList", bankList);
		resultMap.put("interesttypeList", interesttypeList);
		
		resultMap.put("mortgageList", mortgageList);
	
		
		return resultMap;
	}
	
	//余额详细表
	
	public Map<String, Object> dataGridBalance() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String key = getParameterCK("key");
		String sort = getParameter("sort");
		int page = CustomUtil.getInt(getParameterCK("page"), 1);
		int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		String startDate = getParameter("startDate", true);
//		String endDate = getParameter("endDate", true);
		
		String orderSqlT2 = "";
		String orderSqlT1 = "";
		if (CustomUtil.isNotBlank(sort)) {
			orderSqlT2 = " ORDER BY " + sort;
			orderSqlT1 = " ORDER BY T1." + sort;
		}
		
		String[] fieldArr = null;
		if (CustomUtil.isBlank(field)) {
			field = "T1.*";
		} else {
			fieldArr = field.split(",");
			field = "T1." + fieldArr[0];
			for (int i = 1; i < fieldArr.length; i++) {
				field += ", T1." + fieldArr[i];
			}
		}
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			defFilter = defFilter.replace("{AND}", "AND");
			whereSql = " WHERE (" + defFilter + ")";
		}
		if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
		}
		
//		if (CustomUtil.isNotBlank(startDate)) {
//			whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd'))";
//		}
		
		
		String Sql="select  t5.id,t8.subcompanyname,t5.yewrq,t5.tianbr,t11.lastname,t9.bankmc,t5.jinrjg,t7.kaihh,t5.yinhzh,t6.zhanghxzmc,t10.currencydesc,t5.benwb,t5.yuanb,t5.shifjgzh,t5.shifdkjajhzyh,t5.yongt  from ("
			       +" select  t1.*,t4.yuanb,t4.benwb,t4.yewrq,t4.tianbr  from  uf_account t1 "
                   +" left join  "
                   +" (select t2. * ,t3.yewrq ,t3.tianbr from uf_accountyedj_DT1 t2  "
                   +" left join uf_accountyedj t3 on t3.id = t2.mainid "
                   +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
                   +" )t4 on t4.yinhzh = t1.id "
                   +" )t5 "
                   +" LEFT JOIN uf_yeaccounttype t6 ON t6.id = t5.zhanghxz "
		           +" LEFT JOIN uf_bankyu t7 ON t7.id = t5.kaihh "
		           +" LEFT JOIN hrmsubcompany t8 on t8.id = t5.gongsmc "
		           +" LEFT JOIN uf_bankuai t9 on t9.id = t5.suosbk "
		           +" LEFT JOIN FnaCurrency t10 on t10.id = t5.bib "
		           +" LEFT JOIN hrmresource t11 on t11.id = t5.tianbr ";
		   		
		
		String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
		log.debug("totalSql = " + totalSql);
		Map<String, Object> pageResult = new HashMap<String, Object>();
		pageResult.put("total", RecordUtil.getInt(totalSql));

		String listSql= Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T12 ON T12." + key + "=T5." + key
                            + " WHERE T12.ROWNO>" + ((page - 1) * rows) + " AND T12.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
//                            +" order by t8.subcompanyname,t7.kaihh desc";
		log.debug("listSql = " + listSql);

		List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
		pageResult.put("rows", records);
		
		return pageResult;
	}
	
	//获取余额统计表監管非監管项数据
	
		public Map<String, Object> dataGridJinRjg() throws Exception {
			String objectName = getParameterCK("name");
			String field = getParameter("field");
			String key = getParameterCK("key");
			String sort = getParameter("sort");
			int page = CustomUtil.getInt(getParameterCK("page"), 1);
			int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
			String q = getParameter("q");
			String filter = getParameter("filter");
			String defFilter = getParameter("defFilter");
			String startDate = getParameter("startDate", true);
			
			String orderSqlT2 = "";
			String orderSqlT1 = "";
			if (CustomUtil.isNotBlank(sort)) {
				orderSqlT2 = " ORDER BY " + sort;
				orderSqlT1 = " ORDER BY T1." + sort;
			}
			
			String[] fieldArr = null;
			if (CustomUtil.isBlank(field)) {
				field = "T1.*";
			} else {
				fieldArr = field.split(",");
				field = "T1." + fieldArr[0];
				for (int i = 1; i < fieldArr.length; i++) {
					field += ", T1." + fieldArr[i];
				}
			}
			
			String whereSql = "";
			if (CustomUtil.isNotBlank(defFilter)) {
				defFilter = defFilter.replace("{LIKE}", "LIKE");
				defFilter = defFilter.replace("{AND}", "AND");
				whereSql = " WHERE (" + defFilter + ")";
			}
			if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
				whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
			}
			

			String Sql="select  * from ("
				       +" select  t7.shifjgh,t8.kaihh,t7.zhanghxzmc,t9.* from "
                    +" (select t6.kaihuh,  t6.zhanghxz  ,sum( t6.yuanb) as yuanb ,sum( t6.benwb) as benwb  from "
                    +" ("+objectName+")t6 "
                    +" where (TO_DATE (T6.yewrq , 'yyyy-MM-dd')>=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
                    +" group by  t6.kaihuh, t6.zhanghxz "
                    +" )t9 left join uf_yeaccounttype t7 on t7.id = t9.zhanghxz "
                    +" LEFT JOIN uf_bankyu t8 ON t8.id = t9.kaihuh"
                    +") t10";
			
			String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
			log.debug("totalSql = " + totalSql);
			Map<String, Object> pageResult = new HashMap<String, Object>();
			pageResult.put("total", RecordUtil.getInt(totalSql));
			
			String listSql= Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T12 ON T12." + key + "=T10." + key
	                            + " WHERE T12.ROWNO>" + ((page - 1) * rows) + " AND T12.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
			              
			

//			String listSql = "SELECT DISTINCT " + field + " FROM " + objectName + " T1"
//					+ " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM " + objectName + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
//					+ " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")")
//					+ " and (TO_DATE (T1.yewrq , 'yyyy-MM-dd')>=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd') AND TO_DATE (T1.yewrq , 'yyyy-MM-dd') <=TO_DATE ("+ "\'" +endDate + "\'"+ ", 'yyyy-MM-dd')) ";
			log.debug("listSql = " + listSql);

			List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
			pageResult.put("rows", records);
			
			return pageResult;
		}
		
		//获取余额统计表版快項数据

		public Map<String, Object> dataGridBlock() throws Exception {
			String objectName = getParameterCK("name");
			String field = getParameter("field");
			String key = getParameterCK("key");
			String sort = getParameter("sort");
			int page = CustomUtil.getInt(getParameterCK("page"), 1);
			int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
			String q = getParameter("q");
			String filter = getParameter("filter");
			String defFilter = getParameter("defFilter");
			String startDate = getParameter("startDate", true);
			
			String orderSqlT2 = "";
			String orderSqlT1 = "";
			if (CustomUtil.isNotBlank(sort)) {
				orderSqlT2 = " ORDER BY " + sort;
				orderSqlT1 = " ORDER BY T1." + sort;
			}
			
			String[] fieldArr = null;
			if (CustomUtil.isBlank(field)) {
				field = "T1.*";
			} else {
				fieldArr = field.split(",");
				field = "T1." + fieldArr[0];
				for (int i = 1; i < fieldArr.length; i++) {
					field += ", T1." + fieldArr[i];
				}
			}
			
			String whereSql = "";
			if (CustomUtil.isNotBlank(defFilter)) {
				defFilter = defFilter.replace("{LIKE}", "LIKE");
				defFilter = defFilter.replace("{AND}", "AND");
				whereSql = " WHERE (" + defFilter + ")";
			}
			if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
				whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
			}
			

			String Sql="select   t2.kaihh,t1.* from ("
                    +" select kaihuh,suosbk ,sum(yuanb) as yuanb ,sum(benwb) as benwb  from "
                    +" ("+objectName+") "
                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
                    +" group by  kaihuh, suosbk "
                    +" )t1"
                    +" LEFT JOIN uf_bankyu t2 ON t2.id = t1.kaihuh";
			
			String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
			log.debug("totalSql = " + totalSql);
			Map<String, Object> pageResult = new HashMap<String, Object>();
			pageResult.put("total", RecordUtil.getInt(totalSql));
			
			String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
	                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
			              
			

			log.debug("listSql = " + 7);

			List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
			pageResult.put("rows", records);
			
			return pageResult;
		}
	
		
		//获取是否监管小计

		public Map<String, Object> dataGridJianG() throws Exception {
			String objectName = getParameterCK("name");
			String field = getParameter("field");
			String key = getParameterCK("key");
			String sort = getParameter("sort");
			int page = CustomUtil.getInt(getParameterCK("page"), 1);
			int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
			String q = getParameter("q");
			String filter = getParameter("filter");
			String defFilter = getParameter("defFilter");
			String startDate = getParameter("startDate", true);
			
			String orderSqlT2 = "";
			String orderSqlT1 = "";
			if (CustomUtil.isNotBlank(sort)) {
				orderSqlT2 = " ORDER BY " + sort;
				orderSqlT1 = " ORDER BY T1." + sort;
			}
			
			String[] fieldArr = null;
			if (CustomUtil.isBlank(field)) {
				field = "T1.*";
			} else {
				fieldArr = field.split(",");
				field = "T1." + fieldArr[0];
				for (int i = 1; i < fieldArr.length; i++) {
					field += ", T1." + fieldArr[i];
				}
			}
			
			String whereSql = "";
			if (CustomUtil.isNotBlank(defFilter)) {
				defFilter = defFilter.replace("{LIKE}", "LIKE");
				defFilter = defFilter.replace("{AND}", "AND");
				whereSql = " WHERE (" + defFilter + ")";
			}
			if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
				whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
			}
			

			String Sql="select   t2.kaihh,t1.* from ("
                    +" select kaihuh,shifjgh,sum(yuanb)total_jiang from "
                    +" ("+objectName+") "
                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
                    +" group by  kaihuh,shifjgh "
                    +" )t1"
                    +" LEFT JOIN uf_bankyu t2 ON t2.id = t1.kaihuh";
			
			String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
			log.debug("totalSql = " + totalSql);
			Map<String, Object> pageResult = new HashMap<String, Object>();
			pageResult.put("total", RecordUtil.getInt(totalSql));
			
			String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
	                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
			              
			

			log.debug("listSql = " + 7);

			List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
			pageResult.put("rows", records);
			
			return pageResult;
		}
		
		

		
		//获取金融机构，分类和合计

				public Map<String, Object> dataGridTotal() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql="select t5.*,t6.total_yuanb from cv_jinrjg t5 "
		                    +" left join(select kaihuh,sum(yuanb)total_yuanb from "
		                    +" (select t2.kaihuh  ,t2.yuanb ,t3.yewrq  from uf_accountyedj_dt1 t2 "
		                    +" left join  uf_accountyedj t3 on t3.id = t2.mainid ) t1 "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  kaihuh ) t6 on t6.kaihuh = t5.kaihuh ";
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + Sql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T5." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
				
				
				//获取板块，公司，金融机构，分类和合计

				public Map<String, Object> dataGridTotal01() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String blockid = getParameter("blockid");
//					String daiklxid = getParameter("daiklxid");
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					
					if (CustomUtil.isNotBlank(blockid)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "suosbk="  + blockid + ")" ;
								
					}
					
					String Sql=" select t8.* from ("
							+ " select  ROWNUM id,t6.* from ( "
							+"  select  suosbk,bankmc,suosgs,subcompanyname,sum(yuanb)total_yuanb,yewrq from  ("
		                    +"  select t2.suosbk,t2.suosgs  ,t2.benwb yuanb ,t3.yewrq ,t4.bankmc,t5.subcompanyname  from uf_accountyedj_dt1 t2 "
		                    +"  left join  uf_accountyedj t3 on t3.id = t2.mainid "
		                    +"  left join  uf_bankuai t4 on t4.id = t2.suosbk  "
		                    +"  left join  hrmsubcompany t5 on t5.id = t2.suosgs "
		                    +"  ) t1 "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  suosbk,suosgs,yewrq,bankmc,subcompanyname ) t6 ) t8";

//					String Sql=" select t8.* from ("
//							+ "select  ROWNUM id,t5.*,t6.total_yuanb from cv_jinrjg_01 t5 "
//		                    +" left join(select suosbk,suosgs,sum(yuanb)total_yuanb from "
//		                    +" (select t2.suosbk,t2.suosgs  ,t2.yuanb ,t3.yewrq  from uf_accountyedj_dt1 t2 "
//		                    +" left join  uf_accountyedj t3 on t3.id = t2.mainid ) t1 "
//		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
//		                    +" group by  suosbk,suosgs ) t6 on t6.suosgs = t5.companyid ) t8";
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + Sql);
					LinkedHashMap<String, Object> pageResult = new LinkedHashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")"  + orderSqlT2 + ")) T2 ON T2." + key + "=T8." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) +   (CustomUtil.isBlank(blockid) ? "" :" AND (" + "suosbk="  + blockid + ")") 
			                           + " ORDER BY t8.suosbk asc";
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
			
				//获取是否监管小计01

				public Map<String, Object> dataGridJianG01() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql= "select * from ("
							+" select  ROWNUM id,t3.* from ("
							+ " select blockid,bankmc,companyid,subcompanyname,shifjgh,sum(yuanb)total_jiang from "
		                    +" ("+objectName+") "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  blockid,bankmc,companyid,subcompanyname,shifjgh "
		                    +" ) t3"
					        +" ) t1";
		                   
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + totalSql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					
					log.debug("listSql = " + 7);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
				
				
				public Map<String, Object> dataGridJinRjg01() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 100);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql="select  * from ("
						       +"  select  ROWNUM id, t3.shifjgh,t3.zhanghxzmc,t2.*,t4.bankmc,t5.subcompanyname from ( "
		                    +"   select t1.suosbk,t1.suosgs,  t1.zhanghxz  ,sum( t1.yuanb) as yuanb ,sum( t1.benwb) as benwb from  "
		                    +" "+objectName+" t1 "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  t1.suosbk,t1.suosgs, t1.zhanghxz "
		                    +" )t2 "
		                    + "left join uf_yeaccounttype t3 on t3.id = t2.zhanghxz "
		                    +" LEFT JOIN uf_bankuai t4 ON t4.id = t2.suosbk "
		                    +" LEFT JOIN hrmsubcompany t5 on t5.id = t2.suosgs "
		                    +") t6";
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + totalSql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql= Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T12 ON T12." + key + "=T6." + key
			                            + " WHERE T12.ROWNO>" + ((page - 1) * rows) + " AND T12.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
				
				
				//获取板块，公司，金融机构，分类和合计02

				public Map<String, Object> dataGridTotal02() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String bankid = getParameter("bankid");
//					String daiklxid = getParameter("daiklxid");
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					
					if (CustomUtil.isNotBlank(bankid)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "jinrjg="  + bankid + ")" ;
								
					}

//					bankid,jinrjgm
					String Sql=" select t8.* from ("
							+ " select  ROWNUM id,t6.* from ( "
							+"  select  jinrjg,jinrmc,suosgs,subcompanyname,sum(yuanb)total_yuanb,yewrq from  ("
		                    +"  select t2.jinrjg,t2.suosgs  ,t2.benwb yuanb ,t3.yewrq ,t4.jinrmc,t5.subcompanyname  from uf_accountyedj_dt1 t2 "
		                    +"  left join  uf_accountyedj t3 on t3.id = t2.mainid "
		                    +"  left join  uf_bankgs t4 on t4.id = t2.jinrjg  "
		                    +"  left join  hrmsubcompany t5 on t5.id = t2.suosgs "
		                    +"  ) t1 "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  jinrjg,suosgs,yewrq,jinrmc,subcompanyname ) t6 ) t8";
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + Sql);
					LinkedHashMap<String, Object> pageResult = new LinkedHashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")"  + orderSqlT2 + ")) T2 ON T2." + key + "=T8." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) +   (CustomUtil.isBlank(bankid) ? "" :" AND (" + "jinrjg="  + bankid + ")") 
			                           + " ORDER BY t8.jinrjg asc";
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
			
				//获取是否监管小计02  bankid,jinrjgmc

				public Map<String, Object> dataGridJianG02() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql= "select * from ("
							+" select  ROWNUM id,t3.* from ("
							+ " select bankid,jinrmc,companyid,subcompanyname,shifjgh,sum(yuanb)total_jiang from "
		                    +" ("+objectName+") "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  bankid,jinrmc,companyid,subcompanyname,shifjgh "
		                    +" ) t3"
					        +" ) t1";
		                   
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + totalSql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					
					log.debug("listSql = " + 7);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
				
				
				public Map<String, Object> dataGridJinRjg02() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 100);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql="select  * from ("
						       +"  select  ROWNUM id, t3.shifjgh,t3.zhanghxzmc,t2.*,t4.jinrmc,t5.subcompanyname from ( "
		                    +"   select t1.jinrjg,t1.suosgs,  t1.zhanghxz  ,sum( t1.yuanb) as yuanb ,sum( t1.benwb) as benwb from  "
		                    +" "+objectName+" t1 "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  t1.jinrjg,t1.suosgs, t1.zhanghxz "
		                    +" )t2 "
		                    + "left join uf_yeaccounttype t3 on t3.id = t2.zhanghxz "
		                    +" LEFT JOIN uf_bankgs t4 ON t4.id = t2.jinrjg "
		                    +" LEFT JOIN hrmsubcompany t5 on t5.id = t2.suosgs "
		                    +") t6";
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + totalSql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql= Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T12 ON T12." + key + "=T6." + key
			                            + " WHERE T12.ROWNO>" + ((page - 1) * rows) + " AND T12.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
				
				//余额字段查询
				public Map<String, Object> getSearchDataByBanlance() {
//					String year = getParameterCK("year");
					
					
					List<LoanBankJg> bankList = RecordUtil.searchAll(LoanBankJg.class);
					List<LoanBlock> blockList = RecordUtil.searchAll(LoanBlock.class);
					
					
					Map<String, Object> resultMap = new HashMap<String, Object>();
					resultMap.put("bankList", bankList);
					resultMap.put("blockList", blockList);
				
				
					
					return resultMap;
				}
				
				
				
				//领导获取板块，公司，金融机构，分类和合计03

				public Map<String, Object> dataGridTotal_block03() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String blockid = getParameter("blockid");
//					String daiklxid = getParameter("daiklxid");
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					
					if (CustomUtil.isNotBlank(blockid)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "suosbk="  + blockid + ")" ;
								
					}
					
					String Sql="  select  t2.id suosbk,t2.bankmc,t3.id suosgs ,t3.subcompanyname  from  uf_cashier t1 "
		                    +"  left join  uf_bankuai t2 on t2.id = t1.shuosbk   "
		                    +"  left join  hrmsubcompany t3 on t3.id = t1.gongsmc  "
		                    +   (CustomUtil.isBlank(blockid) ? "" :" where (" + "t1.shuosbk="  + blockid + ")")  +"  order by t1.shuosbk asc ";
		                   

					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + Sql);
					LinkedHashMap<String, Object> pageResult = new LinkedHashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")"  + orderSqlT2 + ")) T2 ON T2." + key + "=T8." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) +   (CustomUtil.isBlank(blockid) ? "" :" AND (" + "suosbk="  + blockid + ")") 
			                           + " ORDER BY t8.suosbk asc";
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(Sql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
							
    
				
				//领导获取板块是否可用余额小计

				public Map<String, Object> dataGridJianG_block03() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql= "select * from ("
							+" select  ROWNUM id,t3.* from ("
							+ " select blockid,bankmc,companyid,subcompanyname,sum(yuanb)total_jiang from "
		                    +" ("+objectName+") "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  blockid,bankmc,companyid,subcompanyname "
		                    +" ) t3"
					        +" ) t1";
		                   
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + totalSql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					
					log.debug("listSql = " + 7);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
				
				
				public Map<String, Object> dataGridTotal_bank03() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String bankid = getParameter("bankid");
//					String daiklxid = getParameter("daiklxid");
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					
					if (CustomUtil.isNotBlank(bankid)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + "jinrjg="  + bankid + ")" ;
								
					}
					
					String Sql="select jinrjg,jinrmc,suosgs,subcompanyname from ("
							+ "  select t1.jinrjg,t2.jinrmc,t3.id suosgs ,t3.subcompanyname from uf_account t1  "
		                    +"  left join  uf_bankgs t2 on t2.id = t1.jinrjg   "
		                    +"  left join  hrmsubcompany t3 on t3.id = t1.gongsmc  "
		                    +")"
		                    +   (CustomUtil.isBlank(bankid) ? "" :" where (" + "jinrjg="  + bankid + ")") 
		                    +" group by jinrjg,jinrmc,suosgs,subcompanyname"
		                    +"  order by jinrjg asc ";
		                   

					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + Sql);
					LinkedHashMap<String, Object> pageResult = new LinkedHashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")"  + orderSqlT2 + ")) T2 ON T2." + key + "=T8." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) +   (CustomUtil.isBlank(bankid) ? "" :" AND (" + "jinrjg="  + bankid + ")") 
			                           + " ORDER BY t8.jinrjg asc";
					

					log.debug("listSql = " + listSql);

					List<Map<String, String>> records = RecordUtil.executeQuery(Sql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
							
    
				
				//领导（看全部）获取可用余额和不可用小计

				public Map<String, Object> dataGridJianG_bank03() throws Exception {
					String objectName = getParameterCK("name");
					String field = getParameter("field");
					String key = getParameterCK("key");
					String sort = getParameter("sort");
					int page = CustomUtil.getInt(getParameterCK("page"), 1);
					int rows = CustomUtil.getInt(getParameterCK("rows"), 10);
					String q = getParameter("q");
					String filter = getParameter("filter");
					String defFilter = getParameter("defFilter");
					String startDate = getParameter("startDate", true);
					
					String orderSqlT2 = "";
					String orderSqlT1 = "";
					if (CustomUtil.isNotBlank(sort)) {
						orderSqlT2 = " ORDER BY " + sort;
						orderSqlT1 = " ORDER BY T1." + sort;
					}
					
					String[] fieldArr = null;
					if (CustomUtil.isBlank(field)) {
						field = "T1.*";
					} else {
						fieldArr = field.split(",");
						field = "T1." + fieldArr[0];
						for (int i = 1; i < fieldArr.length; i++) {
							field += ", T1." + fieldArr[i];
						}
					}
					
					String whereSql = "";
					if (CustomUtil.isNotBlank(defFilter)) {
						defFilter = defFilter.replace("{LIKE}", "LIKE");
						defFilter = defFilter.replace("{AND}", "AND");
						whereSql = " WHERE (" + defFilter + ")";
					}
					if (CustomUtil.isNotBlank(q) && CustomUtil.isNotBlank(filter)) {
						whereSql += (CustomUtil.isBlank(whereSql) ? " WHERE " : " AND ") + "(" + filter + " LIKE '%" + q + "%')";
					}
					

					String Sql= "select * from ("
							+" select  ROWNUM id,t3.* from ("
							+ " select bankid,jinrmc,companyid,subcompanyname,sum(yuanb)total_jiang from "
		                    +" ("+objectName+") "
		                    +" where (TO_DATE (yewrq , 'yyyy-MM-dd')=TO_DATE ("  + "\'"+startDate + "\'"+ ", 'yyyy-MM-dd')) "
		                    +" group by  bankid,jinrmc,companyid,subcompanyname "
		                    +" ) t3"
					        +" ) t1";
		                   
					
					String totalSql = "SELECT COUNT(1) FROM (" + Sql +")"+ whereSql;
					log.debug("totalSql = " + totalSql);
					Map<String, Object> pageResult = new HashMap<String, Object>();
					pageResult.put("total", RecordUtil.getInt(totalSql));
					
					String listSql=Sql + " INNER JOIN (SELECT ROWNUM ROWNO, " + key + " FROM (SELECT " + key + " FROM (" + Sql+")" + whereSql + orderSqlT2 + ")) T2 ON T2." + key + "=T1." + key
			                           + " WHERE T2.ROWNO>" + ((page - 1) * rows) + " AND T2.ROWNO<=" + (page * rows) + (CustomUtil.isBlank(defFilter) ? "" : " AND (" + defFilter + ")");
					              
					
					log.debug("listSql = " + 7);

					List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
					pageResult.put("rows", records);
					
					return pageResult;
				}
}
