package ecustom.dao;

import java.sql.SQLException;

import ecustom.entities.SysPoppupRemindInfoNew;
import weaver.conn.RecordSet;

public class SysPoppupRemindInfoNewDao {

	/**
	 * 删除一条提醒。
	 * @param userId
	 * @param typeId
	 * @throws SQLException 
	 */
	public void delete(int userId, int type) throws SQLException {
		String sql = "DELETE FROM SYSPOPPUPREMINDINFONEW WHERE USERID=? AND TYPE=?";
		RecordSet rs = new RecordSet();
		if (!rs.executeUpdate(sql, userId, type)) {
			throw new SQLException("删除失败，userId=" + userId + ", type=" + type);
		}
	}
	
	public void save(SysPoppupRemindInfoNew e) throws SQLException {
		String sql = "INSERT INTO SYSPOPPUPREMINDINFONEW(USERID, IFPUP, USERTYPE, TYPE, REQUESTID, REMINDDATE, COUNTS,"
				+ " MOBILEPUP, CHECKTIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		RecordSet rs = new RecordSet();
		if (!rs.executeUpdate(sql, e.getUserId(), e.getIfPup(), e.getUserType(), e.getType(), e.getRequestId(),
				e.getRemindDate(), e.getCounts(), e.getMobilePup(), e.getCheckTime())) {
			throw new SQLException("新增失败 - " + sql);
		}
	}
}
