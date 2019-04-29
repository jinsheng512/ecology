package greedc.budgetplan.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//BP07-外部经营性支出主表实体类
@Table(name = "UF_BPEXTERNALPAY", catalog = "ecology")
public class UFBPExternalPay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer requestId;
	private String activeDate;	// 生效日期
	private Integer company;	// 公司名称
	private Integer status;	// 状态
	private Integer submitUser;	// 提交人
	private Integer year;	// 年度
	
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
	public String getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(Integer submitUser) {
		this.submitUser = submitUser;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}
