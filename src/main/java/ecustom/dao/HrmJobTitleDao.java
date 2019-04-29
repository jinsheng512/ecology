package ecustom.dao;

import weaver.conn.RecordSet;
import ecustom.entities.HrmJobTitle;

public class HrmJobTitleDao {

	public HrmJobTitle getByCode(String code) {
		String sql = "SELECT ID, JOBTITLEMARK, JOBTITLENAME, JOBTITLECODE FROM HRMJOBTITLES WHERE JOBTITLECODE=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, code) && rs.next()) {
			HrmJobTitle v = new HrmJobTitle();
			v.setId(rs.getInt("ID"));
			v.setJobTitleMark(rs.getString("JOBTITLEMARK"));
			v.setJobTitleName(rs.getString("JOBTITLENAME"));
			v.setJobTitleCode(rs.getString("JOBTITLECODE"));
			return v;
		}
		return null;
	}

	public HrmJobTitle getById(int id) {
		String sql = "SELECT ID, JOBTITLEMARK, JOBTITLENAME, JOBTITLECODE FROM HRMJOBTITLES WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, id) && rs.next()) {
			HrmJobTitle v = new HrmJobTitle();
			v.setId(rs.getInt("ID"));
			v.setJobTitleMark(rs.getString("JOBTITLEMARK"));
			v.setJobTitleName(rs.getString("JOBTITLENAME"));
			v.setJobTitleCode(rs.getString("JOBTITLECODE"));
			return v;
		}
		return null;
	}
}
