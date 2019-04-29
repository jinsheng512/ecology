package greedc.ws.client.oa.workflowservice;

import greedc.ws.client.oa.ArrayOfWorkflowDetailTableInfo;
import greedc.ws.client.oa.ArrayOfWorkflowRequestTableField;
import greedc.ws.client.oa.ArrayOfWorkflowRequestTableRecord;
import greedc.ws.client.oa.CustomWorkflowRequestInfo;
import greedc.ws.client.oa.WorkflowBaseInfo;
import greedc.ws.client.oa.WorkflowDetailTableInfo;
import greedc.ws.client.oa.WorkflowMainTableInfo;
import greedc.ws.client.oa.WorkflowRequestTableField;
import greedc.ws.client.oa.WorkflowRequestTableRecord;
import greedc.ws.client.oa.WorkflowServiceNewLocator;
import greedc.ws.client.oa.WorkflowServiceNewPortType;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

public class WSContractService {
	
	public final static String WSDL_LOCATION = "http://192.168.130.107:8088/services/WorkflowServiceNew?";

	public static void main(String[] args) {
		try {
			WorkflowServiceNewPortType service = new WorkflowServiceNewLocator().getWorkflowServiceNewHttpPort(new URL(WSDL_LOCATION));
			String requestId = null;
			CustomWorkflowRequestInfo requestInfo = new WSContractService().getWorkflowRequestInfo();
			requestId = service.doCreateWorkflowRequest(requestInfo, 0);
			System.out.println("requestId=" + requestId);
		} catch (MalformedURLException e) {
		    System.out.println("WSDL地址解析失败[WSDL_LOCATION=" + WSDL_LOCATION + "]");
			e.printStackTrace();
		} catch (ServiceException e) {
		    System.out.println("服务创建失败");
			e.printStackTrace();
		} catch (RemoteException e) {
		    System.out.println("服务调用失败");
			e.printStackTrace();
		}
	}
	
	private CustomWorkflowRequestInfo getWorkflowRequestInfo() {
		CustomWorkflowRequestInfo requestInfo = new CustomWorkflowRequestInfo();
		requestInfo.setCreatorId("003460");  //工号
		requestInfo.setApplicantCode("003460");
		requestInfo.setCreateTime("2018-09-09");
		requestInfo.setRequestLevel("0");
		requestInfo.setRequestName("骑呗融租合同审批申请-2022-09-09");
		requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
		requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
		requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
		return requestInfo;
	}
	
	private WorkflowBaseInfo getWorkflowBaseInfo() {
		WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
//		workflowBase.setWorkflowId("1541");
		workflowBase.setWorkflowId("3244");
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
		fieldMap.put("pich", "1422338377"); // 表单编号
		fieldMap.put("shenqr", "empcode:default"); // 申请人
		fieldMap.put("shenqrq", "2016-11-22"); // 申请日期
		fieldMap.put("suosgs", "comcode:default"); // 申请人公司
		fieldMap.put("jingbr", "empcode:002589"); // 申请人职位
		fieldMap.put("suosbm", "depcode:default"); // 所属部门
		fieldMap.put("qibdkje", "16000"); // 骑呗打款金额
		fieldMap.put("shenqfkze", "200000"); // 申请放款总额
		fieldMap.put("qibylje", "12000"); // 骑呗预留金额
		fieldMap.put("bencdkje", "8000"); // 本次抵扣金额
		fieldMap.put("bencdkjyje", "4000"); // 本次抵扣金额
		fieldMap.put("haikje", "18400"); // 海控金额
		fieldMap.put("leijfk", "770000"); // 累计放款金额
		fieldMap.put("yuqjl", "1"); // 累计逾期笔数
		fieldMap.put("yuqje", "12000"); // 累计逾期金额
		return getFields(fieldMap);
	}

	private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
		WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[1];
		tableInfos[0] = new WorkflowDetailTableInfo();
		tableInfos[0].setTableDBName("formtable_main_133_dt1");
		tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
		return tableInfos;
	}

	/**
	 * 明细表字段。
	 * @return
	 */
	private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("heth", "QBRZZL20180906001"); // 合同号
		fieldMap.put("dingdh", "RZZL20180906001"); // 订单号
		fieldMap.put("goucr", "李四"); // 购车人
		fieldMap.put("dingdje", "220000"); // 出生日期
		fieldMap.put("jingxs", "1"); // 经销商id
		fieldMap.put("jingxsmc", "ABC摩托车贸易行"); // 经销商名称
		fieldMap.put("jingxskhh", "中国建设银行"); //经销商开户行
		fieldMap.put("jingxszh", "6222531700400000569"); // 经销商账号
		fieldMap.put("rongzzje", "200000"); // 融资总金额
		fieldMap.put("qibrzje", "16000"); // 试用期
		fieldMap.put("haikrzje", "184000"); // 工作经历
		fieldMap.put("fj", "http://jin.xxx.xxx/abc.pdf"); // 附件查看
		fieldMap.put("hetwb", "http://sheng.xxx.xxx/abc.pdf"); // 合同文本
	    
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
