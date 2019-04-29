package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BPCOMPANYPLAN", catalog = "ecology")
public class UFBPCompanyPlan {

	@Id
	private Integer id;                     //主表id
	private Integer requestId;	// 关联审批流程的RequestID
	private Integer year;                   //年份
	private Integer month;                  //月份
	private String yearMonth;               //期间
	private String lastYearMonth;           // 上月期间
	private Integer company;                //公司名称
	private String submitDate;             //填报日期
	private Integer submitName;             //填报人
	private String auditDate;              //审批日期
	private Integer auditName;              //审批人
	private String submitEndDate;          //填报截止日期
	private Integer status;                 //状态
	private Double lastMonthBegin;          //上月期初余额
	private Double thisMonthPlan;           //本月计划额
	private String bussInOverReason;        //外部经营收入超额原因
	private String fnaInOverReason;         //筹资收入超额原因
	private String bussOutOverReason;       //经营性支出超额原因
	private String projOutOverReason;       //项目投资支出超额原因
	private String fnaOutOverReason;        //筹资支出超额原因
	private Double bussInOverDiff;	//外部经营收入超额额度
	private Double fnaInOverDiff;	//筹资收入超额额度
	private Double bussOutOverDiff;	//经营性支出超额额度
	private Double projOutOverDiff;	//项目投资支出超额额度
	private Double fnaOutOverDiff;	//筹资支出超额额度
	private Integer block;	//所属板块

	
	
	public Integer getBlock() {
		return block;
	}
	public void setBlock(Integer block) {
		this.block = block;
	}
	public Double getBussInOverDiff() {
		return bussInOverDiff;
	}
	public void setBussInOverDiff(Double bussInOverDiff) {
		this.bussInOverDiff = bussInOverDiff;
	}
	public Double getFnaInOverDiff() {
		return fnaInOverDiff;
	}
	public void setFnaInOverDiff(Double fnaInOverDiff) {
		this.fnaInOverDiff = fnaInOverDiff;
	}
	public Double getBussOutOverDiff() {
		return bussOutOverDiff;
	}
	public void setBussOutOverDiff(Double bussOutOverDiff) {
		this.bussOutOverDiff = bussOutOverDiff;
	}
	public Double getProjOutOverDiff() {
		return projOutOverDiff;
	}
	public void setProjOutOverDiff(Double projOutOverDiff) {
		this.projOutOverDiff = projOutOverDiff;
	}
	public Double getFnaOutOverDiff() {
		return fnaOutOverDiff;
	}
	public void setFnaOutOverDiff(Double fnaOutOverDiff) {
		this.fnaOutOverDiff = fnaOutOverDiff;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public Integer getSubmitName() {
		return submitName;
	}
	public void setSubmitName(Integer submitName) {
		this.submitName = submitName;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public Integer getAuditName() {
		return auditName;
	}
	public void setAuditName(Integer auditName) {
		this.auditName = auditName;
	}
	public String getSubmitEndDate() {
		return submitEndDate;
	}
	public void setSubmitEndDate(String submitEndDate) {
		this.submitEndDate = submitEndDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getLastMonthBegin() {
		return lastMonthBegin;
	}
	public void setLastMonthBegin(Double lastMonthBegin) {
		this.lastMonthBegin = lastMonthBegin;
	}
	public Double getThisMonthPlan() {
		return thisMonthPlan;
	}
	public void setThisMonthPlan(Double thisMonthPlan) {
		this.thisMonthPlan = thisMonthPlan;
	}
	public String getBussInOverReason() {
		return bussInOverReason;
	}
	public void setBussInOverReason(String bussInOverReason) {
		this.bussInOverReason = bussInOverReason;
	}
	public String getFnaInOverReason() {
		return fnaInOverReason;
	}
	public void setFnaInOverReason(String fnaInOverReason) {
		this.fnaInOverReason = fnaInOverReason;
	}
	public String getBussOutOverReason() {
		return bussOutOverReason;
	}
	public void setBussOutOverReason(String bussOutOverReason) {
		this.bussOutOverReason = bussOutOverReason;
	}
	public String getProjOutOverReason() {
		return projOutOverReason;
	}
	public void setProjOutOverReason(String projOutOverReason) {
		this.projOutOverReason = projOutOverReason;
	}
	public String getFnaOutOverReason() {
		return fnaOutOverReason;
	}
	public void setFnaOutOverReason(String fnaOutOverReason) {
		this.fnaOutOverReason = fnaOutOverReason;
	}
	public String getLastYearMonth() {
		return lastYearMonth;
	}
	public void setLastYearMonth(String lastYearMonth) {
		this.lastYearMonth = lastYearMonth;
	}
	
}
