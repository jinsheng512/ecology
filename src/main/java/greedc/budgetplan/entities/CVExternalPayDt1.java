package greedc.budgetplan.entities;

import javax.persistence.Table;

@Table(name = "CV_UF_BPEXTERNALPAY_DT1", catalog = "ecology")
public class CVExternalPayDt1 {
	private Integer mainid;
	private Integer year;		//年份
	private Integer company;		//公司
	private Integer accountId;		//科目
	private Double season1;		//第一季度
	private Double season2;		//第二季度
	private Double season3;		//第三季度
	private Double season4;		//第四季度

	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMainid() {
		return mainid;
	}
	public void setMainid(Integer mainid) {
		this.mainid = mainid;
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
