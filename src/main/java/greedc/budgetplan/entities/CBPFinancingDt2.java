package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "C_BP_UF_BPFINANCING_DT2", catalog = "ecology")
public class CBPFinancingDt2 {

	@Id
	private Integer id;	//	内置字段
	private Integer mainId;	//	内置字段
	private String accountId;	// 科目	
	private Double lastPlan;	// 上月计划额
	private Double lastHappend;	// 上月发生额
	private Double thisPlan;	// 本月计划额
	private Double difference;	// 差额
	private String remarks;	// 备注
	private String accountName;	// 科目名称
	
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
	public Double getDifference() {
		return difference;
	}
	public void setDifference(Double difference) {
		this.difference = difference;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	public String getAccountId() {
		return accountId;
	}
	public int getAccountIdInt() {
		return accountId == null || accountId.isEmpty() ? 0 : Integer.parseInt(accountId);
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
