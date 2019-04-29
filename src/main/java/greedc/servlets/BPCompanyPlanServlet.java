package greedc.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import weaver.soa.workflow.request.RequestService;
import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.hrm.entities.HrmResource;
import ecustom.ecology8.webservice.workflow.WSField;
import ecustom.ecology8.webservice.workflow.WSRequestInfo;
import ecustom.ecology8.webservice.workflow.WSRow;
import ecustom.ecology8.webservice.workflow.WSTable;
import ecustom.ecology8.webservice.workflow.WorkflowServiceXmlImpl;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.budgetplan.entities.UFBPCompany;
import greedc.budgetplan.entities.UFBPCompanyPlan;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1_Year;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1_Year2;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1_Year3;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1_Year4;
import greedc.budgetplan.entities.UFBPCompanyPlanDt1_Year5;
import greedc.budgetplan.entities.UFBPCompanyPlanDt2;
import greedc.budgetplan.entities.UFBPCompanyPlanDt3;
import greedc.budgetplan.entities.UFBPCompanyPlanDt4;
import greedc.budgetplan.entities.UFBPCompanyPlanDt5;
import greedc.budgetplan.entities.UFBPConfig;
import greedc.services.BPCompanyPlanService;

/**
 * 公司数据填报请求服务类。
 * @author William
 */
public class BPCompanyPlanServlet extends BaseServlet {

	private final static Logger log = Logger.getLogger(BPCompanyPlanServlet.class);

