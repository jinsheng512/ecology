package greedc.ws.server.oa;

import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.dao.HrmDepartmentDao;
import ecustom.entities.HrmDepartment;
import ecustom.util.CustomUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.GreedcUtil;
import greedc.dao.RequestBaseDao;

/**
 * 生成“新员工录用申请”接口Action。
 * @author William
 */
public class GreedcFlow108Action implements ICustomWorkflowServiceAction {
	
	private String dbFieldName = "BIAODBH";
	private String dhFieldValue = null;
	private String tableName = "FORMTABLE_MAIN_30";
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) {
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			if (this.dbFieldName.equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldValue = field.getFieldValue();
			}
		}

		// 获取第一行明细中的部门编码
		WorkflowRequestTableField[] dtField = request.getWorkflowDetailTableInfos()[0].getWorkflowRequestTableRecords()[0].getWorkflowRequestTableFields();
		String deptCode = GreedcUtil.getFieldValue(dtField, "xingzzjCode");
		// 将工作转换成ID
		HrmDepartmentDao dao = new HrmDepartmentDao();
		HrmDepartment dept = dao.getByCode(deptCode);
		if (dept != null) {
			// 在主表中添加部门ID字段
			fields = GreedcUtil.addFiled(fields, "dept", dept.getId() + "");
			request.getWorkflowMainTableInfo().getRequestRecords()[0].setWorkflowRequestTableFields(fields);
		}
	}

	@Override
	public void doAfter(String requestId) {
		RequestBaseDao rbDao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			rbDao.deleteByBillCode(this.tableName, this.dbFieldName, this.dhFieldValue, CustomUtil.getInt(requestId), rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
		}
	}
}
