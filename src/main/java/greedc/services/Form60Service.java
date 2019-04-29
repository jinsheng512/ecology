package greedc.services;

import greedc.dao.Form60Dao;
import greedc.entities.Form60;
import weaver.conn.RecordSetTrans;

/**
 * 项目合同结算单业务管理。
 * @author 曹水涛
 */
public class Form60Service {

	public Form60 getFormByRequestId(int requestId) {
		return new Form60Dao().getFormByRequestId(requestId);
	}

	public void updateForm(Form60 form) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			new Form60Dao().updateForm(form, rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
