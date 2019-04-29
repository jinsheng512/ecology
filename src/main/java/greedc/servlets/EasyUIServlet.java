package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.util.CustomUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ecustom.ecology8.commons.RecordUtil;

public class EasyUIServlet extends BaseServlet {

	private final static Logger log = Logger.getLogger(EasyUIServlet.class);

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
	
	public List<Map<String, String>> dataGrid() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String sort = getParameter("sort");
		String q = getParameter("q");
		String filter = getParameter("filter");
		String defFilter = getParameter("defFilter");
		
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
		log.debug("listSql = " + listSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);
		
		return records;
	}
}
