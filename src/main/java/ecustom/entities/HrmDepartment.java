package ecustom.entities;

public class HrmDepartment {

	private Integer id;
	private String departmentName;
	private String departmentMark;
	private String departmentCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentMark() {
		return departmentMark;
	}
	public void setDepartmentMark(String departmentMark) {
		this.departmentMark = departmentMark;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
}
