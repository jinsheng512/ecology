package greedc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import greedc.GreedcUtil;
import greedc.entities.PersonIndex;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

public class PersonIndexDao extends BaseDao {

	public boolean exists(int id, int year, int season) {
		String sql = "SELECT ID FROM UF_PERSONINDEX WHERE BEIKHR=? AND KAOHD=? AND YEAR=?";
		RecordSet rs = new RecordSet();
		return rs.executeQuery(sql, id, season, year) && rs.next();
	}

	public boolean save(PersonIndex index, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO UF_PERSONINDEX(BEIKHR, BUMFZR, KAOHD, FAYQ, CHENGJ, GONGZYJ, XINGWPS, ZONGTPS, STATUS, YWZBZHJ,"
				+ " XWNLZHJ, YWZBSHJ, XWNLSHJ, YEAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean flag = rst.executeUpdate(sql, index.getBeikhr(), index.getBumfzr(), 
				index.getSeason(), index.getFazyq(), index.getChengj(), index.getGongzyj(), index.getXingwps(),
				index.getZongtps(), index.getStatus(), index.getYwzbzhj(), index.getXwnlzhj(), index.getYwzbshj(),
				index.getXwnlshj(), index.getYear());
		index.setId(super.getCurrId("UF_PERSONINDEX_ID", rst));
		return flag;
	}

	/**
	 * 查询个人绩效主表。
	 * @return
	 */
	public PersonIndex getPersonIndex(int userId, int year, int season) {
		String sql = "SELECT T1.ID, T1.REQUESTID, T1.BEIKHR, T1.BUMFZR, R.DEPARTMENTID, T1.KAOHD, T1.FAYQ, T1.CHENGJ, T1.GONGZYJ,"
				+ " T1.XINGWPS, T1.ZONGTPS, T1.STATUS, T1.YWZBZHJ, T1.XWNLZHJ, T1.YWZBSHJ, T1.XWNLSHJ,"
				+ " T2.SELECTVALUE, T2.SELECTNAME"
				+ " FROM UF_PERSONINDEX T1"
				+ " INNER JOIN HRMRESOURCE R ON R.ID=T1.BEIKHR"
				+ " LEFT JOIN WORKFLOW_SELECTITEM T2 ON T2.SELECTVALUE=T1.YOULPD AND T2.FIELDID=8726"
				+ " WHERE BEIKHR=? AND KAOHD=? AND YEAR=?";
		RecordSet rs = new RecordSet();
		PersonIndex index = null;
		if (rs.executeQuery(sql, userId, season, year) && rs.next()) {
			index = new PersonIndex();
			index.setId(GreedcUtil.getInteger(rs.getString("ID")));
			index.setBeikhr(GreedcUtil.getInteger(rs.getString("BEIKHR")));
			index.setBumfzr(GreedcUtil.getInteger(rs.getString("BUMFZR")));
			index.setDeptId(GreedcUtil.getInteger(rs.getString("DEPARTMENTID")));
			index.setSeason(GreedcUtil.getInteger(rs.getString("KAOHD")));
			index.setFazyq(rs.getString("FAYQ"));
			index.setChengj(GreedcUtil.getDouble(rs.getString("CHENGJ")));
			index.setGongzyj(rs.getString("GONGZYJ"));
			index.setXingwps(rs.getString("XINGWPS"));
			index.setZongtps(rs.getString("ZONGTPS"));
			index.setStatus(GreedcUtil.getInteger(rs.getString("STATUS")));
			index.setYwzbzhj(GreedcUtil.getDouble(rs.getString("YWZBZHJ")));
			index.setXwnlzhj(GreedcUtil.getDouble(rs.getString("XWNLZHJ")));
			index.setYwzbshj(GreedcUtil.getDouble(rs.getString("YWZBSHJ")));
			index.setXwnlshj(GreedcUtil.getDouble(rs.getString("XWNLSHJ")));
			index.setYoulpd(GreedcUtil.getInteger(rs.getString("SELECTVALUE")));
			index.setYoulpdName(rs.getString("SELECTNAME"));
		}
		return index;
	}
	
