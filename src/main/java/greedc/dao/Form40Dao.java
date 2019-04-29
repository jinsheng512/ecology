package greedc.dao;

import ecustom.util.CustomUtil;
import weaver.conn.RecordSet;
import weaver.file.Prop;

public class Form40Dao {

	public static final int YEAR_ID = CustomUtil.getInt(Prop.getPropValue("ecustom", "jdpf.pfform.yearid"), 9708);

	/**
	 * 检查部门负责人考核流程是否存在。
	 * @param deptId
	 * @param year
	 * @param season
	 * @return
	 */
	public boolean checkExists(int deptId, int year, int season) {
		String sql = "SELECT COUNT(1) FROM FORMTABLE_MAIN_40 T1"
				+ " INNER JOIN WORKFLOW_SELECTITEM T2 ON T2.SELECTVALUE=T1.YEAR AND T2.FIELDID=" + Form40Dao.YEAR_ID
				+ " WHERE T1.BUMMC=? AND T1.KAOJD=? AND T2.SELECTNAME=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, deptId, season, year + "") && rs.next()) {
			return rs.getInt(1) > 0;
		}
		return false;
	}
}
