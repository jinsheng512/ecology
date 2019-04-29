package ecustom.workflow.dao;

import weaver.conn.RecordSet;

public class CurrentOperatorDao {

	/**
	 * 获取已经流转过的节点数量，含待办节点。
	 * @param requestId
	 * @return
	 */
	public int getFlowedNode(int requestId) {
		String sql = "SELECT COUNT(DISTINCT NODEID) FLOWEDNODE FROM"
				+ " WORKFLOW_CURRENTOPERATOR WHERE REQUESTID=" + requestId
				+ " GROUP BY REQUESTID";
		RecordSet rs = new RecordSet();
		if (rs.execute(sql) && rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
}
