package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 付息方式
 *
 */

@Table(name = "	uf_interesttype", catalog = "ecology")
public class LoanInteresttype {
	
	private Integer id;
	private String 	fuxfs;
	private String 	fuxfsbm;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFuxfs() {
		return fuxfs;
	}
	public void setFuxfs(String fuxfs) {
		this.fuxfs = fuxfs;
	}
	public String getFuxfsbm() {
		return fuxfsbm;
	}
	public void setFuxfsbm(String fuxfsbm) {
		this.fuxfsbm = fuxfsbm;
	}
	
	
	
}
