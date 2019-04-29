package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BPCOMPANY", catalog = "ecology")
public class UFBPCompany {
	
	@Id
   	private Integer company;
	private Integer auditUser;
	public Integer getCompany() {
		return company;
	}
	public void setCompany(Integer company) {
		this.company = company;
	}
	public Integer getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	
	
}
