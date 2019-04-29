package greedc.budgetplan.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BPCOMPANYACCOUNT", catalog = "ecology")
public class UFBPCompanyAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer company;
	private Integer accountL1;	// 一级科目
	private Integer accountL2;	// 二级科目
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
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
}
