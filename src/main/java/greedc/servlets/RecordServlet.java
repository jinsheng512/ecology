package greedc.servlets;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.servlets.BaseServlet;
import ecustom.util.CustomUtil;

public class RecordServlet extends BaseServlet {
	
	private static final Logger log = Logger.getLogger(RecordServlet.class);

	public Object listAll() throws Exception {
		String objectName = getParameterCK("name");
		String field = getParameter("field");
		String sort = getParameter("sort");
		String defFilter = getParameter("defFilter");
		
		String[] fieldArr = field.replace(" ", "").split(",");
		field = "T1." + fieldArr[0];
		for (int i = 1; i < fieldArr.length; i++) {
			field += ", T1." + fieldArr[i];
		}
		
		sort = CustomUtil.isNotBlank(sort) ? (" ORDER BY " + sort) : "";
		
		String whereSql = "";
		if (CustomUtil.isNotBlank(defFilter)) {
			defFilter = defFilter.replace("{LIKE}", "LIKE");
			whereSql = " WHERE (" + defFilter + ")";
		}
		
		String listSql = "SELECT " + field
				+ " FROM " + objectName + " T1 " + whereSql + sort;
		log.debug("listSql = " + listSql);
		List<Map<String, String>> records = RecordUtil.executeQuery(listSql, fieldArr);

		return records;
	}
}
