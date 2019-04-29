package greedc.ws.server.oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.easyui.vo.DatagridColumnVo;
import ecustom.services.BigFieldService;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.dao.RequestBaseDao;
import greedc.eas.dao.ViewCmpItemDao;
import greedc.eas.entities.ViewCmpItem;

/**
 * 生成薪酬发放审批流程接口Action。
 * @author William
 */
public class GreedcFlow163Action implements ICustomWorkflowServiceAction {
	
	private String entries = null;	// 薪资发放明细
	private String dbFieldName = "BIAODBH";
	private String dhFieldValue = null;
	private String tableName = "FORMTABLE_MAIN_44";
	private Integer entriesSeqNo = null;
	private Integer entriesKeySeqNo = null;
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) {
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			if ("entries".equalsIgnoreCase(field.getFieldName())) {
				this.entries = field.getFieldValue();
				field.setFieldValue("");
			} else if (this.dbFieldName.equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldValue = field.getFieldValue();
			}
		}
		if (this.entries == null) {
			throw new RuntimeException("entries 分录信息不能为空");
		}
		
		// 保存分录信息
		BigFieldService bfService = new BigFieldService();
		this.entriesSeqNo = bfService.save(this.entries);
		
		List<Map<String, Object>> entriesList = GsonUtil.toMapList(this.entries);
		String entryKeys = ",";
		for (Map<String, Object> entry : entriesList) {
			Set<String> keys = entry.keySet();
			for (String key : keys) {
				if (!entryKeys.contains("," + key + ",")) {
					entryKeys += key + ",";
				}
			}
		}
		List<DatagridColumnVo> cols = new ArrayList<DatagridColumnVo>();
		cols.add(new DatagridColumnVo("FPER001", "职员姓名"));
		cols.add(new DatagridColumnVo("FPER003", "组织全名称", 165));
		cols.add(new DatagridColumnVo("FPER025", "个人账号1", 165));
		if (entryKeys.length() > 1) {
			ViewCmpItemDao dao = new ViewCmpItemDao();
			List<ViewCmpItem> items = dao.getAll();
			for (int i = 0; i < items.size(); i++) {
				if ("FPER001".equals(items.get(i).getItemNumber())
						|| "FPER003".equals(items.get(i).getItemNumber())
						|| "FPER025".equals(items.get(i).getItemNumber())) {
					continue ;
				}
				
				if (!entryKeys.contains(items.get(i).getItemNumber())) {
					items.remove(i--);
				} else {
					DatagridColumnVo colVo = new DatagridColumnVo(items.get(i).getItemNumber(), items.get(i).getItem(), 80);
					colVo.setAlign("right");
					colVo.setFormatter("GREEDC.toMoney");
					cols.add(colVo);
				}
			}
		}
		this.entriesKeySeqNo = bfService.save(GsonUtil.toJSONString(cols));
		
		fields = concat(fields, new WorkflowRequestTableField[]{
				createField("entriesKeySeqNo", entriesKeySeqNo + ""),
				createField("entriesSeqNo", entriesSeqNo + "")
		});
		request.getWorkflowMainTableInfo().getRequestRecords()[0].setWorkflowRequestTableFields(fields);
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
	
	private WorkflowRequestTableField createField(String name, String value) {
		WorkflowRequestTableField field = new WorkflowRequestTableField();
		field.setView(true);
		field.setEdit(true);
		field.setFieldName(name);
		field.setFieldValue(value);
		return field;
	}
	
	public static <T> T[] concat(T[] first, T[] second) {  
		T[] result = Arrays.copyOf(first, first.length + second.length);  
		System.arraycopy(second, 0, result, first.length, second.length);  
		return result;  
	}
}
