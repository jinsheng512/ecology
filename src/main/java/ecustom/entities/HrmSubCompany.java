package ecustom.entities;

public class HrmSubCompany {

	private Integer id;
	private String subCompanyName;
	private String subCompanyDesc;
	private String subCompanyCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubCompanyName() {
		return subCompanyName;
	}
	public void setSubCompanyName(String subCompanyName) {
		this.subCompanyName = subCompanyName;
	}
	public String getSubCompanyCode() {
		return subCompanyCode;
	}
	public void setSubCompanyCode(String subCompanyCode) {
		this.subCompanyCode = subCompanyCode;
	}
	public String getSubCompanyDesc() {
		return subCompanyDesc;
	}
	public void setSubCompanyDesc(String subCompanyDesc) {
		this.subCompanyDesc = subCompanyDesc;
	}
}
