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

public class WorkflowServiceFlow128Test {
	
	private final static Log log = new Log(WorkflowServiceFlow122Test.class);
	public final static String WSDL_LOCATION = "http://localhost:8080/services/WorkflowServiceNew";

	@Test
	public void testFlow() {
		try {
			WorkflowServiceNewPortType service = new WorkflowServiceNewLocator().getWorkflowServiceNewHttpPort(new URL(WSDL_LOCATION));
			String requestId = null;
			CustomWorkflowRequestInfo requestInfo = getCustomWorkflowRequestInfo();
			requestId = service.doCreateWorkflowRequest(requestInfo, 0);
			log.info("requestId=" + requestId);
			Assert.assertNotEquals(requestId, null);
		} catch (MalformedURLException e) {
			log.error("WSDL��ַ����ʧ��[WSDL_LOCATION=" + WSDL_LOCATION + "]");
			e.printStackTrace();
		} catch (ServiceException e) {
			log.error("���񴴽�ʧ��");
			e.printStackTrace();
		} catch (RemoteException e) {
			log.error("�������ʧ��");
			e.printStackTrace();
		}
	}
	
	private CustomWorkflowRequestInfo getCustomWorkflowRequestInfo() {
		CustomWorkflowRequestInfo requestInfo = new CustomWorkflowRequestInfo();
		requestInfo.setCreatorId("001916");
		requestInfo.setCreateTime("2016-11-22");
		requestInfo.setRequestLevel("0");
		requestInfo.setRequestName("н���������-������-2016-11-22");
		requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
		requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
		requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
		return requestInfo;
	}
	
	private WorkflowBaseInfo getWorkflowBaseInfo() {
		WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
		workflowBase.setWorkflowId("128");
		return workflowBase;
	}

	private ArrayOfWorkflowDetailTableInfo getWorkflowDetailTableInfos() {
		ArrayOfWorkflowDetailTableInfo tableInfo = new ArrayOfWorkflowDetailTableInfo();
		tableInfo.setWorkflowDetailTableInfo(getWorkflowDetailTableInfo());
		return tableInfo;
	}

	/**
	 * �����ֶΡ�
	 * @return
	 */
	private WorkflowRequestTableField[] getWorkflowRequestTableField() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("biaodbh", "EAS-BILL-CODE"); // �����
		fieldMap.put("shenqr", "empcode:default"); // ������
		fieldMap.put("baogsj", "2016-11-22"); // ��������
		fieldMap.put("suosgs", "comcode:default"); // �����˹�˾
		fieldMap.put("suosbm", "depcode:default"); // ��������
		fieldMap.put("zhiw", "poscode:default"); // ������ְλ
		fieldMap.put("beiz", "�ӿڲ���"); // ��ע

		return getFields(fieldMap);
	}

	private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
		WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[1];
		tableInfos[0] = new WorkflowDetailTableInfo();
		tableInfos[0].setTableDBName("FORMTABLE_MAIN_36_DT1");
		tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
		return tableInfos;
	}

	/**
	 * ��ϸ���ֶΣ�ת��Ա���б���
	 * @return
	 */
	private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("yuangbm", "text"); // Ա�����
		fieldMap.put("xingm", "text"); // ����
		fieldMap.put("tiaoxyy", "text"); // ��нԭ��
		fieldMap.put("shengxrq", "2016-11-22"); // ��Ч����
		fieldMap.put("yuantxrq", "2016-11-22"); // ԭ��н����
		fieldMap.put("xinzfwh", "text"); // н�ʷ��ĺ�
		fieldMap.put("beiz", "text"); // ��ע
		// fieldMap.put("fuj", "text"); // ����
		fieldMap.put("yuanzwlb", "text"); // ԭְλ���
		fieldMap.put("xianzwlb", "text"); // ��ְλ���
		fieldMap.put("yuanzj", "text"); // ԭְ��
		fieldMap.put("xianzj", "text"); // ��ְ��
		fieldMap.put("yuanzw", "text"); // ԭְλ
		fieldMap.put("xianzw", "text"); // ��ְλ
		fieldMap.put("yuanzz", "text"); // ԭ��֯
		fieldMap.put("xianzz", "text"); // ����֯
		fieldMap.put("yzzqmc", "text"); // ԭ��֯ȫ����
		fieldMap.put("xzzqmc", "text"); // ����֯ȫ����

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
