package ecustom.dao;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import ecustom.entities.WorkflowBillField;

public class WorkflowBillFieldDao {

	public List<WorkflowBillField> getByFormId(int formId) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT ID, FIELDNAME, FIELDDBTYPE, DETAILTABLE"
				+ " FROM WORKFLOW_BILLFIELD WHERE BILLID=?";
		List<WorkflowBillField> rows = new ArrayList<WorkflowBillField>();
		if (rs.executeQuery(sql, formId)) {
			while (rs.next()) {
				WorkflowBillField row = new WorkflowBillField();
				row.setId(rs.getInt("ID"));
				row.setFieldName(rs.getString("FIELDNAME"));
				row.setFieldDbType(rs.getString("FIELDDBTYPE"));
				row.setDetailTable(rs.getString("DETAILTABLE"));
				rows.add(row);
			}
		}
		return rows;
	}
}
