package ecustom.services;

import weaver.conn.RecordSetTrans;
import ecustom.dao.ImageFileDao;
import ecustom.entities.ImageFile;

public class ImageFileService {

	public boolean save(ImageFile entry) throws Exception {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			ImageFileDao dao = new ImageFileDao();
			dao.save(entry, rst);
			rst.commit();
			return true;
		} catch (Exception e) {
			rst.rollback();
		}
		return false;
	}
}
