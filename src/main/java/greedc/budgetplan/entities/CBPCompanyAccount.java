package greedc.budgetplan.entities;


import javax.persistence.Table;

@Table(name = "C_BP_COMPANYACCOUNT", catalog = "ecology")
public class CBPCompanyAccount {
	
	private Integer accountL1;
	private Integer accountL2;
	private String accountName;
	private Integer company;
	private Double dataOrder;
	
	public Integer getAccountL1() {
		return accountL1;
	}
	public void setAccountL1(Integer accountL1) {
		this.accountL1 = accountL1;
	}
	public Integer getAccountL2() {
		return accountL2;
	}
	public void setAccountL2(Integer accountL2) {
		this.accountL2 = accountL2;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Double getDataOrder() {
		return dataOrder;
	}
	public void setDataOrder(Double dataOrder) {
		this.dataOrder = dataOrder;
	}
	
	
}
