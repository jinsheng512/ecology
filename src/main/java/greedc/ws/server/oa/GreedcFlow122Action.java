package greedc.ws.server.oa;

import java.util.HashSet;
import java.util.Set;

import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;
import ecustom.util.CustomUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.FieldConvert;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.dao.RequestBaseDao;

/**
 * 生成内部异动申请流程接口Action。
 * @author William
 */
public class GreedcFlow122Action implements ICustomWorkflowServiceAction {
	
	private HrmResource creator = null;
	private String dbFieldName = "BIAODBH";
	private String dhFieldValue = null;
	private String tableName = "FORMTABLE_MAIN_31";
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) {
		int creatorId = Integer.parseInt(request.getCreatorId());
		this.creator = new HrmResourceDao().getById(creatorId);
		
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			if (this.dbFieldName.equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldValue = field.getFieldValue();
			}
		}
		
		String diaorbm = getDiaorbm(request.getWorkflowDetailTableInfos()[0].getWorkflowRequestTableRecords()[0].getWorkflowRequestTableFields());
		setDiaorbm(request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields(), diaorbm);
		
		String suosbm = getSuosbm(request.getWorkflowDetailTableInfos()[0].getWorkflowRequestTableRecords()[0].getWorkflowRequestTableFields());
		setSuosbm(request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields(), suosbm);
	}

	@Override
	public void doAfter(String requestId) {
		RequestBaseDao rbDao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		String sql = "UPDATE FORMTABLE_MAIN_31 SET SHIFBBM=(CASE WHEN SUOSBM=DIAORBM THEN 0 ELSE 1 END) WHERE REQUESTID=?";
		try {
			rst.setAutoCommit(false);
			rst.executeUpdate(sql, requestId);
			rbDao.deleteByBillCode(this.tableName, this.dbFieldName, this.dhFieldValue, CustomUtil.getInt(requestId), rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
		}
	}
	
	/**
	 * 在主表中填入调入部门。
	 * @param fields
	 * @param idStr
	 */
	private void setDiaorbm(WorkflowRequestTableField[] fields, String idStr) {
		String name;
		for (WorkflowRequestTableField field : fields) {
			name = field.getFieldName();
			if ("diaorbm".equalsIgnoreCase(name)) { 
				field.setFieldValue(idStr);
			}
		}
	}
	
	/**
	 * 在主表中填入所属部门。
	 * @param fields
	 * @param idStr
	 */
	private void setSuosbm(WorkflowRequestTableField[] fields, String suosbmId) {
		String name;
		for (WorkflowRequestTableField field : fields) {
			name = field.getFieldName();
			if ("suosbm".equalsIgnoreCase(name)) { 
				field.setFieldValue(suosbmId);
			}
		}
	}
	
	/**
	 * 从明细字段中获取调入部门。
	 * @param fields 明细字段
	 * @return 调入部门ID
	 */
	private String getDiaorbm(WorkflowRequestTableField[] fields) {
		FieldConvert fieldConvert = new FieldConvert(this.creator);
		Set<String> ids = new HashSet<String>();
		for (WorkflowRequestTableField field : fields) {
			if ("mubxzzz".equalsIgnoreCase(field.getFieldName())) {
				fieldConvert.convertField(field);
				addDeptIds(ids, field.getFieldValue());
			}
		}
		return getDeptIdStr(ids);
	}
	
	/**
	 * 从明细字段中获取所属部门。
	 * @param fields 明细字段
	 * @return 调入部门ID
	 */
	private String getSuosbm(WorkflowRequestTableField[] fields) {
		FieldConvert fieldConvert = new FieldConvert(this.creator);
		for (WorkflowRequestTableField field : fields) {
			if ("xingzengzz".equalsIgnoreCase(field.getFieldName())) {
				fieldConvert.convertField(field);
				return field.getFieldValue();
			}
		}
		throw new RuntimeException("从第一行明细中获取调出部门失败");
	}
	
	/**
	 * 拼接部门ID为字符串，以“,”分隔。
	 * @param ids
	 * @return
	 */
	private String getDeptIdStr(Set<String> ids) {
		String idStr = "";
		for (String id : ids) {
			idStr = "," + id;
		}
		return idStr.length() > 0 ? idStr.substring(1) : idStr;
	}
	
	/**
	 * 添加调入部门ID，如果调入部门已经存在，则不重复添加。
	 * @param ids
	 * @param id
	 */
	private void addDeptIds(Set<String> ids, String id) {
		if (!ids.contains(id)) {
			ids.add(id);
		}
	}
}
