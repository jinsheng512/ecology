package ecustom.dao;

import weaver.conn.RecordSet;
import ecustom.entities.HrmSubCompany;

public class HrmSubCompanyDao {

	public HrmSubCompany getByCode(String code) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT ID, SUBCOMPANYNAME, SUBCOMPANYDESC, SUBCOMPANYCODE"
				+ " FROM HRMSUBCOMPANY WHERE SUBCOMPANYCODE=?";
		if (rs.executeQuery(sql, code) && rs.next()) {
			HrmSubCompany entity = new HrmSubCompany();
			entity.setId(rs.getInt("ID"));
			entity.setSubCompanyName(rs.getString("SUBCOMPANYNAME"));
			entity.setSubCompanyDesc(rs.getString("SUBCOMPANYDESC"));
			entity.setSubCompanyCode(rs.getString("SUBCOMPANYCODE"));
			return entity;
		}
		return null;
	}

	public HrmSubCompany getById(int id) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT ID, SUBCOMPANYNAME, SUBCOMPANYDESC, SUBCOMPANYCODE"
				+ " FROM HRMSUBCOMPANY WHERE ID=?";
		if (rs.executeQuery(sql, id) && rs.next()) {
			HrmSubCompany entity = new HrmSubCompany();
			entity.setId(rs.getInt("ID"));
			entity.setSubCompanyName(rs.getString("SUBCOMPANYNAME"));
			entity.setSubCompanyDesc(rs.getString("SUBCOMPANYDESC"));
			entity.setSubCompanyCode(rs.getString("SUBCOMPANYCODE"));
			return entity;
		}
		return null;
	}
	
	public Integer getIdByName(String name) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT ID FROM HRMSUBCOMPANY" +
				" WHERE TRIM(SUBCOMPANYDESC)=? OR TRIM(SUBCOMPANYNAME)=?";
		if (rs.executeQuery(sql, name.trim(), name.trim()) && rs.next()) {
			return rs.getInt("ID");
		}
		return null;
	}
}
