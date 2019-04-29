package ecustom.dao;

import weaver.conn.RecordSetTrans;
import weaver.docs.docs.DocImageManager;
import weaver.docs.docs.VersionIdUpdate;
import ecustom.entities.DocImageFile;

public class DocImageFileDao {

	public boolean save(DocImageFile entry, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO DOCIMAGEFILE(ID, DOCID, IMAGEFILEID, IMAGEFILENAME,"
				+ " IMAGEFILEDESC, IMAGEFILEWIDTH, IMAGEFILEHEIGHT, IMAGEFIELSIZE,"
				+ " DOCFILETYPE, VERSIONID, VERSIONDETAIL, ISEXTFILE, HASUSEDTEMPLET)"
				+ " VALUES (?, ?, ?, ?, ?, '0', '0', ?, ?, ?, '', ?, '0')";
		entry.setId(new DocImageManager().getNextDocImageFileId());
		entry.setVersionId(new VersionIdUpdate().getVersionNewId());
		return rst.executeUpdate(sql, entry.getId(), entry.getDocId(), entry.getImagefileId(), 
				entry.getImageFileName(), entry.getImageFileDesc(), entry.getImageFileSize(),
				entry.getDocFileType(), entry.getVersionId(), entry.getIsExtFile());
	}
}
