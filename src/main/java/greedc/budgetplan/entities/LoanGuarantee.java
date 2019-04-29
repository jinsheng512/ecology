package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 担保管理
 *
 */

@Table(name = "	uf_guarantee", catalog = "ecology")
public class LoanGuarantee {
	
	private Integer id;
	private String 	danbdh;
	private String 	danbht;
	
	
	
	public String getDanbdh() {
		return danbdh;
	}
	public void setDanbdh(String danbdh) {
		this.danbdh = danbdh;
	}
	public String getDanbht() {
		return danbht;
	}
	public void setDanbht(String danbht) {
		this.danbht = danbht;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
}
