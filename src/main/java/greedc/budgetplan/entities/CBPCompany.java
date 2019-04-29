package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "C_BP_COMPANY", catalog = "ecology")
public class CBPCompany {
	
	@Id
   	private Integer company;
   	private Integer companyBlock;
   	private String blockName;
   	private String subCompanyName;
   	
   	private String submitEndDate;
   	
	public String getSubmitEndDate() {
		return submitEndDate;
	}
	public void setSubmitEndDate(String submitEndDate) {
		this.submitEndDate = submitEndDate;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getCompanyBlock() {
		return companyBlock;
	}
	public void setCompanyBlock(Integer companyBlock) {
		this.companyBlock = companyBlock;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getSubCompanyName() {
		return subCompanyName;
	}
	public void setSubCompanyName(String subCompanyName) {
		this.subCompanyName = subCompanyName;
	}
}
