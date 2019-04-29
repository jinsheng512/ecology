package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "uf_BPCompanyPlan_dt5", catalog = "ecology")
public class UFBPCompanyPlanDt5 {
	
	@Id
	private Integer id;             //id
	private Integer mainId;         //主表id
	private Integer accountId;      //科目名称
	private Double lastPlan;        //上月计划额
	private Double lastHappend;     //上月发生额
	private Double thisPlan;        //本月计划额
	private String difference;      //差额
	private String remarks;         //备注
	

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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
