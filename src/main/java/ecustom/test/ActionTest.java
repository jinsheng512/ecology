package ecustom.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ecustom.entities.WorkflowBillField;
import ecustom.exception.DataNotFoundException;
import ecustom.services.SqlService;
import ecustom.services.WorkflowBillFieldService;
import ecustom.services.WorkflowRequestBaseService;
import ecustom.util.CustomUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import weaver.workflow.request.RequestManager;

public class ActionTest {

	private Integer requestId;
	private String actionClass;
	private Integer formId;
	private Integer mainId;
	private Integer lastOperator;
	private String src;

	private String mainFields;
	private Map<String, String> dtFields;

	public ActionTest(Integer requestId, String actionClass) {
		this.requestId = requestId;
		this.actionClass = actionClass;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	
	public Integer getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(Integer lastOperator) {
		this.lastOperator = lastOperator;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String execute() throws Exception {
		String result = null;
		Action action = (Action)Class.forName(this.actionClass).newInstance();
		RequestInfo req = getRequest();
		req.setRequestManager(new RequestManager());
		req.setLastoperator(this.lastOperator == null ? null : this.lastOperator.toString());
		req.getRequestManager().setSrc(this.src);
		req.getRequestManager().setFormid(formId);
		req.getRequestManager().setCreater(CustomUtil.getInteger(req.getCreatorid()));
		req.getRequestManager().setLastoperator(this.lastOperator);
		req.getRequestManager().setBillid(this.formId);
		req.getRequestManager().setRequestid(this.requestId);
		result = action.execute(req);
		if (CustomUtil.isNotBlank(req.getRequestManager().getMessageid())) {
			throw new Exception(req.getRequestManager().getMessageid() + ": " + req.getRequestManager().getMessagecontent());
		}
		return result;
	}

	private RequestInfo getRequest() {
		WorkflowRequestBaseService rbs = new WorkflowRequestBaseService();
		Map<String, Object> rbVo = rbs.getByRequestId(this.requestId);
		if (rbVo == null) {
			throw new DataNotFoundException("没有找到对应的单据信息，RequestId: " + this.requestId);
		}
		RequestInfo request = new RequestInfo();
		request.setRequestid(this.requestId + "");
		request.setCreatorid(rbVo.get("CREATER") + "");
		request.setWorkflowid(rbVo.get("WORKFLOWID") + "");

		this.formId = new Integer(rbVo.get("FORMID") + "");
		WorkflowBillFieldService wbfs = new WorkflowBillFieldService();
		List<WorkflowBillField> wbfList = wbfs.getByFormId(this.formId);
		splitField(wbfList);
		
		request.setMainTableInfo(getMainTableInfo(wbfList, this.requestId));
		request.setDetailTableInfo(getDetailTableInfo());
		
		return request;
	}

	private void splitField(List<WorkflowBillField> wbfList) {
		for (WorkflowBillField vo : wbfList) {
			if (vo.getDetailTable() == null || vo.getDetailTable().trim().length() == 0) {
				if (this.mainFields == null) {
					this.mainFields = "";
				}
				this.mainFields += ", " + vo.getFieldName();
			} else {
				if (this.dtFields == null) {
					this.dtFields = new HashMap<String, String>();
				}
				String dtFieldsStr = "";
				String dtTableName = vo.getDetailTable().toUpperCase();
				if (this.dtFields.containsKey(dtTableName)) {
					dtFieldsStr = this.dtFields.get(dtTableName);
				}
				this.dtFields.put(dtTableName, dtFieldsStr + ", " + vo.getFieldName());
			}
		}
	}

	private MainTableInfo getMainTableInfo(List<WorkflowBillField> wbfList, int requestId) {
		
		if (this.mainFields == null || this.mainFields.isEmpty()) {
			return null;
		}
		
		String sql = "SELECT ID %s FROM FORMTABLE_MAIN_" + Math.abs(this.formId) + " WHERE  REQUESTID=" + requestId;
		
		sql = String.format(sql, this.mainFields);

		SqlService sqlService = new SqlService();
		Map<String, String> formData = sqlService.selectOne(sql);

		this.mainId = new Integer(formData.get("ID") + "");

		MainTableInfo vo = new MainTableInfo();
		Property[] ps = new Property[formData.size()];
		Set<String> fieldNames = formData.keySet();
		int index = 0;
		for (String name : fieldNames) {
			ps[index] = new Property();
			ps[index].setName(name);
			ps[index].setValue(formData.get(name) + "");
			index++;
		}

		vo.setProperty(ps);
		return vo;
	}

	private DetailTableInfo getDetailTableInfo() {
		if (this.dtFields == null || this.dtFields.isEmpty()) {
			return null;
		}
		DetailTable[] dtTables = new DetailTable[this.dtFields.size()];

		for (int i = 0; i < dtTables.length; i++) {
			dtTables[i] = new DetailTable();
			dtTables[i].setRow(getRows(i + 1));
		}
		DetailTableInfo tableInfo = new DetailTableInfo();
		tableInfo.setDetailTable(dtTables);

		return tableInfo;
	}

	private Row[] getRows(int tableIndex) {
		String tblName = "FORMTABLE_MAIN_" + Math.abs(this.formId) + "_DT" + tableIndex;
		String sql = "SELECT ID %s FROM " + tblName + " WHERE MAINID=" + this.mainId;
		sql = String.format(sql, this.dtFields.get(tblName));
		SqlService sqlService = new SqlService();
		List<Map<String, String>> formDataList = sqlService.selectList(sql);
		Row[] rows = new Row[formDataList.size()];
		for (int i = 0; i < formDataList.size(); i++) {
			Map<String, String> formData = formDataList.get(i);
			Cell[] cells = new Cell[formData.size()];
			Set<String> fieldNames = formData.keySet();
			int index = 0;
			for (String name : fieldNames) {
				cells[index] = new Cell();
				cells[index].setName(name);
				cells[index].setValue(formData.get(name) + "");
				index++;
			}
			rows[i] = new Row();
			rows[i].setCell(cells);
		}

		return rows;
	}
}
