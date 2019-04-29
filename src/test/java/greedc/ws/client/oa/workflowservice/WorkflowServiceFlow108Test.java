package greedc.ws.client.oa.workflowservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.junit.Assert;
import org.junit.Test;

import ecustom.Log;
import greedc.ws.client.oa.*;

public class WorkflowServiceFlow108Test {
	
	private final static Log log = new Log(WorkflowServiceFlow108Test.class);
	public final static String WSDL_LOCATION = "http://192.168.130.194:8080/services/WorkflowServiceNew";

	@Test
	public void testFlow() {
		try {
			WorkflowServiceNewPortType service = new WorkflowServiceNewLocator().getWorkflowServiceNewHttpPort(new URL(WSDL_LOCATION));
			String requestId = null;
			CustomWorkflowRequestInfo requestInfo = getWorkflowRequestInfo();
			requestId = service.doCreateWorkflowRequest(requestInfo, 0);
			log.info("requestId=" + requestId);
			Assert.assertNotEquals(requestId, null);
		} catch (MalformedURLException e) {
			log.error("WSDL地址解析失败[WSDL_LOCATION=" + WSDL_LOCATION + "]");
			e.printStackTrace();
		} catch (ServiceException e) {
			log.error("服务创建失败");
			e.printStackTrace();
		} catch (RemoteException e) {
			log.error("服务调用失败");
			e.printStackTrace();
		}
	}
	
	private CustomWorkflowRequestInfo getWorkflowRequestInfo() {
		CustomWorkflowRequestInfo requestInfo = new CustomWorkflowRequestInfo();
		requestInfo.setCreatorId("002486");
		requestInfo.setApplicantCode("000575");
		requestInfo.setCreateTime("2016-11-22");
		requestInfo.setRequestLevel("0");
		requestInfo.setRequestName("新员工录用申请-黄星贤-2016-11-22");
		requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
		requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
		requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
		return requestInfo;
	}
	
	private WorkflowBaseInfo getWorkflowBaseInfo() {
		WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
		workflowBase.setWorkflowId("108");
		return workflowBase;
	}

	private ArrayOfWorkflowDetailTableInfo getWorkflowDetailTableInfos() {
		ArrayOfWorkflowDetailTableInfo tableInfo = new ArrayOfWorkflowDetailTableInfo();
		tableInfo.setWorkflowDetailTableInfo(getWorkflowDetailTableInfo());
		return tableInfo;
	}

	/**
	 * 主表字段。
	 * @return
	 */
	private WorkflowRequestTableField[] getWorkflowRequestTableField() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("biaodbh", "EAS-BILL-CODE"); // 表单编号
		fieldMap.put("shenqr", "empcode:default"); // 申请人
		fieldMap.put("shenqrq", "2016-11-22"); // 申请日期
		fieldMap.put("suosgs", "comcode:default"); // 申请人公司
		fieldMap.put("suosbm", "depcode:default"); // 所属部门
		fieldMap.put("zhiw", "poscode:default"); // 申请人职位
		fieldMap.put("beiz", "接口测试"); // 备注

		return getFields(fieldMap);
	}

	private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
		WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[1];
		tableInfos[0] = new WorkflowDetailTableInfo();
		tableInfos[0].setTableDBName("FORMTABLE_MAIN_30_DT1");
		tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
		return tableInfos;
	}

	/**
	 * 明细表字段。
	 * @return
	 */
	private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("xingm", "text"); // 姓名
		fieldMap.put("zhengjhm", "text"); // 身份证
		fieldMap.put("xingb", "男"); // 性别
		fieldMap.put("shengr", "2016-11-22"); // 出生日期
		fieldMap.put("xingzzj", "text"); // 行政组织
		fieldMap.put("zhiw", "text"); // 职位
		fieldMap.put("xuel", "博士"); // 学历
		fieldMap.put("zhuany", "计算机"); // 专业
		fieldMap.put("biyxx", "text"); // 毕业学校
		fieldMap.put("shiyq", "8"); // 试用期
		fieldMap.put("gongzjl", "text"); // 工作经历
		fieldMap.put("beiz", "text"); // 备注

		ArrayOfWorkflowRequestTableField workflowRequestTableFields = new ArrayOfWorkflowRequestTableField();
		workflowRequestTableFields.setWorkflowRequestTableField(getFields(fieldMap));
		WorkflowRequestTableRecord[] records = new WorkflowRequestTableRecord[1];
		records[0] = new WorkflowRequestTableRecord();
		records[0].setWorkflowRequestTableFields(workflowRequestTableFields);
		ArrayOfWorkflowRequestTableRecord record = new ArrayOfWorkflowRequestTableRecord();
		record.setWorkflowRequestTableRecord(records);
		return record;
	}

	private WorkflowMainTableInfo getWorkflowMainTableInfo() {
		WorkflowMainTableInfo mainTable = new WorkflowMainTableInfo();
		mainTable.setRequestRecords(getRequestRecords());
		return mainTable;
	}

	private ArrayOfWorkflowRequestTableRecord getRequestRecords() {
		ArrayOfWorkflowRequestTableRecord records = new ArrayOfWorkflowRequestTableRecord();
		records.setWorkflowRequestTableRecord(getWorkflowRequestTableRecord());
		return records;
	}

	private WorkflowRequestTableRecord[] getWorkflowRequestTableRecord() {
		WorkflowRequestTableRecord[] records = new WorkflowRequestTableRecord[1];
		records[0] = new WorkflowRequestTableRecord();
		records[0].setWorkflowRequestTableFields(getWorkflowRequestTableFields());
		return records;
	}

	private ArrayOfWorkflowRequestTableField getWorkflowRequestTableFields() {
		ArrayOfWorkflowRequestTableField fields = new ArrayOfWorkflowRequestTableField();
		fields.setWorkflowRequestTableField(getWorkflowRequestTableField());
		return fields;
	}
	
	private WorkflowRequestTableField[] getFields(Map<String, String> fieldMap) {
		WorkflowRequestTableField[] fields = new WorkflowRequestTableField[fieldMap.size()];
		Set<String> keys = fieldMap.keySet();
		int i = 0;
		for (String key : keys) {
			fields[i] = new WorkflowRequestTableField();
			fields[i].setFieldName(key);
			fields[i].setFieldValue(fieldMap.get(key));
			fields[i].setView(true);
			fields[i].setEdit(true);
			i++;
		}
		
		return fields;
	}
}
