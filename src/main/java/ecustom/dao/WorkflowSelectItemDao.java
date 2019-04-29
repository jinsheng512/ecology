package ecustom.dao;

import weaver.conn.RecordSet;

/**
 * Created by William on 2017-6-7.
 */
public class WorkflowSelectItemDao {

    /**
     * 根据选项文本获取选项值。
     * @param fieldId
     * @param selectName
     * @return  SelectValue 或者 -1
     */
    public int getSelectValue(int fieldId, String selectName) {
        String sql = "SELECT SELECTVALUE FROM WORKFLOW_SELECTITEM" +
                " WHERE FIELDID=? AND SELECTNAME=?";
        RecordSet rs = new RecordSet();
        if (rs.executeQuery(sql, fieldId, selectName) && rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

	/**
	 * 根据选项值获取选项文本。
	 * @param fieldId
	 * @param selectValue
	 * @return  selectName 或者 null
	 */
	public String getSelectName(int fieldId, String selectValue) {
		String sql = "SELECT SELECTNAME FROM WORKFLOW_SELECTITEM" +
				" WHERE FIELDID=? AND SELECTVALUE=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, fieldId, selectValue) && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}
}
