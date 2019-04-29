package greedc.dao;

import greedc.entities.UFIndexAssign;
import weaver.conn.RecordSet;

public class UFIndexAssignDao {

	/**
	 * 判断是否有部门指标。
	 * @param deptId
	 * @return
	 */
	public boolean hasDeptAssign(int deptId) {
		String sql = "SELECT 1 FROM UF_INDEXASSIGN WHERE DEPT=? AND ASSLEVEL=1";
		RecordSet rs = new RecordSet();
		return rs.executeQuery(sql, deptId) && rs.next();
	}
	
	/**
	 * 获取部门指标分配记录。
	 * @param deptId
	 * @return
	 */
	public UFIndexAssign getDeptAssign(int deptId) {
		String sql = "SELECT ID,COMPANY,DEPT,ASSLEVEL,INDEX1,INDEX2,INDEX3,INDEX4,INDEX5,INDEX6"
				+ " FROM UF_INDEXASSIGN WHERE DEPT=? AND ASSLEVEL=1";
		return getAssign(sql, deptId);
	}
	
	/**
	 * 获取公司指标分配记录。
	 * @param companyId
	 * @return
	 */
	public UFIndexAssign getCompanyAssign(int companyId) {
		String sql = "SELECT ID,COMPANY,DEPT,ASSLEVEL,INDEX1,INDEX2,INDEX3,INDEX4,INDEX5,INDEX6"
				+ " FROM UF_INDEXASSIGN WHERE COMPANY=? AND ASSLEVEL=0";
		return getAssign(sql, companyId);
	}
	
	/**
	 * 获取指标分配记录。
	 * @param sql
	 * @param id	部门ID/公司ID
	 * @return
	 */
	public UFIndexAssign getAssign(String sql, int id) {
		UFIndexAssign entry = null;
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, id) && rs.next()) {
			entry = new UFIndexAssign();
			entry.setId(rs.getInt("ID"));
			entry.setCompany(rs.getInt("COMPANY"));
			entry.setDept(rs.getInt("DEPT"));
			entry.setAssLevel(rs.getInt("ASSLEVEL"));
			entry.setIndex1(rs.getInt("INDEX1"));
			entry.setIndex2(rs.getInt("INDEX2"));
			entry.setIndex3(rs.getInt("INDEX3"));
			entry.setIndex4(rs.getInt("INDEX4"));
			entry.setIndex5(rs.getInt("INDEX5"));
			entry.setIndex6(rs.getInt("INDEX6"));
		}
		return entry;
	}
}