	public Map<String, Object> getBPCompanyPlan() {
		int companyId = getParameterIntegerCK("companyId");
		String yearMonth = getParameterCK("yearMonth");
		
		String whereSql = "COMPANY=? AND YEARMONTH=?";
		UFBPCompanyPlan companyPlan = RecordUtil.find(UFBPCompanyPlan.class,
				whereSql, companyId, yearMonth);
		
		List<UFBPCompanyPlanDt1> dt1 = RecordUtil.search(UFBPCompanyPlanDt1.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt2> dt2 = RecordUtil.search(UFBPCompanyPlanDt2.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt3> dt3 = RecordUtil.search(UFBPCompanyPlanDt3.class,
				"MAINID=?", companyPlan.getId());
		
		
		List<UFBPCompanyPlanDt4> dt4 = RecordUtil.search(UFBPCompanyPlanDt4.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt5> dt5 = RecordUtil.search(UFBPCompanyPlanDt5.class,
				"MAINID=?", companyPlan.getId());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("main", companyPlan);
		resultMap.put("dt1", dt1);
		resultMap.put("dt2", dt2);
		resultMap.put("dt3", dt3);
		resultMap.put("dt4", dt4);
		resultMap.put("dt5", dt5);
		
		return resultMap;
	}
	
	/**
	 * 获取某公司的指定期间的主表信息
	 */
	public UFBPCompanyPlan getCompanyPlanMain() {
		int companyId = getParameterIntegerCK("companyId");
		String yearMonth = getParameterCK("yearMonth");
		
		String whereSql = "COMPANY=? AND YEARMONTH=?";
		UFBPCompanyPlan companyPlan = RecordUtil.find(UFBPCompanyPlan.class,
				whereSql, companyId, yearMonth);
		return companyPlan;
	}
	
	public Response createRequest() throws Exception {
		
		int billId = getParameterIntegerCK("billid");
		
		UFBPCompanyPlan companyPlan = RecordUtil.findById(UFBPCompanyPlan.class, billId);
		
		if (companyPlan  == null) {
			throw new RuntimeException("填报数据不存在，UF_BPCOMPANYPLAN.ID = " + billId);
		}
		if (companyPlan.getStatus() != 0 && companyPlan.getStatus() != 4) {
			throw new RuntimeException("审批流程已经存在，requestId = " + companyPlan.getRequestId() + ", status = " + companyPlan.getStatus());
		}
		
		List<UFBPCompanyPlanDt1> dt1List = RecordUtil.search(UFBPCompanyPlanDt1.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt2> dt2List = RecordUtil.search(UFBPCompanyPlanDt2.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt3> dt3List = RecordUtil.search(UFBPCompanyPlanDt3.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt4> dt4List = RecordUtil.search(UFBPCompanyPlanDt4.class,
				"MAINID=?", companyPlan.getId());
		
		List<UFBPCompanyPlanDt5> dt5List = RecordUtil.search(UFBPCompanyPlanDt5.class,
				"MAINID=?", companyPlan.getId());
	

		WSRequestInfo wsRequestInfo = new WSRequestInfo();
		HrmResource creator = RecordUtil.findById(HrmResource.class, companyPlan.getSubmitName());
		
		UFBPConfig config = RecordUtil.find(UFBPConfig.class,
				"ISOPEN=? ORDER BY DATAORDER", 1);

		//流程主信息
		wsRequestInfo.setCreateTime(CustomUtil.dateFormat("yyyy-MM-dd"));
		wsRequestInfo.setCreatorId(creator.getId());
		wsRequestInfo.setIsNextFlow(1);
		wsRequestInfo.setRequestName("BP09-数据填报审批流程-" + creator.getLastName() + "-" + wsRequestInfo.getCreateTime());
		wsRequestInfo.setWorkflowId(config.getCompanyPlanId());
		
		String whereSql = "COMPANY=?";
		UFBPCompany company = RecordUtil.find(UFBPCompany.class,
				whereSql, companyPlan.getCompany());
		if (company.getAuditUser() == null) {
			throw new RuntimeException("该公司没有该维护领导");
		}

		// 设置表信息
		wsRequestInfo.setMainFields(new WSField[] {
				new WSField("month", "" + companyPlan.getMonth()),
				new WSField("yearMonth", companyPlan.getYearMonth()),
				new WSField("company", "" + companyPlan.getCompany()),
				new WSField("submitDate", companyPlan.getSubmitDate()),	//填报日期
				new WSField("submitName", "" + companyPlan.getSubmitName()),	//填报人
				new WSField("auditDate", companyPlan.getAuditDate()),	//审批日期
				new WSField("auditName", "" + company.getAuditUser()),	//审批人
				new WSField("submitEndDate", companyPlan.getSubmitEndDate()),	//填报截止日期
				new WSField("status", "" + companyPlan.getStatus()),	//状态
				new WSField("lastMonthBegin", "" + companyPlan.getLastMonthBegin()),	//上月期初余额
				new WSField("thisMonthPlan", "" + companyPlan.getThisMonthPlan()),	//本月计划额
				new WSField("bussInOverReason", companyPlan.getBussInOverReason()),	//外部经营收入超额原因
				new WSField("fnaInOverReason", companyPlan.getFnaInOverReason()),	//筹资收入超额原因
				new WSField("bussOutOverReason", companyPlan.getBussOutOverReason()),	//经营性支出超额原因
				new WSField("projOutOverReason", companyPlan.getProjOutOverReason()),	//项目投资支出超额原因
				new WSField("fnaOutOverReason", companyPlan.getFnaOutOverReason()),	//筹资支出超额原因
				new WSField("bussInOverDiff",""+ companyPlan.getBussInOverDiff()),	//外部经营收入超额
				new WSField("fnaInOverDiff", ""+ companyPlan.getFnaInOverDiff()),	//筹资收入超额
				new WSField("bussOutOverDiff","" + companyPlan.getBussOutOverDiff()),	//经营性支出超额
				new WSField("projOutOverDiff","" + companyPlan.getProjOutOverDiff()),	//项目投资支出超额
				new WSField("fnaOutOverDiff" ,"" + companyPlan.getFnaOutOverDiff()),	//筹资支出超额
				new WSField("block" ,"" + companyPlan.getBlock()),	//筹资支出超额
		});

		//取明细信息
		WSTable[] detailTables = new WSTable[5];
		
		// 明细表1
		detailTables[0] = new WSTable();
		WSRow[] rows = new WSRow[dt1List.size()];
		for ( int i = 0; i< dt1List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("accountId",  "" + dt1List.get(i).getAccountId()), // 科目名称
					new WSField("lastPlan",  "" + dt1List.get(i).getLastPlan()),  // 上月计划额
					new WSField("lastHappend", "" + dt1List.get(i).getLastHappend()), // 上月发生额
					new WSField("thisPlan",  "" + dt1List.get(i).getThisPlan()) ,  // 本月计划额
					new WSField("difference",  "" + dt1List.get(i).getDifference()), // 差额
					new WSField("remarks",  dt1List.get(i).getRemarks()),  // 备注
			
					
			};
			rows[i].setFields(rowFields);
		}
		detailTables[0].setRows(rows);
		
		// 明细表2
		detailTables[1] = new WSTable();
		rows = new WSRow[dt2List.size()];
		for ( int i = 0; i< dt2List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("accountId",  "" + dt2List.get(i).getAccountId()), // 科目名称
					new WSField("lastPlan",  "" + dt2List.get(i).getLastPlan()),  // 上月计划额
					new WSField("lastHappend", "" + dt2List.get(i).getLastHappend()), // 上月发生额
					new WSField("thisPlan",  "" + dt2List.get(i).getThisPlan()) ,  // 本月计划额
					new WSField("difference",  "" + dt2List.get(i).getDifference()), // 差额
					new WSField("remarks",  dt2List.get(i).getRemarks()),  // 备注
					
			};
			rows[i].setFields(rowFields);
		}
		detailTables[1].setRows(rows);
		
		// 明细表3
		detailTables[2] = new WSTable();
		rows = new WSRow[dt3List.size()];
		for ( int i = 0; i< dt3List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("accountId",  "" + dt3List.get(i).getAccountId()), // 科目名称
					new WSField("lastPlan",  "" + dt3List.get(i).getLastPlan()),  // 上月计划额
					new WSField("lastHappend", "" + dt3List.get(i).getLastHappend()), // 上月发生额
					new WSField("thisPlan",  "" + dt3List.get(i).getThisPlan()) ,  // 本月计划额
					new WSField("difference",  "" + dt3List.get(i).getDifference()), // 差额
					new WSField("remarks",  dt3List.get(i).getRemarks()),  // 备注
					
			};
			rows[i].setFields(rowFields);
		}
		detailTables[2].setRows(rows);
		
		// 明细表4
		detailTables[3] = new WSTable();
		rows = new WSRow[dt4List.size()];
		for ( int i = 0; i< dt4List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("accountId",  "" + dt4List.get(i).getAccountId()), // 科目名称
					new WSField("lastPlan",  "" + dt4List.get(i).getLastPlan()),  // 上月计划额
					new WSField("lastHappend", "" + dt4List.get(i).getLastHappend()), // 上月发生额
					new WSField("thisPlan",  "" + dt4List.get(i).getThisPlan()) ,  // 本月计划额
					new WSField("difference",  "" + dt4List.get(i).getDifference()), // 差额
					new WSField("remarks",  dt4List.get(i).getRemarks()),  // 备注
					
			};
			rows[i].setFields(rowFields);
		}
		detailTables[3].setRows(rows);
		
		// 明细表5
		detailTables[4] = new WSTable();
		rows = new WSRow[dt5List.size()];
		for ( int i = 0; i< dt5List.size();i++ ) {
			rows[i] = new WSRow();
			WSField[] rowFields = new WSField[] {
					new WSField("accountId",  "" + dt5List.get(i).getAccountId()), // 科目名称
					new WSField("lastPlan",  "" + dt5List.get(i).getLastPlan()),  // 上月计划额
					new WSField("lastHappend", "" + dt5List.get(i).getLastHappend()), // 上月发生额
					new WSField("thisPlan",  "" + dt5List.get(i).getThisPlan()) ,  // 本月计划额
					new WSField("difference",  "" + dt5List.get(i).getDifference()), // 差额
					new WSField("remarks",  dt5List.get(i).getRemarks()),  // 备注
					
			};
			rows[i].setFields(rowFields);
		}
		detailTables[4].setRows(rows);
		
		wsRequestInfo.setDetailTables(detailTables);

		WorkflowServiceXmlImpl wfService = new WorkflowServiceXmlImpl();
		RequestService requestService = new RequestService();
		int requestId = Integer.parseInt(requestService.createRequest(wfService.toRequestInfo(wsRequestInfo)));
		log.info("创建流程成功，requestId = " + requestId);
		
		if(requestId <= 0 ){
			throw new Exception("创建流程失败："  + requestId);
		}
		
		RecordUtil.executeUpdate("UPDATE UF_BPCOMPANYPLAN SET REQUESTID=? , STATUS=? WHERE ID=?", requestId,1,billId);
	
		return new Response(true, "创建流程成功，RequestId = " + requestId);
	}
	
	public Map<String, Object> getBPCompanyPlanYear() {
		int companyId = getParameterIntegerCK("companyId");
		String year = getParameterCK("year");
		
		List<UFBPCompanyPlanDt1_Year> dt1 = RecordUtil.search(UFBPCompanyPlanDt1_Year.class,
				"STATUS=3 AND COMPANY=? AND YEAR=? ", companyId,year);
		
		List<UFBPCompanyPlanDt1_Year2> dt2 = RecordUtil.search(UFBPCompanyPlanDt1_Year2.class,
				"STATUS=3 AND COMPANY=? AND YEAR=? ", companyId,year);
		
		List<UFBPCompanyPlanDt1_Year3> dt3 = RecordUtil.search(UFBPCompanyPlanDt1_Year3.class,
				"STATUS=3 AND COMPANY=? AND YEAR=? ", companyId,year);
		
		List<UFBPCompanyPlanDt1_Year4> dt4 = RecordUtil.search(UFBPCompanyPlanDt1_Year4.class,
				"STATUS=3 AND COMPANY=? AND YEAR=? ", companyId,year);
		
		List<UFBPCompanyPlanDt1_Year5> dt5 = RecordUtil.search(UFBPCompanyPlanDt1_Year5.class,
				"STATUS=3 AND COMPANY=? AND YEAR=? ", companyId,year);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("dt1", dt1);
		resultMap.put("dt2", dt2);
		resultMap.put("dt3", dt3);
		resultMap.put("dt4", dt4);
		resultMap.put("dt5", dt5);
		
		return resultMap;
	}
	
	/**
	 * 数据填报反审核的功能。
	 * @return
	 * @throws Exception 
	 */
	public Response cancelAudit() throws Exception {
		int billId = getParameterIntegerCK("billid");
		BPCompanyPlanService service = new BPCompanyPlanService();
		service.cancelAudit(billId);
		return new Response(true, "反审核成功！");
	}
}
