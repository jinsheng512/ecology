package greedc.dao;

import greedc.vo.RequestLogVo;

import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowService;
import weaver.workflow.webservices.WorkflowServiceImpl;

public class RequestBaseDao {

	/**
	 * 删除相同单号的请求，但保留历史意见。
	 * @param tableName
	 * @param fieldName
	 * @param fieldValue
	 * @param requestId
	 * @param rst
	 * @return
	 * @throws Exception
	 */
	public boolean deleteByBillCode(String tableName, String fieldName, String fieldValue, int requestId,
			RecordSetTrans rst) throws Exception {
		RequestLogDao dao = new RequestLogDao();
		List<RequestLogVo> list = dao.listByBillCode(requestId, tableName, fieldName, fieldValue);
		WorkflowService service = new WorkflowServiceImpl();
		for (RequestLogVo vo : list) {
			dao.save(vo, rst);
			service.deleteRequest(vo.getRequestId(), 1);
		}
		
		return true;
	}
	
	/**
	 * 获取当前节点Id
	 * @param requestId
	 * @return
	 */
	public int getCurrNodeId(int requestId) {
		String sql = "SELECT T1.CURRENTNODEID FROM WORKFLOW_REQUESTBASE T1"
				+ " INNER JOIN WORKFLOW_NODEBASE T2 ON T2.ID=T1.CURRENTNODEID WHERE T1.REQUESTID=?";
		RecordSet rs = new RecordSet();
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
}
