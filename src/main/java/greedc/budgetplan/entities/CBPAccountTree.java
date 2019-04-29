package greedc.budgetplan.entities;

import javax.persistence.Table;

@Table(name = "C_BP_ACCOUNTTREE", catalog = "ecology")
public class CBPAccountTree {
   	private Integer id;
   	private String accountName;
   	private Double dataOrder;
   	private Integer accountType;
   	private Integer childId;
   	private String childAccountName;
   	private Double childDataOrder;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Double getDataOrder() {
		return dataOrder;
	}
	public void setDataOrder(Double dataOrder) {
		this.dataOrder = dataOrder;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public Integer getChildId() {
		return childId;
	}
	public void setChildId(Integer childId) {
		this.childId = childId;
	}
	public String getChildAccountName() {
		return childAccountName;
	}
	public void setChildAccountName(String childAccountName) {
		this.childAccountName = childAccountName;
	}
	public Double getChildDataOrder() {
		return childDataOrder;
	}
	public void setChildDataOrder(Double childDataOrder) {
		this.childDataOrder = childDataOrder;
	}
}
