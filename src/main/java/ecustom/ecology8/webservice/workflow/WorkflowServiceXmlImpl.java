package ecustom.ecology8.webservice.workflow;

import com.thoughtworks.xstream.XStream;

import ecustom.util.CustomUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.RequestService;
import weaver.soa.workflow.request.Row;

public class WorkflowServiceXmlImpl implements WorkflowServiceXml {
	
	private RequestService requestService = new RequestService();
	
	@Override
	public String createRequest(String inputXml) {
		System.out.println("WorkflowServiceXmlImpl.createRequest inputXml = " + inputXml);
		XStream xs = new XStream();
		xs.alias("request", WSRequestInfo.class);
		xs.alias("response", WSResponse.class);
		xs.alias("field", WSField.class);
		xs.alias("table", WSTable.class);
		xs.alias("row", WSRow.class);
		WSRequestInfo wsRequestInfo = (WSRequestInfo)xs.fromXML(inputXml);
		RequestInfo requestInfo;
		WSResponse response = new WSResponse();
		try {
			requestInfo = toRequestInfo(wsRequestInfo);
			String requestId = requestService.createRequest(requestInfo);
			response.setRequestId(Integer.parseInt(requestId));
			if (response.getRequestId() > 0) {
				response.setMessage("创建流程成功！");
			} else if (response.getRequestId() == -7) {
				response.setMessage("创建流程失败，流程不能提交到下一个节点，请检查出口、节点操作者等设置！");
			}
		} catch (Exception e) {
			response.setRequestId(-99);
			response.setMessage("创建流程失败，" + e.toString());
			e.printStackTrace();
		}

		String xmlStr = xs.toXML(response);
		System.out.println("WorkflowServiceXmlImpl.createRequest outputXml = " + inputXml);
		return xmlStr;
	}

	public RequestInfo toRequestInfo(WSRequestInfo wsRequestInfo)
			throws Exception {
		if (wsRequestInfo == null) {
			throw new RuntimeException("Request 不能为空!");
		}
		if (wsRequestInfo.getCreatorId() == null) {
			throw new RuntimeException("CreatorId 不能为空!");
		}
		if (wsRequestInfo.getWorkflowId() == null) {
			throw new RuntimeException("WorkflowId 不能为空!");
		}
		if (wsRequestInfo.getIsNextFlow() == null) {
			wsRequestInfo.setIsNextFlow(0);
		}
		if (wsRequestInfo.getRequestLevel() == null) {
			wsRequestInfo.setRequestLevel(0);
		}
		if (wsRequestInfo.getMessageType() == null) {
			wsRequestInfo.setMessageType(0);
		}
		if (wsRequestInfo.getRequestId() == null) {
			wsRequestInfo.setRequestId(0);
		}
		RequestInfo requestInfo = new RequestInfo();
		if (wsRequestInfo.getRequestId() > 0) {
			requestInfo = requestService.getRequest(wsRequestInfo.getRequestId());
		}
		requestInfo.setRequestid(wsRequestInfo.getRequestId() + "");
		requestInfo.setWorkflowid(wsRequestInfo.getWorkflowId() + "");
		requestInfo.setCreatorid(wsRequestInfo.getCreatorId() + "");
		requestInfo.setDescription(wsRequestInfo.getRequestName());
		requestInfo.setRequestlevel(wsRequestInfo.getRequestLevel() + "");
		requestInfo.setRemindtype(wsRequestInfo.getMessageType() + "");
		requestInfo.setIsNextFlow(wsRequestInfo.getIsNextFlow() + "");

		WSField[] wsMainFields = wsRequestInfo.getMainFields();
		if (wsMainFields != null && wsMainFields.length > 0) {
			Property[] mainFields = new Property[wsRequestInfo.getMainFields().length];
			for (int i = 0; i < wsMainFields.length; i++) {
				WSField wsField = wsMainFields[i];
				mainFields[i] = new Property();
				mainFields[i].setName(wsField.getName());
				mainFields[i].setValue(CustomUtil.isBlank(wsField.getValue()) ? "" : Util.processHtmlUnicode(wsField.getValue()));
				mainFields[i].setType(wsField.getType());
			}
			MainTableInfo mainTable = new MainTableInfo();
			mainTable.setProperty(mainFields);
			requestInfo.setMainTableInfo(mainTable);
		}

		DetailTableInfo detailTableInfo = new DetailTableInfo();
		WSTable[] wsDetaiTables = wsRequestInfo.getDetailTables();
		DetailTable[] detailTables = new DetailTable[wsDetaiTables == null ? 0 : wsDetaiTables.length];
		for (int t = 0; t < detailTables.length; t++) {
			detailTables[t] = new DetailTable();
			detailTables[t].setId(t + 1 + "");

			WSTable wsDetaiTable = wsDetaiTables[t];
			if (wsDetaiTable != null && wsDetaiTable.getRows() != null && wsDetaiTable.getRows().length > 0) {
				detailTables[t].setTableDBName(wsDetaiTable.getTableName() == null ? "" : wsDetaiTable.getTableName());
				WSRow[] wsRows = wsDetaiTable.getRows();
				Row[] rows = new Row[wsRows.length];
				for (int r = 0; r < rows.length; r++)
				{
					rows[r] = new Row();
					rows[r].setId((wsRows[r].getId() != null && wsRows[r].getId() > 0) ? wsRows[r].getId() + "" : "");

					WSField[] wsFields = wsRows[r].getFields();
					Cell[] cells = new Cell[wsRows[r].getFields() == null ? 0 : wsRows[r].getFields().length];
					for (int c = 0; c < cells.length; c++) {
						cells[c] = new Cell();
						cells[c].setName(wsFields[c].getName());
						cells[c].setValue(CustomUtil.isBlank(wsFields[c].getValue()) ? "" : Util.processHtmlUnicode(wsFields[c].getValue()));
						cells[c].setType(wsFields[c].getType());
					}
					if (cells != null && cells.length > 0) {
						rows[r].setCell(cells);
					}
				}
				if (rows != null && rows.length > 0) {
					detailTables[t].setRow(rows);
				}
			}
		}
		detailTableInfo.setDetailTable(detailTables);
		requestInfo.setDetailTableInfo(detailTableInfo);

		return requestInfo;
	}
}
