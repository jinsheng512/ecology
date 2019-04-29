package ecustom.dao;

import weaver.conn.RecordSet;
import ecustom.entities.HrmDepartment;

public class HrmDepartmentDao {

	public HrmDepartment getByCode(String code) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT ID, DEPARTMENTNAME, DEPARTMENTMARK, DEPARTMENTCODE"
				+ " FROM HRMDEPARTMENT WHERE DEPARTMENTCODE=?";
		if (rs.executeQuery(sql, code) && rs.next()) {
			HrmDepartment entity = new HrmDepartment();
			entity.setId(rs.getInt("ID"));
			entity.setDepartmentMark(rs.getString("DEPARTMENTMARK"));
			entity.setDepartmentCode(rs.getString("DEPARTMENTCODE"));
			return entity;
		}
		return null;
	}

	public HrmDepartment getById(int id) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT ID, DEPARTMENTNAME, DEPARTMENTMARK, DEPARTMENTCODE"
				+ " FROM HRMDEPARTMENT WHERE ID=?";
		if (rs.executeQuery(sql, id) && rs.next()) {
			HrmDepartment entity = new HrmDepartment();
			entity.setId(rs.getInt("ID"));
			entity.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			entity.setDepartmentMark(rs.getString("DEPARTMENTMARK"));
			entity.setDepartmentCode(rs.getString("DEPARTMENTCODE"));
			return entity;
		}
		return null;
	}
}
