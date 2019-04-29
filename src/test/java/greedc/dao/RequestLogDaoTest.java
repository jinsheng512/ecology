package greedc.dao;

import greedc.vo.RequestLogVo;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import weaver.conn.RecordSetTrans;
import weaver.general.GCONST;

public class RequestLogDaoTest {
	
	@Before
	public void before() {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
	}

	@Test
	public void test() throws Exception {
		RequestLogDao dao = new RequestLogDao();
		List<RequestLogVo> list = dao.listByBillCode(3230, "FORMTABLE_MAIN_5", "BIAODBH", "201704-018");
		if (list.size() > 0) {
			RecordSetTrans rst = new RecordSetTrans();
			boolean committed = false;
			try {
				rst.setAutoCommit(false);
				dao.save(list, rst);
				rst.commit();
				committed = true;
			} finally {
				if (!committed) {
					rst.rollback();
				}
			}
		}
	}

	@Test
	public void listByBillCode() throws ClassNotFoundException, SQLException {
		RequestLogDao dao = new RequestLogDao();
		List<RequestLogVo> list = dao.listByBillCode("201704-018");
		System.out.println(list);
	}
}
