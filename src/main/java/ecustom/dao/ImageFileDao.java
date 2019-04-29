package ecustom.dao;

import weaver.conn.RecordSetTrans;
import weaver.docs.docs.ImageFileIdUpdate;
import ecustom.entities.ImageFile;

public class ImageFileDao {

	public boolean save(ImageFile entry, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO IMAGEFILE(IMAGEFILEID, IMAGEFILENAME, IMAGEFILETYPE,"
				+ " IMAGEFILEUSED, FILEREALPATH, ISZIP, ISENCRYPT, FILESIZE, DOWNLOADS)"
				+ " VALUES (?, ?, ?, '1', ?, '1', '0', ?, 0)";
		entry.setImageFileId(new ImageFileIdUpdate().getImageFileNewId());
		return rst.executeUpdate(sql, entry.getImageFileId(), entry.getImageFileName(),
				entry.getImageFileType(), entry.getFileRealPath(), entry.getFileSize());
	}
}
