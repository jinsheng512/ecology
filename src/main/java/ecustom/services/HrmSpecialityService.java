package ecustom.services;

import weaver.conn.RecordSetTrans;
import ecustom.dao.HrmSpecialityDao;

/**
 * 专业数据访问类。
 * @author William
 */
public class HrmSpecialityService {

	public int getByName(String name) {
		return new HrmSpecialityDao().getByName(name);
	}
	
	public int save(String name) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			int id = new HrmSpecialityDao().save(name, rst);
			rst.commit();
			return id;
		} catch (Exception e) {
			rst.rollback();
			e.printStackTrace();
		}
		return -1;
	}
}
