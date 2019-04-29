package ecustom.services;

import java.util.List;

import weaver.conn.RecordSetTrans;
import ecustom.dao.BigFieldDao;
import ecustom.dao.WebServiceLogDao;
import ecustom.entities.WebServiceLog;

public class WebServiceLogService {

	public Integer save(WebServiceLog entry) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			WebServiceLogDao dao = new WebServiceLogDao();
			entry.setSeqNo(dao.getNextSeqNo());
			dao.save(entry, rst);
			rst.commit();
			return entry.getSeqNo();
		} catch(Exception e) {
			rst.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<WebServiceLog> findAll() {
		WebServiceLogDao dao = new WebServiceLogDao();
		return dao.findAll();
	}

	public boolean delete(int seqNo) {
		WebServiceLogDao dao = new WebServiceLogDao();
		BigFieldDao bfDao = new BigFieldDao();
		WebServiceLog log = dao.getBySeqNo(seqNo);
		
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			bfDao.delete(log.getRequestBody(), rst);
			bfDao.delete(log.getResponseBody(), rst);
			dao.delete(seqNo, rst);
			rst.commit();
			return true;
		} catch(Exception e) {
			rst.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
