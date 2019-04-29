package ecustom.dao;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import ecustom.entities.HrmResource;

/**
 * 人员信息数据访问类。
 * @author William
 */
public class HrmResourceDao {
	
	/**
	 * 根据登录名修改密码。
	 * @param loginId	登录名
	 * @param password	密码（MD5格式）
	 * @throws Exception 
	 */
	public void changePassword(String loginId, String password, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE HRMRESOURCE SET PASSWORD=? WHERE LOGINID=?";
		rst.executeUpdate(sql, password, loginId);
	}

	public HrmResource getByLoginId(String loginId) {
		String sql = "SELECT ID, LASTNAME, LOGINID, PASSWORD, DEPARTMENTID, SUBCOMPANYID1, JOBTITLE FROM HRMRESOURCE WHERE LOGINID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, loginId) && rs.next()) {
			HrmResource vo = new HrmResource();
			vo.setId(rs.getInt("ID"));
			vo.setLastName(rs.getString("LASTNAME"));
			vo.setLoginId(rs.getString("LOGINID"));
			vo.setPassword(rs.getString("PASSWORD"));
			vo.setDepartmentId(rs.getInt("DEPARTMENTID"));
			vo.setSubCompanyId(rs.getInt("SUBCOMPANYID1"));
			vo.setJobTitle(rs.getInt("JOBTITLE"));
			return vo;
		}
		return null;
	}

	public HrmResource getById(int id) {
		String sql = "SELECT ID, LASTNAME, LOGINID, PASSWORD, DEPARTMENTID, SUBCOMPANYID1,"
				+ " JOBTITLE, WORKCODE, EMAIL FROM HRMRESOURCE WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, id) && rs.next()) {
			HrmResource vo = new HrmResource();
			vo.setId(rs.getInt("ID"));
			vo.setLastName(rs.getString("LASTNAME"));
			vo.setLoginId(rs.getString("LOGINID"));
			vo.setPassword(rs.getString("PASSWORD"));
			vo.setDepartmentId(rs.getInt("DEPARTMENTID"));
			vo.setSubCompanyId(rs.getInt("SUBCOMPANYID1"));
			vo.setJobTitle(rs.getInt("JOBTITLE"));
			vo.setWorkCode(rs.getString("WORKCODE"));
			vo.setEmail(rs.getString("EMAIL"));
			return vo;
		}
		return null;
	}

	public HrmResource getByWorkCode(String workCode) {
		String sql = "SELECT ID, LASTNAME, LOGINID, PASSWORD, DEPARTMENTID, SUBCOMPANYID1, JOBTITLE FROM HRMRESOURCE WHERE WORKCODE='" + workCode + "'";
		RecordSet rs = new RecordSet();
		if (rs.execute(sql) && rs.next()) {
			HrmResource vo = new HrmResource();
			vo.setId(rs.getInt("ID"));
			vo.setLastName(rs.getString("LASTNAME"));
			vo.setLoginId(rs.getString("LOGINID"));
			vo.setPassword(rs.getString("PASSWORD"));
			vo.setDepartmentId(rs.getInt("DEPARTMENTID"));
			vo.setSubCompanyId(rs.getInt("SUBCOMPANYID1"));
			vo.setJobTitle(rs.getInt("JOBTITLE"));
			return vo;
		}
		return null;
	}

	public String getWorkCodeById(int id) {
		String sql = "SELECT WORKCODE FROM HRMRESOURCE WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, id) && rs.next()) {
			return rs.getString(1);
		}
		return null;
	}

	public Integer getIdByWorkcode(String workCode) {
		String sql = "SELECT ID FROM HRMRESOURCE WHERE WORKCODE=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, workCode) && rs.next()) {
			return rs.getInt(1);
		}
		return null;
	}
}
