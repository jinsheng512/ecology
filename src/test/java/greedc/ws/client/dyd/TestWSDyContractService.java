package greedc.ws.client.dyd;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

public class TestWSDyContractService {

    //public final static String WSDL_LOCATION = "http://192.168.130.194:8080/services/WorkflowServiceNew";

    public static void main(String[] args) {
//        Date ss = new Date();
//        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
//        
//        String time = format0.format(ss.getTime());
//        System.out.println(time);

        DydOADanjuParams danju = new DydOADanjuParams("DYd00001", "DYd00001", "张某某", 
                "123456789123456789", "珠海前山222号1-1-1",
                "500000", "24", "等额本息", "7%", "2019-01-01", "否", "李某某", "123456789123456789", "房产", "80", "珠海前山222号1-1-1", 
                "ZH-001001", "首押", "1000000", "200000", "12", "2050-01-01", "居住",
                "无风险","李某某", "建设银行",  "603300251254786", "500000", "0", "http://xxx.xxx.xxx/yyy/abc.pdf");
        
        List<DydOADanjuSpyjParams> dydOADanjuSpyjParamsList = new ArrayList<DydOADanjuSpyjParams>();
        DydOADanjuSpyjParams dydOADanjuSpyjParams = new DydOADanjuSpyjParams("信用状态", "良好", "http://xxx.xxx.xxx/yyy/abc.pdf", "通过");
        dydOADanjuSpyjParamsList.add(dydOADanjuSpyjParams);
        
        List<DydDanjuHtmxParams> dydOADanjuHtmxParamsList = new ArrayList<DydDanjuHtmxParams>();
        DydDanjuHtmxParams dydDanjuHtmxParams = new DydDanjuHtmxParams("借款合同", "DYD2019001", "500000", "7%", "24", "", "http://xxx.xxx.xxx/yyy/abc.pdf");
        
        TestWSDyContractService dydservice = new TestWSDyContractService(danju,dydOADanjuSpyjParamsList,dydOADanjuHtmxParamsList);
        
        String rs = dydservice.CallOAFlow();
        System.out.println(rs);
    }


    private DydOADanjuParams danjuParams;

    private List<DydOADanjuSpyjParams> DydOADanjuSpyjParamsList;

    private List<DydDanjuHtmxParams> DydOADanjuHtmxParamsList;

    public DydOADanjuParams getDanjuParams() {
        return danjuParams;
    }

    public void setDanjuParams(DydOADanjuParams danjuParams) {
        this.danjuParams = danjuParams;
    }

    public List getDydOADanjuSpyjParamsList() {
        return DydOADanjuSpyjParamsList;
    }

    public void setDydOADanjuSpyjParamsList(List<DydOADanjuSpyjParams> dydOADanjuSpyjParamsList) {
        DydOADanjuSpyjParamsList = dydOADanjuSpyjParamsList;
    }

    public List getDydOADanjuHtmxParamsList() {
        return DydOADanjuHtmxParamsList;
    }

    public void setDydOADanjuHtmxParamsList(List<DydDanjuHtmxParams> dydOADanjuHtmxParamsList) {
        DydOADanjuHtmxParamsList = dydOADanjuHtmxParamsList;
    }

    public TestWSDyContractService(DydOADanjuParams dydOADanjuParams, List<DydOADanjuSpyjParams> dydOADanjuSpyjParamsList,
            List<DydDanjuHtmxParams> dyddanjuHtmxParamsList) {
        this.setDanjuParams(dydOADanjuParams);
        this.setDydOADanjuHtmxParamsList(dyddanjuHtmxParamsList);
        this.setDydOADanjuSpyjParamsList(dydOADanjuSpyjParamsList);
    }

