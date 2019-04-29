package ecustom.entities;

public class SysPoppupRemindInfoNew {
	private Integer userId;
	private Integer ifPup;
	private String userType;
	private Integer type;
	private Integer requestId;
	private String remindDate;
	private Integer counts;
	private String mobilePup;
	private Integer checkTime;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIfPup() {
		return ifPup;
	}
	public void setIfPup(Integer ifPup) {
		this.ifPup = ifPup;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getRemindDate() {
		return remindDate;
	}
	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public String getMobilePup() {
		return mobilePup;
	}
	public void setMobilePup(String mobilePup) {
		this.mobilePup = mobilePup;
	}
	public Integer getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Integer checkTime) {
		this.checkTime = checkTime;
	}
}
