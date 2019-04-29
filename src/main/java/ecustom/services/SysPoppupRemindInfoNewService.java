package ecustom.services;

import java.sql.SQLException;

import ecustom.dao.SysPoppupRemindInfoNewDao;
import ecustom.entities.SysPoppupRemindInfoNew;

public class SysPoppupRemindInfoNewService {

	/**
	 * 删除一条提醒。
	 * @param userId
	 * @param typeId
	 * @throws SQLException 
	 */
	public void delete(int userId, int type) throws SQLException {
		SysPoppupRemindInfoNewDao dao = new SysPoppupRemindInfoNewDao();
		dao.delete(userId, type);
	}
	
	public void save(SysPoppupRemindInfoNew e) throws SQLException {
		SysPoppupRemindInfoNewDao dao = new SysPoppupRemindInfoNewDao();
		dao.save(e);
	}
}
