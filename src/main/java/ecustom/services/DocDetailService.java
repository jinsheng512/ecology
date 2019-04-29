package ecustom.services;

import weaver.conn.RecordSetTrans;
import ecustom.dao.DocDetailDao;
import ecustom.entities.DocDetail;

public class DocDetailService {

	public boolean save(DocDetail entry) throws Exception {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			DocDetailDao dao = new DocDetailDao();
			dao.save(entry, rst);
			rst.commit();
			return true;
		} catch (Exception e) {
			rst.rollback();
		}
		return false;
	}
}
