package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 借款单位
 *
 */

@Table(name = "	uf_jkcompany", catalog = "ecology")
public class LoanJkcompany {
	
	private Integer id;
	private String 	jkdwbh;
	private String 	jkdwmc;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJkdwbh() {
		return jkdwbh;
	}
	public void setJkdwbh(String jkdwbh) {
		this.jkdwbh = jkdwbh;
	}
	public String getJkdwmc() {
		return jkdwmc;
	}
	public void setJkdwmc(String jkdwmc) {
		this.jkdwmc = jkdwmc;
	}
	
	
	
}
