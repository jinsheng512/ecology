package greedc.dao;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;

public class HrmResourceDao {

	public List<Integer> getDeptIdsByBelong(int userId) {
		List<Integer> ids = new ArrayList<Integer>();
		String sql = "SELECT DISTINCT DEPARTMENTID FROM HRMRESOURCE WHERE BELONGTO=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, userId) && rs.next()) {
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
		}
		return ids;
	}
	
	public int getCountByDept(int deptId) {
		String sql = "SELECT COUNT(1) FROM HRMRESOURCE"
				+ " WHERE LENGTH(LOGINID)>0 AND STATUS<>5 AND DEPARTMENTID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, deptId) && rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
}