	/**
	 * 加载需要部门负责人审核的个人绩效考核记录。
	 * @return
	 */
	public List<PersonIndex> listBumfzrAppr(int bumfzr, int year, int season) {
		List<PersonIndex> list = new ArrayList<PersonIndex>();
		String sql = "SELECT BEIKHR, T2.LASTNAME BEIKHRNAME, T2.DEPARTMENTID, TRUNC(YWZBZHJ*0.7) YWZBZHJ, XWNLZHJ,"
				+ " CHENGJZP, TRUNC(YWZBSHJ*0.7) YWZBSHJ, XWNLSHJ, CHENGJ, YOULPD"
				+ " FROM UF_PERSONINDEX T1 INNER JOIN HRMRESOURCE T2 ON T2.ID=T1.BEIKHR"
				+ " WHERE T1.STATUS=1 AND BUMFZR=? AND KAOHD=? AND YEAR=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, bumfzr, season, year)) {
			while (rs.next()) {
				PersonIndex index = new PersonIndex();
				index.setBeikhr(GreedcUtil.getInteger(rs.getString("BEIKHR")));
				index.setBeikhrName(rs.getString("BEIKHRNAME"));
				index.setYwzbzhj(GreedcUtil.getDouble(rs.getString("YWZBZHJ")));
				index.setXwnlzhj(GreedcUtil.getDouble(rs.getString("XWNLZHJ")));
				index.setChengjzp(GreedcUtil.getDouble(rs.getString("CHENGJZP")));
				index.setYwzbshj(GreedcUtil.getDouble(rs.getString("YWZBSHJ")));
				index.setXwnlshj(GreedcUtil.getDouble(rs.getString("XWNLSHJ")));
				index.setChengj(GreedcUtil.getDouble(rs.getString("CHENGJ")));
				index.setYoulpd(GreedcUtil.getInteger(rs.getString("YOULPD")));
				index.setDeptId(GreedcUtil.getInteger(rs.getString("DEPARTMENTID")));
				list.add(index);
			}
		}
		return list;
	}

	public void updateSelfMark(PersonIndex index, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE UF_PERSONINDEX SET FAYQ=?, YWZBZHJ=?, XWNLZHJ=?, CHENGJZP=?"
				+ " WHERE ID=?";
		rst.executeUpdate(sql, index.getFazyq(), index.getYwzbzhj(), index.getXwnlzhj(), index.getChengjzp(),
				index.getId());
	}

	public void setStatus(int id, int status) throws SQLException {
		String sql = "UPDATE UF_PERSONINDEX SET STATUS=? ,GONGZYJ=?,XINGWPS=? ,ZONGTPS=?,CHENGJ=?,YWZBSHJ=?,XWNLSHJ=? WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (!rs.executeUpdate(sql, status,"","","",0,0,0, id)) {
			throw new SQLException("更新单据状态失败");
		}
	}
	
	public void setClearUF_PERSONINDEX_DT2(int id) throws SQLException {
		String sql = "UPDATE UF_PERSONINDEX_DT2 SET SHENDPF=? WHERE MAINID=?";
		RecordSet rs = new RecordSet();
		if (!rs.executeUpdate(sql, "", id)) {
			throw new SQLException("更新行为能力指标明细表2失败");
		}
	}
	
	public void setClearUF_PERSONINDEX_DT1(int id) throws SQLException {
		String sql = "UPDATE UF_PERSONINDEX_DT1 SET SHENDPF=? WHERE MAINID=?";
		RecordSet rs = new RecordSet();
		if (!rs.executeUpdate(sql, "", id)) {
			throw new SQLException("更新关键指标明细表失败");
		}
	}

	public void updateManagerMark(PersonIndex index, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE UF_PERSONINDEX SET GONGZYJ=?, XINGWPS=?, YWZBSHJ=?, XWNLSHJ=?, CHENGJ=?, ZONGTPS=?,"
				+ " YOULPD=? WHERE ID=?";
		rst.executeUpdate(sql, index.getGongzyj(), index.getXingwps(), index.getYwzbshj(), index.getXwnlshj(),
				index.getChengj(), index.getZongtps(), index.getYoulpd(), index.getId());
	}

	public List<PersonIndex> listDeptAppr(int deptId, int year, int season) {
		List<PersonIndex> list = new ArrayList<PersonIndex>();
		String sql = "SELECT BEIKHR, T2.LASTNAME BEIKHRNAME, T2.DEPARTMENTID, TRUNC(YWZBZHJ*0.7) YWZBZHJ, XWNLZHJ, CHENGJZP,"
				+ " TRUNC(YWZBSHJ*0.7) YWZBSHJ, XWNLSHJ, CHENGJ, YOULPD FROM UF_PERSONINDEX T1"
				+ " INNER JOIN HRMRESOURCE T2 ON T2.ID=T1.BEIKHR WHERE T1.STATUS=1 AND T2.DEPARTMENTID=? AND KAOHD=? AND YEAR=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, deptId, season, year)) {
			while (rs.next()) {
				PersonIndex index = new PersonIndex();
				index.setBeikhr(GreedcUtil.getInteger(rs.getString("BEIKHR")));
				index.setBeikhrName(rs.getString("BEIKHRNAME"));
				index.setYwzbzhj(GreedcUtil.getDouble(rs.getString("YWZBZHJ")));
				index.setXwnlzhj(GreedcUtil.getDouble(rs.getString("XWNLZHJ")));
				index.setChengjzp(GreedcUtil.getDouble(rs.getString("CHENGJZP")));
				index.setYwzbshj(GreedcUtil.getDouble(rs.getString("YWZBSHJ")));
				index.setXwnlshj(GreedcUtil.getDouble(rs.getString("XWNLSHJ")));
				index.setChengj(GreedcUtil.getDouble(rs.getString("CHENGJ")));
				index.setYoulpd(GreedcUtil.getInteger(rs.getString("YOULPD")));
				index.setDeptId(GreedcUtil.getInteger(rs.getString("DEPARTMENTID")));
				list.add(index);
			}
		}
		return list;
	}

}
