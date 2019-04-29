package ecustom.dao;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

/**
 * 专业数据访问类。
 * @author William
 */
public class HrmSpecialityDao {

	public int getByName(String name) {
		String sql = "SELECT ID FROM HrmSpeciality WHERE NAME=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, name) && rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}
	
	public int save(String name, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO HRMSPECIALITY(NAME, DESCRIPTION) VALUES(?, ?)";
		rst.executeUpdate(sql, name, name);
		return getByName(name);
	}
}
