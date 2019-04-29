package greedc.ws.server.oa;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;
import ecustom.services.BigFieldService;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.FieldConvert;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.dao.RequestBaseDao;
import greedc.entities.Form59;
import greedc.services.Form59Service;

/**
 * 项目付款申请单流程
 * @author William
 */
public class GreedcFlow246Action implements ICustomWorkflowServiceAction {

	public final static String REPLACE_EMPCODE = "empcode:";
	public final static String REPLACE_DEPCODE = "depcode:";
	public final static String REPLACE_COMCODE = "comcode:";
	public final static String REPLACE_POSCODE = "poscode:";
	public final static String REPLACE_CODE = "code:";
	public final static String DEFAULT_CODE = "default";
	public final static String EMPTY_STRING = "";
	private String dhFieldName = null;
	private String tableName = "FORMTABLE_MAIN_59";

	private String gongcfkxx;
	private HrmResource creator = null;
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) throws SQLException {
		int creatorId = Integer.parseInt(request.getCreatorId());
		this.creator = new HrmResourceDao().getById(creatorId);
		
		String suosgs = "";
		String cu = "";
		FieldConvert fieldCovert = new FieldConvert(this.creator);
		
		Map<String, String> gcfkxxMap = new HashMap<String, String>();
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			if (field.getFieldName().startsWith("proPay")) {
				gcfkxxMap.put(field.getFieldName(), field.getFieldValue());
			} /*else if ("fuj".equalsIgnoreCase(field.getFieldName())) {
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
			}*/ else if ("suosgs".equalsIgnoreCase(field.getFieldName())) {
				fieldCovert.convertField(field);
				suosgs = field.getFieldValue();
			} else if ("cu".equalsIgnoreCase(field.getFieldName())) {
				fieldCovert.convertField(field);
				cu = field.getFieldValue();
			} else if ("BIAODBH".equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldName = field.getFieldValue();
			}
		}
		
		// 创建“shifbgsfk-是否本公司付款”字段
		WorkflowRequestTableField shifbgsfk = new WorkflowRequestTableField();
		shifbgsfk.setEdit(true);
		shifbgsfk.setView(true);
		shifbgsfk.setFieldName("shifbgsfk");
		// 如果所属公司与CU不同，是否本公司付款为“否”否则为“是”
		shifbgsfk.setFieldValue((suosgs != null && suosgs.equals(cu)) ? "0" : "1");
		
		// 给字段数据添加字段“shifbgsfk-是否本公司付款”
		WorkflowRequestTableField[] fieldsResult = new WorkflowRequestTableField[fields.length + 1];
		System.arraycopy(fields, 0, fieldsResult, 0, fields.length);  
		System.arraycopy(new WorkflowRequestTableField[]{shifbgsfk}, 0, fieldsResult, fields.length, 1);
		request.getWorkflowMainTableInfo().getRequestRecords()[0].setWorkflowRequestTableFields(fieldsResult);
		
		this.gongcfkxx = GsonUtil.toJSONString(gcfkxxMap);
	}

	@Override
	public void doAfter(String requestId) {
		Form59 form = new Form59();
		form.setRequestId(CustomUtil.getInt(requestId));
		
		BigFieldService bfService = new BigFieldService();
		form.setGongcfkxx(0);
		
		if (CustomUtil.isNotBlank(this.gongcfkxx)) {
			form.setGongcfkxx(bfService.save(this.gongcfkxx));
		}
		
		Form59Service service = new Form59Service();
		service.updateForm(form);
		
		RequestBaseDao rbDao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);

			String sql = "UPDATE FORMTABLE_MAIN_59 SET GONGH=? WHERE REQUESTID=?";
			rst.executeUpdate(sql, this.creator.getWorkCode(), requestId);
			
			rbDao.deleteByBillCode(this.tableName, "BIAODBH", this.dhFieldName, CustomUtil.getInt(requestId), rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
		}
	
	}
}
