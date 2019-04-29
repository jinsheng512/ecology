package ecustom.entities;

public class HrmJobTitle {

	private Integer id;
	private String jobTitleMark, jobTitleName, jobTitleCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJobTitleMark() {
		return jobTitleMark;
	}
	public void setJobTitleMark(String jobTitleMark) {
		this.jobTitleMark = jobTitleMark;
	}
	public String getJobTitleName() {
		return jobTitleName;
	}
	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}
	public String getJobTitleCode() {
		return jobTitleCode;
	}
	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}
}
