package greedc.budgetplan.entities;


import javax.persistence.Table;

@Table(name = "uf_BPCompanyProject", catalog = "ecology")
public class UFBPCompanyProject {
	
	private Integer id;
	private Integer company;        //公司名称
	private String projectName;     //项目名称
	private Integer isOpen;         //是否启用
	private Double dataOrder;       //排序
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	public Double getDataOrder() {
		return dataOrder;
	}
	public void setDataOrder(Double dataOrder) {
		this.dataOrder = dataOrder;
	}
	
	
}