    public String CallOAFlow() {
        String requestId = null;
        String WSDL_LOCATION = "http://192.168.130.107:8088/services/WorkflowServiceNew";
        try {
            WorkflowServiceNewPortType service = new WorkflowServiceNewLocator().getWorkflowServiceNewHttpPort(new URL(WSDL_LOCATION));

            CustomWorkflowRequestInfo requestInfo = this.getWorkflowRequestInfo();
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
        System.out.println("requestId=" + requestId);
        return requestId;
    }

    private CustomWorkflowRequestInfo getWorkflowRequestInfo() {
        CustomWorkflowRequestInfo requestInfo = new CustomWorkflowRequestInfo();
        requestInfo.setCreatorId("002589");
        requestInfo.setApplicantCode("002589");
        requestInfo.setCreateTime(new Date().toString());
        requestInfo.setRequestLevel("0");
        requestInfo.setRequestName("房抵贷合同审批申请");

        requestInfo.setWorkflowBaseInfo(getWorkflowBaseInfo());
        requestInfo.setWorkflowMainTableInfo(getWorkflowMainTableInfo());
        requestInfo.setWorkflowDetailTableInfos(getWorkflowDetailTableInfos());
        return requestInfo;
    }

    private WorkflowBaseInfo getWorkflowBaseInfo() {
        WorkflowBaseInfo workflowBase = new WorkflowBaseInfo();
        workflowBase.setWorkflowId("3504");
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
        System.out.println("设置主表字段...orderid=" + this.getDanjuParams().getOrderID());

        
        fieldMap.put("suosgs", "comcode:default");
        fieldMap.put("suosbm", "comcode:default");
        fieldMap.put("shenqr", "comcode:default");
        
        fieldMap.put("biaodbh", this.getDanjuParams().getBiaodbh());
        fieldMap.put("jiekr", this.getDanjuParams().getJiekr());
        fieldMap.put("shenfz", this.getDanjuParams().getShenfz());
        fieldMap.put("zhuz", this.getDanjuParams().getZhuz());
        fieldMap.put("jiekje", this.getDanjuParams().getJiekje());
        fieldMap.put("jiekqx", this.getDanjuParams().getJiekqx());
        fieldMap.put("huankfs", this.getDanjuParams().getHuankfs());
        fieldMap.put("daikll", this.getDanjuParams().getDaikll());
        
        fieldMap.put("shenqsj", this.getDanjuParams().getShenqsj());
        fieldMap.put("shifdb", this.getDanjuParams().getShifdb());
        fieldMap.put("diywsyr", this.getDanjuParams().getDiywsyr());
        fieldMap.put("diyrsfz", this.getDanjuParams().getDiyrsfz());
        fieldMap.put("diywmc", this.getDanjuParams().getDiywmc());

        fieldMap.put("fangcmj", this.getDanjuParams().getFangcmj());
        fieldMap.put("diywzld", this.getDanjuParams().getDiywzld());
        fieldMap.put("chanqzh", this.getDanjuParams().getChanqzh());
        fieldMap.put("diylx", this.getDanjuParams().getDiylx());
        fieldMap.put("diywpgj", this.getDanjuParams().getDiywpgj());

        fieldMap.put("yiwqtdy", this.getDanjuParams().getYiwqtdy());
        fieldMap.put("yisynx", this.getDanjuParams().getYisynx());
        fieldMap.put("toursyzzr", this.getDanjuParams().getToursyzzr());
        fieldMap.put("fangwyt", this.getDanjuParams().getFangwyt());
        fieldMap.put("fengkzhyj", this.getDanjuParams().getFengkzhyj());

        fieldMap.put("fangkzhmc", this.getDanjuParams().getFangkzhmc());
        fieldMap.put("fangkyh", this.getDanjuParams().getFangkyh());
        fieldMap.put("zhangh", this.getDanjuParams().getZhangh());
        fieldMap.put("hetfkje", this.getDanjuParams().getHetfkje());
        fieldMap.put("yifkje", this.getDanjuParams().getYifkje());
        
        fieldMap.put("fuj", this.getDanjuParams().getFuj());
      

        return getFields(fieldMap);
    }

    private WorkflowDetailTableInfo[] getWorkflowDetailTableInfo() {
        WorkflowDetailTableInfo[] tableInfos = new WorkflowDetailTableInfo[2];
        tableInfos[0] = new WorkflowDetailTableInfo();
        tableInfos[0].setTableDBName("formtable_main_123_dt1");
        tableInfos[0].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords());
        
        tableInfos[1] = new WorkflowDetailTableInfo();
        tableInfos[1].setTableDBName("formtable_main_123_dt2");
        tableInfos[1].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords1());
        
