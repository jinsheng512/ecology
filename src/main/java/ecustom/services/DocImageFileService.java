package ecustom.services;

import weaver.conn.RecordSetTrans;
import ecustom.dao.DocImageFileDao;
import ecustom.entities.DocImageFile;

public class DocImageFileService {

	public boolean save(DocImageFile entry) throws Exception {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			DocImageFileDao dao = new DocImageFileDao();
			dao.save(entry, rst);
			rst.commit();
			return true;
		} catch (Exception e) {
			rst.rollback();
		}
		return false;
	}
}
