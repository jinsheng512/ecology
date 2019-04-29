package ecustom.entities;

public class HrmAnnualManagement {

	private Integer id;
	private Integer	resourceId;
	private String annualYear;
	private Double annualDays;
	private Integer	status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public String getAnnualYear() {
		return annualYear;
	}
	public void setAnnualYear(String annualYear) {
		this.annualYear = annualYear;
	}
	public Double getAnnualDays() {
		return annualDays;
	}
	public void setAnnualDays(Double annualDays) {
		this.annualDays = annualDays;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
