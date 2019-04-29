package greedc.services;

import greedc.dao.Form59Dao;
import greedc.entities.Form59;
import weaver.conn.RecordSetTrans;

/**
 * 项目付款申请单。
 * @author William
 */
public class Form59Service {

	public boolean updateForm(Form59 form) {
		RecordSetTrans rst = new RecordSetTrans();
		rst.setAutoCommit(false);
		Form59Dao dao = new Form59Dao(rst);
		try {
			if (!dao.updateFormByReqId(form)) {
				throw new Exception("dao.updateFormByReqId(form) invoke error!!");
			}
			rst.commit();
			return true;
		} catch (Exception e) {
			rst.rollback();
		}
		return false;
	}

}
