package ecustom.services;

import weaver.conn.RecordSetTrans;
import ecustom.dao.BigFieldDao;
import ecustom.entities.BigField;

public class BigFieldService {

	public Integer save(String fieldValue) {
		BigField entity = new BigField(fieldValue);
		save(entity);
		return entity.getSeqNo();
	}
	
	public void save(BigField entity) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			BigFieldDao dao = new BigFieldDao();
			entity.setSeqNo(dao.getNextSeqNo());
			dao.save(entity, rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public String getValue(int seqNo) {
		String value = new BigFieldDao().getValue(seqNo);
		return value;
	}

	
	public boolean delete(int seqNo) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			BigFieldDao dao = new BigFieldDao();
			boolean flag = dao.delete(seqNo, rst);
			rst.commit();
			return flag;
		} catch (Exception e) {
			rst.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
