package greedc.ws.server.oa;

import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;
import ecustom.util.CustomUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.GreedcUtil;
import greedc.dao.RequestBaseDao;

/**
 * 生成“试用期转正申请”接口Action。
 * @author William
 */
public class GreedcFlow129Action implements ICustomWorkflowServiceAction {
	
	private String dbFieldName = "BIAODBH";
	private String dhFieldValue = null;
	private String tableName = "FORMTABLE_MAIN_37";
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) {
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			if (this.dbFieldName.equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldValue = field.getFieldValue();
			}
		}
		
		// 获取第一行明细中的工号
		WorkflowRequestTableField[] dtField = request.getWorkflowDetailTableInfos()[0].getWorkflowRequestTableRecords()[0].getWorkflowRequestTableFields();
		String workCode = GreedcUtil.getFieldValue(dtField, "yuangbh");
		// 将工作转换成ID
		HrmResourceDao dao = new HrmResourceDao();
		HrmResource emp = dao.getByWorkCode(workCode);
		// 在主表中添加人力字段
		fields = GreedcUtil.addFiled(fields, "renl", emp.getId() + "");
		request.getWorkflowMainTableInfo().getRequestRecords()[0].setWorkflowRequestTableFields(fields);
		// 将第一行明细中的员工的所属部门赋值到主表当中
		for (WorkflowRequestTableField field : fields) {
			if ("SUOSBM".equalsIgnoreCase(field.getFieldName())) {
				field.setFieldValue(emp.getDepartmentId() + "");
			}
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
