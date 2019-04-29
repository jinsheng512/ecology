package ecustom.dao;

import ecustom.entities.EcstWorkflowBase;
import ecustom.entities.WorkflowBase;
import weaver.conn.RecordSet;

public class WorkflowBaseDao {

	/**
	 * 根据WorkflowId获取Workflow名称。
	 * @return
	 */
	public String getNameById(int id) {
		String workflowName = null;
		RecordSet rs = new RecordSet();
		String sql = "SELECT WORKFLOWNAME FROM WORKFLOW_BASE WHERE ID=?";
		if (rs.executeQuery(sql, id) && rs.next()) {
			workflowName = rs.getString(1);
		}
		return workflowName;
	}
	
	public EcstWorkflowBase getECSTFlowBaseByCode(String workflowCode) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT WORKFLOW_ID, REQUEST_NAME FROM ECST_WORKFLOW_BASE WHERE WORKFLOW_CODE=?";
		if (rs.executeQuery(sql, workflowCode) && rs.next()) {
			EcstWorkflowBase v = new EcstWorkflowBase();
			v.setId(rs.getInt("WORKFLOW_ID"));
			v.setRequestName(rs.getString("REQUEST_NAME"));
			return v;
		}
		return null;
	}
	
	public EcstWorkflowBase getECSTFlowBaseById(int id) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT T2.ID, T1.REQUEST_NAME, T2.WORKFLOWNAME FROM ECST_WORKFLOW_BASE T1"
				+ " INNER JOIN WORKFLOW_BASE T2 ON T2.ID=T1.WORKFLOW_ID WHERE T2.ID=?";
		if (rs.executeQuery(sql, id) && rs.next()) {
			EcstWorkflowBase v = new EcstWorkflowBase();
			v.setId(rs.getInt("WORKFLOW_ID"));
			v.setWorkflowName(rs.getString("WORKFLOWNAME"));
			v.setRequestName(rs.getString("REQUEST_NAME"));
			return v;
		}
		return null;
	}
	
	public String getDocCategoryById(int id) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT DOCCATEGORY FROM WORKFLOW_BASE WHERE ID=?";
		if (rs.executeQuery(sql, id) && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
	
	public WorkflowBase getById(int id) {
		String sql = "SELECT ID, WORKFLOWNAME, WORKFLOWTYPE, FORMID FROM WORKFLOW_BASE WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, id) && rs.next()) {
			WorkflowBase base = new WorkflowBase();
			base.setId(rs.getInt("ID"));
			base.setWorkflowName(rs.getString("WORKFLOWNAME"));
			base.setWorkflowType(rs.getInt("WORKFLOWTYPE"));
			base.setFormId(rs.getInt("FORMID"));
			return base;
		}
		return null;
	}
	
	public WorkflowBase getByRequestId(int requestId) {
		String sql = "SELECT ID, WORKFLOWNAME, WORKFLOWTYPE, FORMID FROM WORKFLOW_BASE WB"
				+ " INNER JOIN WORKFLOW_REQUESTBASE WR ON WR.WORKFLOWID=WB.ID"
				+ " WHERE WR.REQUESTID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			WorkflowBase base = new WorkflowBase();
			base.setId(rs.getInt("ID"));
			base.setWorkflowName(rs.getString("WORKFLOWNAME"));
			base.setWorkflowType(rs.getInt("WORKFLOWTYPE"));
			base.setFormId(rs.getInt("FORMID"));
			return base;
		}
		return null;
	}
}
