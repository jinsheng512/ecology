package greedc.budgetplan.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BPACCOUNT_DT1", catalog = "ecology")
public class UFBPAccountDt1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	//	内置字段
	private Integer mainId;	//	内置字段
	private String accountName;	// 科目	
	private String accountCode;	// 科目	
	private String isOpen;	// 科目	
	private String dataOrder;	// 科目	
	
	
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public String getDataOrder() {
		return dataOrder;
	}
	public void setDataOrder(String dataOrder) {
		this.dataOrder = dataOrder;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	

}
