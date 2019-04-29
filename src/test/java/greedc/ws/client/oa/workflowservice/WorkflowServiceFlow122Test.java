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

public class WorkflowServiceFlow122Test {
	
	private final static Log log = new Log(WorkflowServiceFlow127Test.class);
	public final static String WSDL_LOCATION = "http://192.168.130.194:8080/services/WorkflowServiceNew";

	/**
	 * �ڲ��춯�������̲��ԡ�
	 */
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
	
	private CustomWorkflowRequestInfo getWorkflowRequestInfo() {
		CustomWorkflowRequestInfo requestInfo = new CustomWorkflowRequestInfo();
		requestInfo.setCreatorId("001916");
		requestInfo.setCreateTime("2016-11-22");
		requestInfo.setRequestLevel("0");
		requestInfo.setRequestName("2016-11-22");
		requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
		requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
		requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
		return requestInfo;
	}
	
	private WorkflowBaseInfo getWorkflowBaseInfo() {
		WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
		workflowBase.setWorkflowId("122");
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
		fieldMap.put("suosgs", "comcode:default");
		fieldMap.put("suosbm", "depcode:default");
		fieldMap.put("shenqr", "empcode:default");
		fieldMap.put("shenqrq", "2016-11-22");
		fieldMap.put("hrzz", "�����ز�ɷ����޹�˾��������");
		fieldMap.put("xingzzz", "�����ز�ɷ����޹�˾��������");
		fieldMap.put("zhiw", "poscode:default");
		fieldMap.put("beiz", "�ӿڲ��ԣ�");
		fieldMap.put("shenpfs", "��������");
		fieldMap.put("biaodbh", "EAS-BILL-CODE");
		
		return getFields(fieldMap);
	}

	private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
		WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[1];
		tableInfos[0] = new WorkflowDetailTableInfo();
		tableInfos[0].setTableDBName("FORMTABLE_MAIN_31_DT1");
		tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
		return tableInfos;
	}

	/**
	 * ��ϸ���ֶΣ�ת��Ա���б?��
	 * @return
	 */
	private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("yuangbh", "Ա�����");
		fieldMap.put("xingm", "Ա������");
		fieldMap.put("zhiw", "Ա��ְλ");
		fieldMap.put("xingzengzz", "����������֯");
		fieldMap.put("dangqzw", "��ǰְ��");
		fieldMap.put("dangqzj", "��ǰְ��");
		fieldMap.put("dangqzd", "��ǰְ��");
		fieldMap.put("mubzw", "Ŀ��ְλ");
		fieldMap.put("mubxzzz", "Ŀ��������֯");
		fieldMap.put("mubzwei", "Ŀ��ְ��");
		fieldMap.put("mubzj", "Ŀ��ְ��");
		fieldMap.put("mubzd", "Ŀ��ְ��");
		fieldMap.put("biangyy", "���ԭ��");
		fieldMap.put("rensfwh", "���·��ĺ�");
		fieldMap.put("shengxrq", "2016-11-22");
		fieldMap.put("kaocqcd", "�����ڳ���");
		fieldMap.put("kaocjsrq", "�����������");
		fieldMap.put("yuanzwzwjz", "0");	// 0:�ǡ�1:��
		fieldMap.put("mingxbz", "��ϸ��ע");
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