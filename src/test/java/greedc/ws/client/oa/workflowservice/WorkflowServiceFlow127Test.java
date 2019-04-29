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

public class WorkflowServiceFlow127Test {
	
	private final static Log log = new Log(WorkflowServiceFlow122Test.class);
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
		requestInfo.setRequestName("��ͬ������ǩ����-������-2016-11-22");
		requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
		requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
		requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
		return requestInfo;
	}
	
	private WorkflowBaseInfo getWorkflowBaseInfo() {
		WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
		workflowBase.setWorkflowId("127");
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
		fieldMap.put("shenqr", "empcode:default"); // ������
		fieldMap.put("baogrq", "2016-11-22"); // ��������
		fieldMap.put("beiz", "text"); // ��ע
		fieldMap.put("shenpfs", "��������"); // ����ʽ
		fieldMap.put("biaodbh", "EAS-BILL-CODE"); // �?���
		fieldMap.put("suosgs", "comcode:default"); // ������˾ 
		fieldMap.put("suosbm", "depcode:default"); // ��������
		fieldMap.put("zhiw", "poscode:default"); // ְλ

		return getFields(fieldMap);
	}

	private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
		WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[1];
		tableInfos[0] = new WorkflowDetailTableInfo();
		tableInfos[0].setTableDBName("FORMTABLE_MAIN_35_DT1");
		tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
		return tableInfos;
	}

	/**
	 * ��ϸ���ֶΣ�ת��Ա���б?��
	 * @return
	 */
	private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("jiafdw", "text"); // �׷���λ
		fieldMap.put("xitndw", "text"); // ϵͳ�ڵ�λ
		fieldMap.put("jiafdbr", "text"); // �׷������
		fieldMap.put("jiafdz", "text"); // �׷���ַ
		fieldMap.put("yifxm", "����"); // �ҷ�����
		fieldMap.put("shenfzh", "text"); // ���֤��
		fieldMap.put("huzh", "text"); // ���պ�
		fieldMap.put("xingzzz", "text"); // ������֯
		fieldMap.put("diz", "text"); // ��ַ
		fieldMap.put("disfdw", "text"); // ����λ
		fieldMap.put("disfdbr", "text"); // ��������
		fieldMap.put("disfdz", "text"); // �����ַ
		fieldMap.put("hetbh", "text"); // ��ͬ���
		fieldMap.put("hetlb", "text"); // ��ͬ���
		fieldMap.put("hetmk", "text"); // ��ͬģ��
		fieldMap.put("hetqxlx", "text"); // ��ͬ��������
		fieldMap.put("shengxrq", "2016-11-22"); // ��Ч����
		fieldMap.put("zhongzrq", "2016-11-22"); // ��ֹ����
		fieldMap.put("shiyqksrq", "2016-11-22"); // �����ڿ�ʼ����
		fieldMap.put("shiyqjsrq", "2016-11-22"); // �����ڽ�������
		fieldMap.put("hetqx", "text"); // ��ͬ����
		fieldMap.put("wufqx", "text"); // ��������
		fieldMap.put("weiyj", "number"); // ΥԼ��
		fieldMap.put("weiypf", "number"); // ΥԼ�⸶
		fieldMap.put("jianzdw", "text"); // ��֤��λ
		fieldMap.put("jianzrq", "2016-11-22"); // ��֤����
		fieldMap.put("qiandrq", "2016-11-22"); // ǩ������
		fieldMap.put("jiecrq", "2016-11-22"); // �������
		fieldMap.put("lianxqd", "text"); // ����ǩ��
		fieldMap.put("lianxqdcs", "number"); // ����ǩ������
		fieldMap.put("xuqyy", "text"); // ��ǩԭ��
		fieldMap.put("beiz", "text"); // ��ע

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
