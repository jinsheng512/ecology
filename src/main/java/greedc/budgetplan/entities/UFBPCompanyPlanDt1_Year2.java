package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CV_BPCOMPANYPLANDT2_YEAR", catalog = "ecology")
public class UFBPCompanyPlanDt1_Year2 {
	
	@Id
	private String year;          //年份
	private Integer status;         //审批状态
	private Integer company;        //公司id 
	private Integer accountId;      //科目名称
	private Double lastPlan;        //上月计划额
	private Double lastHappend;     //上月发生额
	private Double thisPlan;        //本月计划额
	private String difference;      //差额
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Double getLastPlan() {
		return lastPlan;
	}
	public void setLastPlan(Double lastPlan) {
		this.lastPlan = lastPlan;
	}
	public Double getLastHappend() {
		return lastHappend;
	}
	public void setLastHappend(Double lastHappend) {
		this.lastHappend = lastHappend;
	}
	public Double getThisPlan() {
		return thisPlan;
	}
	public void setThisPlan(Double thisPlan) {
		this.thisPlan = thisPlan;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	
 
}
