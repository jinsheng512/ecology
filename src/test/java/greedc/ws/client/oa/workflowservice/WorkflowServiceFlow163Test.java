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

public class WorkflowServiceFlow163Test {
	
	private final static Log log = new Log(WorkflowServiceFlow163Test.class);
	public final static String WSDL_LOCATION = "http://192.168.130.194:8080/services/WorkflowServiceNew";

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
		requestInfo.setRequestName("н�귢������-������-2016-11-22");
		requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
		requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
		// requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
		return requestInfo;
	}
	
	private WorkflowBaseInfo getWorkflowBaseInfo() {
		WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
		workflowBase.setWorkflowId("163");
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
		fieldMap.put("biaodbh", "EAS-BILL-CODE"); // �?���
		fieldMap.put("shenqr", "empcode:default"); // ������
		fieldMap.put("shenqrq", "2016-11-22"); // ��������
		fieldMap.put("suosgs", "comcode:default"); // �����˹�˾
		fieldMap.put("suosbm", "depcode:default"); // ��������
		fieldMap.put("zhiw", "poscode:default"); // ������ְλ
		fieldMap.put("entries", "[{\"FSAL006\":845,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����ΰ\",\"FSAL004\":845,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�ۺϰ칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�ۺϰ칫��\",\"FFSAL213\":45,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�ۺϰ칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222600780006331644\"},{\"FSAL006\":4045,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"�ӽ���\",\"FSAL004\":4045,\"FFSAL210\":3000,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":800,\"FFSAL378\":0,\"orgNumber\":\"�麣����������۷�չ���޹�˾\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾\",\"FFSAL213\":45,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�麣����������۷�չ���޹�˾\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222600780007866655\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"¬����\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780002565340\"},{\"FSAL006\":2400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":2400,\"FFSAL210\":1800,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":400,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780000328311\"},{\"FSAL006\":2445,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":2445,\"FFSAL210\":1800,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":400,\"FFSAL378\":0,\"orgNumber\":\"�ۺϰ칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�ۺϰ칫��\",\"FFSAL213\":45,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�ۺϰ칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780000327669\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780000328816\"},{\"FSAL006\":3045,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"�쾧��\",\"FSAL004\":3045,\"FFSAL210\":1800,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":400,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":45,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780000328600\"},{\"FSAL006\":2445,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����\",\"FSAL004\":2445,\"FFSAL210\":1800,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":400,\"FFSAL378\":0,\"orgNumber\":\"����\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_����\",\"FFSAL213\":45,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"����\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"62225807801171619\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�ۺϰ칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�ۺϰ칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�ۺϰ칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780001910166\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"�ƹ�Ө\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780003627172\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"�ξ���\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780003627149\"},{\"FSAL006\":4000,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����\",\"FSAL004\":4000,\"FFSAL210\":3000,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":800,\"FFSAL378\":0,\"orgNumber\":\"�麣����������۷�չ���޹�˾\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�麣����������۷�չ���޹�˾\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222600780000539838\"},{\"FSAL006\":4600,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"��ǿ\",\"FSAL004\":4600,\"FFSAL210\":3500,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":900,\"FFSAL378\":0,\"orgNumber\":\"�����ز�ɷ����޹�˾��������\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�����ز�ɷ����޹�˾��������\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�����ز�ɷ����޹�˾��������\",\"FSAL029\":0,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780000331364\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"����\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_����\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"����\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780004177169\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"��ҵ��\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780001910125\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"������Ŀ��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_������Ŀ��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"������Ŀ��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780001910083\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780002666031\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"̷����\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780001924522\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����ʤ\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�߻���\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�߻���\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�߻���\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780000555665\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780001787135\"},{\"FSAL006\":1445,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"��־��\",\"FSAL004\":1445,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":45,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780002094978\"},{\"FSAL006\":1800,\"FFSAL349\":400,\"FSAL007\":0,\"FPER001\":\"�³�\",\"FSAL004\":1800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�ۺϰ칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�ۺϰ칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�ۺϰ칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780001755686\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780005770228\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"������Ŀ��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_������Ŀ��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"������Ŀ��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780002856582\"},{\"FSAL006\":1800,\"FFSAL349\":400,\"FSAL007\":0,\"FPER001\":\"����\",\"FSAL004\":1800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"���޸��ϵ���Ŀ��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣������ͷ��չ���޹�˾ _������Ŀ��_���޸��ϵ���Ŀ��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"���޸��ϵ���Ŀ��\",\"FSAL029\":0,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780002665512\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"���������Ŀ��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_������Ŀ��_���������Ŀ��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"���������Ŀ��\",\"FSAL029\":0,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780002835032\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"������\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"������������ͷ��Ŀ��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_������Ŀ��_������������ͷ��Ŀ��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"������������ͷ��Ŀ��\",\"FSAL029\":0,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780005925467\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����Ӣ\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780005769188\"},{\"FSAL006\":4000,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"³��\",\"FSAL004\":4000,\"FFSAL210\":3000,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":800,\"FFSAL378\":0,\"orgNumber\":\"�����ز�ɷ����޹�˾��������\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�����ز�ɷ����޹�˾��������\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�����ز�ɷ����޹�˾��������\",\"FSAL029\":0,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780004888872\"},{\"FSAL006\":1400,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"����\",\"FSAL004\":1400,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":600,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"��Ǩ�칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_��Ǩ�칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"��Ǩ�칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222600780010161920\"},{\"FSAL006\":800,\"FFSAL349\":0,\"FSAL007\":0,\"FPER001\":\"�ܺ���\",\"FSAL004\":800,\"FFSAL210\":300,\"FSAL005\":0,\"FFSAL212\":0,\"FFSAL211\":300,\"FFSAL378\":0,\"orgNumber\":\"�ۺϰ칫��\",\"FFSAL214\":200,\"FPER003\":\"�����ز�ɷ����޹�˾_�麣�����������޹�˾_�麣����������۷�չ���޹�˾_�ۺϰ칫��\",\"FFSAL213\":0,\"FFSAL216\":0,\"FSAL014\":0,\"orgName\":\"�ۺϰ칫��\",\"FSAL029\":3500,\"FSAL027\":0,\"FFSAL248\":0,\"FPER025\":\"6222620780004792892\"}]");

		return getFields(fieldMap);
	}

	private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
		WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[1];
		tableInfos[0] = new WorkflowDetailTableInfo();
		tableInfos[0].setTableDBName("FORMTABLE_MAIN_44_DT1");
		tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
		return tableInfos;
	}

	/**
	 * ��ϸ���ֶΡ�
	 * @return
	 */
	private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("gerzh", "text"); // �����˺�
		fieldMap.put("zhiyxm", "text"); // ְԱ����
		fieldMap.put("bummc", "text"); // �������
		fieldMap.put("tongxbt", "89"); // ͨѶ����
		fieldMap.put("yingfhj", "90"); // Ӧ���ϼ�
		fieldMap.put("dusznbt", "91"); // ������Ů����
		fieldMap.put("liuyjbt", "92"); // ��һ�ڲ���
		fieldMap.put("zufbt", "93"); // �ⷿ����
		fieldMap.put("zhicbt", "94"); // ְ�Ʋ���
		fieldMap.put("bufjt", "95"); // ��������
		fieldMap.put("koukhj", "96"); // �ۿ�ϼ�
		fieldMap.put("shifhj", "97"); // ʵ���ϼ�
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
