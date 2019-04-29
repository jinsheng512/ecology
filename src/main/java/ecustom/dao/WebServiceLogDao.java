package ecustom.dao;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import ecustom.entities.WebServiceLog;

public class WebServiceLogDao {

	public Integer getNextSeqNo() throws Exception {
		RecordSet rs = new RecordSet();
		if (rs.execute("SELECT ECSTM_WS_LOG_SEQ.NEXTVAL FROM DUAL") && rs.next()) {
			return rs.getInt(1);
		}
		throw new Exception("生成序列号失败！");
	}

	public boolean save(WebServiceLog entry, RecordSetTrans rst) throws Exception {
		return rst.executeUpdate("INSERT INTO ECSTM_WS_LOG (SeqNo, PathInfo, RequestBody, ResponseBody, RequestTime) VALUES (?, ?, ?, ?, sysdate)",
				entry.getSeqNo(), entry.getPathInfo(), entry.getRequestBody(), entry.getResponseBody());
	}
	
	public List<WebServiceLog> findAll() {
		String sql = "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM "
				+ 		"( SELECT SeqNo, PathInfo, RequestBody, ResponseBody, TO_CHAR(RequestTime,'yyyy-mm-dd HH24:mi:ss') RequestTime"
				+ 		" FROM ECSTM_WS_LOG ORDER BY SEQNO DESC )"
				+ " A WHERE ROWNUM <= 100 ) WHERE RN >= 1";
		RecordSet rs = new RecordSet();
		List<WebServiceLog> vos = new ArrayList<WebServiceLog>();
		if (rs.executeQuery(sql)) {
			while (rs.next()) {
				WebServiceLog vo = new WebServiceLog();
				vo.setSeqNo(rs.getInt("SeqNo"));
				vo.setPathInfo(rs.getString("PathInfo"));
				vo.setRequestBody(rs.getInt("RequestBody"));
				vo.setResponseBody(rs.getInt("ResponseBody"));
				vo.setRequestTime(rs.getString("RequestTime"));
				vos.add(vo);
			}
		}
		return vos;
	}
	
	public WebServiceLog getBySeqNo(int seqNo) {
		String sql = "SELECT SeqNo, PathInfo, RequestBody, ResponseBody, TO_CHAR(RequestTime,'yyyy-mm-dd HH24:mi:ss') RequestTime"
				+ " FROM ECSTM_WS_LOG WHERE SEQNO=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, seqNo) && rs.next()) {
			WebServiceLog vo = new WebServiceLog();
			vo.setSeqNo(rs.getInt("SeqNo"));
			vo.setPathInfo(rs.getString("PathInfo"));
			vo.setRequestBody(rs.getInt("RequestBody"));
			vo.setResponseBody(rs.getInt("ResponseBody"));
			vo.setRequestTime(rs.getString("RequestTime"));
			return vo;
		}
		return null;
	}

	public boolean delete(int seqNo, RecordSetTrans rst) throws Exception {
		String sql = "DELETE FROM ECSTM_WS_LOG WHERE SEQNO=?";
		return rst.executeUpdate(sql, seqNo);
	}
}
