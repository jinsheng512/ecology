package ecustom.dao;

import java.util.HashMap;
import java.util.Map;

import weaver.conn.RecordSet;

public class WorkflowRequestBaseDao {

	public Map<String, Object> getByRequestId(int requestId) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT T1.REQUESTID, T1.WORKFLOWID, T1.REQUESTNAME,"
				+ " T1.CREATER, T2.FORMID FROM WORKFLOW_REQUESTBASE T1"
				+ " INNER JOIN WORKFLOW_BASE T2 ON T2.ID=T1.WORKFLOWID"
				+ " WHERE T1.REQUESTID=?";
		Map<String, Object> data = new HashMap<String, Object>();
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			data.put("REQUESTID", rs.getInt("REQUESTID"));
			data.put("WORKFLOWID", rs.getInt("WORKFLOWID"));
			data.put("REQUESTNAME", rs.getString("REQUESTNAME"));
			data.put("CREATER", rs.getInt("CREATER"));
			data.put("FORMID", rs.getInt("FORMID"));
		}
		return data;
	}
}
