package greedc.eas.vo;

public class EASResignItemVo {

	private String name;	// 姓名
	private String person;	// 员工编码
	private String oldemptype;	// 员工状态编码
	private String emptype;	// 目标员工状态编码
	private String oldAdminOrg;	// 所属行政组织编码
	private String enAdminOrg;	// 目标行政组织编码
	private String position;	// 职位编码
	private String reasonType;	// 离职原因编号
	private String reason;	// 离职原因编号
	private String suggest;	// 建议
	private String bizDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getOldemptype() {
		return oldemptype;
	}
	public void setOldemptype(String oldemptype) {
		this.oldemptype = oldemptype;
	}
	public String getEmptype() {
		return emptype;
	}
	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}
	public String getOldAdminOrg() {
		return oldAdminOrg;
	}
	public void setOldAdminOrg(String oldAdminOrg) {
		this.oldAdminOrg = oldAdminOrg;
	}
	public String getEnAdminOrg() {
		return enAdminOrg;
	}
	public void setEnAdminOrg(String enAdminOrg) {
		this.enAdminOrg = enAdminOrg;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getBizDate() {
		return bizDate;
	}
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
}