        tableInfos[1] = new WorkflowDetailTableInfo();
//      tableInfos[1].setTableDBName("formtable_main_123_dt3");
//      tableInfos[1].setWorkflowRequestTableRecords(getWorkflowRequestTableRecords2());
        
        return tableInfos;
    }

    /**
     * 明细表字段。
     * @return
     */
    private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords() {
        WorkflowRequestTableRecord[] records;
        ArrayOfWorkflowRequestTableRecord record = null;
        if (this.getDydOADanjuSpyjParamsList() != null && this.getDydOADanjuSpyjParamsList().size() > 0) {
            System.out.println("审批明细数据条数: " + this.getDydOADanjuSpyjParamsList().size());
            //records = new WorkflowRequestTableRecord[1];
            records = new WorkflowRequestTableRecord[this.getDydOADanjuSpyjParamsList().size()];
            for (int i = 0; i < this.getDydOADanjuSpyjParamsList().size(); i++) {

                ArrayOfWorkflowRequestTableField workflowRequestTableFields = new ArrayOfWorkflowRequestTableField();
                Map<String, String> fieldMap = new HashMap<String, String>();
                DydOADanjuSpyjParams SpyjParams = (DydOADanjuSpyjParams)this.getDydOADanjuSpyjParamsList().get(i);
                //System.out.println("设置明细字段...contractno=" + SpyjParams.getFengkscx());


                fieldMap.put("fengkscx", SpyjParams.getFengkscx()); 
                fieldMap.put("shencjg", SpyjParams.getShencjg()); 
                fieldMap.put("fujzl", SpyjParams.getFujzl()); 
                fieldMap.put("shenpyj", SpyjParams.getShenpyj()); 

                workflowRequestTableFields.setWorkflowRequestTableField(getFields(fieldMap));
                records[i] = new WorkflowRequestTableRecord();
                records[i].setWorkflowRequestTableFields(workflowRequestTableFields);

            }
            record = new ArrayOfWorkflowRequestTableRecord();
            record.setWorkflowRequestTableRecord(records);
        }

        return record;
    }
    
    /**
     * 明细表字段。
     * @return
     */
    private ArrayOfWorkflowRequestTableRecord getWorkflowRequestTableRecords1() {
        WorkflowRequestTableRecord[] records;
        ArrayOfWorkflowRequestTableRecord record = null;
        if (this.getDydOADanjuHtmxParamsList() != null && this.getDydOADanjuHtmxParamsList().size() > 0) {
            System.out.println("合同明细数据条数: " + this.getDydOADanjuHtmxParamsList().size());
            records = new WorkflowRequestTableRecord[this.getDydOADanjuHtmxParamsList().size()];
            for (int i = 0; i < this.getDydOADanjuHtmxParamsList().size(); i++) {

                ArrayOfWorkflowRequestTableField workflowRequestTableFields = new ArrayOfWorkflowRequestTableField();
                Map<String, String> fieldMap = new HashMap<String, String>();
                DydDanjuHtmxParams HtmxParams = (DydDanjuHtmxParams)this.getDydOADanjuHtmxParamsList().get(i);

                fieldMap.put("hetlx", HtmxParams.getHetlx()); 
                fieldMap.put("hetbh", HtmxParams.getHetbh()); 
                fieldMap.put("hetje", HtmxParams.getHetje()); 
                fieldMap.put("lil", HtmxParams.getLil()); 
                fieldMap.put("jiekqx", HtmxParams.getJiekqx()); 
                fieldMap.put("daysj", HtmxParams.getDaysj()); 
                fieldMap.put("hetdzb", HtmxParams.getHetdzb()); 

                workflowRequestTableFields.setWorkflowRequestTableField(getFields(fieldMap));
                records[i] = new WorkflowRequestTableRecord();
                records[i].setWorkflowRequestTableFields(workflowRequestTableFields);

            }
            record = new ArrayOfWorkflowRequestTableRecord();
            record.setWorkflowRequestTableRecord(records);
        }

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
