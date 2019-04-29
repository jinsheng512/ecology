package greedc.dao;

import greedc.entities.Form44;
import weaver.conn.RecordSet;

public class Form44Dao {

	public Form44 getByRequestId(int requestId) {
		String sql = "SELECT ENTRIESKEYSEQNO, ENTRIESSEQNO FROM FORMTABLE_MAIN_44 WHERE REQUESTID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			Form44 entity = new Form44();
			entity.setEntriesKeySeqNo(rs.getInt("ENTRIESKEYSEQNO"));
			entity.setEntriesSeqNo(rs.getInt("ENTRIESSEQNO"));
			entity.setRequestId(requestId);
			return entity;
		}
		return null;
		
	}
}
