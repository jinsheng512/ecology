package greedc.budgetplan.entities;

import javax.persistence.Table;

@Table(name = "CV_PLANSUMMARY", catalog = "ecology")
public class CVPlanSummary {

	private Integer yearMonth;
	private Integer subcompanyName;
	private Integer lastName;
	private Integer submitDate;
	private Integer planAmountIn;
	private Integer spendamountIn;
	private Integer planAmounText;
	private Integer spendAmounText;
	
	public Integer getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(Integer yearMonth) {
		this.yearMonth = yearMonth;
	}
	public Integer getSubcompanyName() {
		return subcompanyName;
	}
	public void setSubcompanyName(Integer subcompanyName) {
		this.subcompanyName = subcompanyName;
	}
	public Integer getLastName() {
		return lastName;
	}
	public void setLastName(Integer lastName) {
		this.lastName = lastName;
	}
	public Integer getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Integer submitDate) {
		this.submitDate = submitDate;
	}
	public Integer getPlanAmountIn() {
		return planAmountIn;
	}
	public void setPlanAmountIn(Integer planAmountIn) {
		this.planAmountIn = planAmountIn;
	}
	public Integer getSpendamountIn() {
		return spendamountIn;
	}
	public void setSpendamountIn(Integer spendamountIn) {
		this.spendamountIn = spendamountIn;
	}
	public Integer getPlanAmounText() {
		return planAmounText;
	}
	public void setPlanAmounText(Integer planAmounText) {
		this.planAmounText = planAmounText;
	}
	public Integer getSpendAmounText() {
		return spendAmounText;
	}
	public void setSpendAmounText(Integer spendAmounText) {
		this.spendAmounText = spendAmounText;
	}
}
