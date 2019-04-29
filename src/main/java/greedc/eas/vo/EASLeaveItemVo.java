package greedc.eas.vo;

public class EASLeaveItemVo {

	private String person;	// 员工编码
	private String org;	// 行政组织编码
	private String type;	// 请假类型编码
	private String reason;	// 请假原因
	private String beginTime;	// 开始时间
	private String endTime;	// 结束时间
	private Double leaveLength;	// 请假时长
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getLeaveLength() {
		return leaveLength;
	}
	public void setLeaveLength(Double leaveLength) {
		this.leaveLength = leaveLength;
	}
}
