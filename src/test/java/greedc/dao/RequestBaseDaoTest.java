package greedc.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import weaver.conn.RecordSetTrans;
import weaver.general.GCONST;

public class RequestBaseDaoTest {

	@Before
	public void before() {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
	}

	@Test
	public void deleteByBillCode() throws Exception {
		RequestBaseDao dao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		boolean committed = false;
		try {
			rst.setAutoCommit(false);
			dao.deleteByBillCode("FORMTABLE_MAIN_5", "BIAODBH", "XXX", 471, rst);
			rst.commit();
			committed = true;
		} finally {
			if (!committed) {
				rst.rollback();
			}
		}
	}

	@Test
	public void getCurrNodeId() {
		RequestBaseDao dao = new RequestBaseDao();
		int nodeId = dao.getCurrNodeId(4558);
		System.out.println(nodeId);
		Assert.assertTrue(nodeId > 0);
	}
}
