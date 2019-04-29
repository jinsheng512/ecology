package greedc.budgetplan.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BPEXTERNALPAY_DT1", catalog = "ecology")
public class UFBPExternalPayDt1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	//	内置字段
	private Integer mainId;	//	内置字段
	private String accountId;	// 科目	
	private Double season1;	// 第一季度
	private Double season2;	// 第二季度
	private Double season3;	// 第三季度
	private Double season4;	// 第四季度

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
