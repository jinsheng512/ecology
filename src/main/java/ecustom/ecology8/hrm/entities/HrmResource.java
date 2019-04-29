package ecustom.ecology8.hrm.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "HRMRESOURCE", catalog = "ecology")
public class HrmResource {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String lastName;
	private Integer sex;
	private Integer systemLanguage;
	private String startDate;
	private Integer jobTitle;
	private Integer jobLevel;
	private Integer secLevel;
	private Integer departmentId;
	@Column(name = "SUBCOMPANYID1")
	private Integer subCompanyId;
	private Integer createrId;
	private String createDate;
	private Integer lastModId;
	private String lastModDate;
	private String certificateNum;
	private Integer status;
	private String outKey;
	private String endDate;
	private String workCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getSystemLanguage() {
		return systemLanguage;
	}
	public void setSystemLanguage(Integer systemLanguage) {
		this.systemLanguage = systemLanguage;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(Integer jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Integer getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}
	public Integer getSecLevel() {
		return secLevel;
	}
	public void setSecLevel(Integer secLevel) {
		this.secLevel = secLevel;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public Integer getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getLastModId() {
		return lastModId;
	}
	public void setLastModId(Integer lastModId) {
		this.lastModId = lastModId;
	}
	public String getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(String lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOutKey() {
		return outKey;
	}
	public void setOutKey(String outKey) {
		this.outKey = outKey;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
}