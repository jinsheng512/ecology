package ecustom.dao;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import ecustom.entities.BigField;
import ecustom.exception.DataNotFoundException;

public class BigFieldDao {

	public Integer getNextSeqNo() throws Exception {
		RecordSet rs = new RecordSet();
		if (rs.execute("SELECT GREEDC_BIG_FIELD_SEQ.NEXTVAL FROM DUAL") && rs.next()) {
			return rs.getInt(1);
		}
		throw new Exception("生成序列号失败！");
	}

	public boolean save(BigField field, RecordSetTrans rst) throws Exception {
		return rst.executeUpdate("INSERT INTO GREEDC_BIG_FIELD(SEQ_NO, FLD_VALUE) VALUES(?, ?)", field.getSeqNo(), field.getFieldValue());
	}

	public String getValue(int seqNo) {
		BigField entity = getBySeqNo(seqNo);
		if (entity != null) {
			return entity.getFieldValue();
		}
		throw new DataNotFoundException("BigField seqNo: " + seqNo);
	}
	
	public BigField getBySeqNo(int seqNo) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT SEQ_NO, FLD_VALUE FROM GREEDC_BIG_FIELD WHERE SEQ_NO=?";
		if (rs.executeQuery(sql, seqNo) && rs.next()) {
			BigField vo = new BigField();
			vo.setSeqNo(rs.getInt("SEQ_NO"));
			vo.setFieldValue(rs.getString("FLD_VALUE"));
			return vo;
		}
		return null;
	}

	public boolean delete(int seqNo, RecordSetTrans rst) throws Exception {
		String sql = "DELETE FROM GREEDC_BIG_FIELD WHERE SEQ_NO=?";
		return rst.executeUpdate(sql, seqNo);
	}
}
