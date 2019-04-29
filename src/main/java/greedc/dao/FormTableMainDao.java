package greedc.dao;

import weaver.conn.RecordSet;

public class FormTableMainDao {

	public String getBillCode(int requestId, String tableName, String fieldName) {
		String sql = "SELECT " + fieldName + " FROM " + tableName + " WHERE REQUESTID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
}
