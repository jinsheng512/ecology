package ecustom.dao;

import weaver.conn.RecordSetTrans;
import weaver.docs.docs.DocIdUpdate;
import ecustom.entities.DocDetail;

public class DocDetailDao {

	public boolean save(DocDetail entry, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO DOCDETAIL(ID, MAINCATEGORY, SUBCATEGORY, SECCATEGORY,"
				+ " DOCTYPE, DOCSUBJECT, DOCCODE, DOCCREATERID, DOCDEPARTMENTID,"
				+ " DOCCREATEDATE, DOCCREATETIME, DOCLASTMODUSERID, DOCLASTMODDATE,"
				+ " DOCLASTMODTIME, DOCEXTENDNAME, OWNERID, ACCESSORYCOUNT, PARENTIDS,"
				+ " MAINDOC, DOCVALIDUSERID, DOCVALIDDATE, DOCVALIDTIME, DOCLANGURAGE,"
				+ " DOCREPLYABLE, REPLYDOCID, ITEMID, ITEMMAINCATEGORYID,"
				+ " HRMRESID, CRMID, PROJECTID, FINANCEID, DOCARCHIVEUSERID, DOCSTATUS,"
				+ " ASSETID, REPLAYDOCCOUNT, USERTYPE, CANCOPY, CANREMIND, DOCEDITION,"
				+ " DOCEDITIONID, ISHISTORY, APPROVETYPE, READOPTERCANPRINT, DOCCREATERTYPE,"
				+ " DOCLASTMODUSERTYPE, DOCVALIDUSERTYPE, OWNERTYPE, CANPRINTEDNUM)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "'1', '0', '0', '0', '0', '0', '0', '0', '0', ?, '0', '0', '1', '1', "
				+ "'1', '-1', '-1', '0', '0', '0', '1', '1', ' ', '1', '0')";
		entry.setId(new Integer(new DocIdUpdate().getDocNewId()));
		entry.setParentIds(entry.getId() + "");
		return rst.executeUpdate(sql, entry.getId(), entry.getMainCategory(), entry.getSubCategory(), entry.getSecCategory(),
				entry.getDocType(), entry.getDocSubject(), entry.getDocCode(), entry.getDocCreaterId(),
				entry.getDocDepartmentId(), entry.getDocCreateDate(), entry.getDocCreateTime(), entry.getDocLastModUserId(),
				entry.getDocLastModDate(), entry.getDocLastModTime(), entry.getDocExtendName(), entry.getOwnerId(),
				entry.getAccessoryCount(), entry.getParentIds(), entry.getMainDoc(), entry.getDocValidUserId(),
				entry.getDocValidDate(), entry.getDocValidTime(), entry.getDocLangurage(), entry.getDocStatus());
	}
}
