package ecustom.dao;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import ecustom.vo.GenerateDescriptionBaseVo;
import ecustom.vo.GenerateDescriptionFieldVo;

public class GenerateDescriptionDao {

	public List<GenerateDescriptionFieldVo> getFields(int workflowId) {
		String sql = "SELECT T1.ID, T1.FIELDNAME, T1.DETAILTABLE, T2.INDEXDESC, "
				+ " T3.PARAM_LABLE_NAME, T3.PARAM_DESC, T3.REMARKS, NVL(T3.IS_ACTIVE, 1), ORDER_BY"
				+ " FROM WORKFLOW_BASE WB"
				+ " INNER JOIN WORKFLOW_BILLFIELD T1 ON T1.BILLID=WB.FORMID"
				+ " INNER JOIN HTMLLABELINDEX T2 ON T2.ID=T1.FIELDLABEL"
				+ " LEFT JOIN ECSTM_WF_GENERATE_FIELD T3 ON T3.FIELDID=T1.ID"
				+ " WHERE NVL(T3.IS_ACTIVE, 1)=1 AND WB.ID=?";
		List<GenerateDescriptionFieldVo> vs = new ArrayList<GenerateDescriptionFieldVo>();
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, workflowId)) {
			while (rs.next()) {
				GenerateDescriptionFieldVo v = new GenerateDescriptionFieldVo();
				v.setFieldId(rs.getInt("ID"));
				v.setFieldName(rs.getString("FIELDNAME"));
				v.setTableName(rs.getString("DETAILTABLE"));
				v.setLabelName(rs.getString("INDEXDESC"));
				v.setParamLabelName(rs.getString("PARAM_LABLE_NAME"));
				v.setParamDesc(rs.getString("PARAM_DESC"));
				v.setRemarks(rs.getString("REMARKS"));
				v.setRemarks(rs.getString("ORDER_BY"));
				vs.add(v);
			}
		}
		return vs;
	}

	public GenerateDescriptionBaseVo getBase(int workflowId) {
		String sql = "SELECT T1.ID, T1.WORKFLOWNAME, T1.FORMID, T4.INDEXDESC FORMNAME, T3.TYPENAME"
				+ " FROM WORKFLOW_BASE T1"
				+ " INNER JOIN WORKFLOW_BILL T2 ON T2.ID=T1.FORMID"
				+ " INNER JOIN WORKFLOW_TYPE T3 ON T3.ID=T1.WORKFLOWTYPE"
				+ " INNER JOIN HTMLLABELINDEX T4 ON T4.ID=T2.NAMELABEL"
				+ " WHERE T1.ID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql) && rs.next()) {
			GenerateDescriptionBaseVo v = new GenerateDescriptionBaseVo();
			v.setWorkflowId(rs.getInt("ID"));
			v.setWorkflowName(rs.getString("WORKFLOWNAME"));
			v.setWorkflowTypeName(rs.getString("TYPENAME"));
			v.setFormId(rs.getInt("FORMID"));
			v.setFormName(rs.getString("FORMNAME"));
			return v;
		}
		return null;
	}
}
