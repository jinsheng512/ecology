package greedc.eas.vo;

public class EASLeaveItemVo {

	private String person;	// Ա������
	private String org;	// ������֯����
	private String type;	// ������ͱ���
	private String reason;	// ���ԭ��
	private String beginTime;	// ��ʼʱ��
	private String endTime;	// ����ʱ��
	private Double leaveLength;	// ���ʱ��
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
