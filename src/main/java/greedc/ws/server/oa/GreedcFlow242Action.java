package greedc.ws.server.oa;

import java.sql.SQLException;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;
import ecustom.util.CustomUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.dao.RequestBaseDao;

/**
 * 生成项目类合同修订流程Action。
 * @author William
 */
public class GreedcFlow242Action implements ICustomWorkflowServiceAction {
	
	private HrmResource creator = null;
	private String dhFieldName = null;
	private String tableName = "FORMTABLE_MAIN_56";
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) throws SQLException {
		int creatorId = Integer.parseInt(request.getCreatorId());
		this.creator = new HrmResourceDao().getById(creatorId);
		
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			/*if ("fuj".equalsIgnoreCase(field.getFieldName())) {
				EASFileService service = new EASFileService();
				List<EASFileVo> vos = GsonUtil.toObjectList(field.getFieldValue(), EASFileVo.class);
				if (vos.isEmpty()) {
					field.setFieldValue("");
				} else {
					try {
						field.setFieldValue("");
						field.setFieldValue(service.saveBase64Files(vos, creatorId, workflowId));
					} catch (IOException e) {
						throw new RuntimeException("附件处理失败");
					}
				}
			} else*/ if ("BIAODBH".equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldName = field.getFieldValue();
			}
		}
	}

	@Override
	public void doAfter(String requestId) {
		RecordSet rs = new RecordSet();
		String sql = "UPDATE FORMTABLE_MAIN_56 SET GONGH=? WHERE REQUESTID=?";
		rs.executeUpdate(sql, this.creator.getWorkCode(), requestId);
		
		RequestBaseDao rbDao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			rbDao.deleteByBillCode(this.tableName, "BIAODBH", this.dhFieldName, CustomUtil.getInt(requestId), rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
		}
	}
}
