package greedc.budgetplan.entities;

import javax.persistence.Table;

@Table(catalog = "ecology")
public class VExternalPay {
	private Integer id;
	private String accountName;
	private Integer company;
	private Integer accountId;
	private Integer year;
	private Double season1;
	private Double season2;
	private Double season3;
	private Double season4;

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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getSeason1() {
		return season1;
	}
	public void setSeason1(Double season1) {
		this.season1 = season1;
	}
	public Double getSeason2() {
		return season2;
	}
	public void setSeason2(Double season2) {
		this.season2 = season2;
	}
	public Double getSeason3() {
		return season3;
	}
	public void setSeason3(Double season3) {
		this.season3 = season3;
	}
	public Double getSeason4() {
		return season4;
	}
	public void setSeason4(Double season4) {
		this.season4 = season4;
	}
}
