package greedc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weaver.conn.RecordSet;
import ecustom.Log;

public class GreedcProExpBudgetService {
	
	private static final Log log = new Log(GreedcProExpBudgetService.class);

	public List<Map<String, String>> listGoux(int requestId) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT T1.PROJECT_ID, T2.BIANMXH, T2.GOUX, T2.HETBM"
				+ " FROM GREEDC_PRO_EXP_BUDGET T1"
				+ " INNER JOIN GREEDC_EB_PRO_ENTRY T2 ON T2.MAIN_SEQ_NO=T1.SEQ_NO"
				+ " WHERE T1.REQUEST_ID=?";
		List<Map<String, String>> rows = new ArrayList<Map<String,String>>();
		
		log.debug(sql + ", " + requestId);
		
		if (rs.executeQuery(sql, requestId)) {
			while (rs.next()) {
				Map<String, String> row = new HashMap<String, String>();
				row.put("PROJECT_ID", rs.getString("PROJECT_ID"));
				row.put("BIANMXH", rs.getString("BIANMXH"));
				row.put("GOUX", rs.getString("GOUX"));
				row.put("HETBM", rs.getString("HETBM"));
				rows.add(row);
			}
		}
		return rows;
	}
}
