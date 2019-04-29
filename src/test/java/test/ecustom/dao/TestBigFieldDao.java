package test.ecustom.dao;

import org.junit.Test;

import ecustom.dao.BigFieldDao;
import ecustom.entities.BigField;
import test.BaseTest;
import weaver.conn.RecordSetTrans;

public class TestBigFieldDao extends BaseTest {

	@Test
	public void save() throws Exception {
		StringBuilder sb = new StringBuilder();
		while (sb.length() <= 5000) {
			sb.append(TestBigFieldDao.class.getName()).append("\t");
		}
		BigField field = new BigField();
		RecordSetTrans rst = null;
		
		BigFieldDao dao = new BigFieldDao();
		dao.save(field, rst);
	}
}
