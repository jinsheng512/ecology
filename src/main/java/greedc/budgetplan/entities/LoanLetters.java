package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 授信管理
 *
 */

@Table(name = "	uf_Letters", catalog = "ecology")
public class LoanLetters {
	
	private Integer id;
	private String 	shouxhth;
	private String 	shouxyh;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShouxhth() {
		return shouxhth;
	}
	public void setShouxhth(String shouxhth) {
		this.shouxhth = shouxhth;
	}
	public String getShouxyh() {
		return shouxyh;
	}
	public void setShouxyh(String shouxyh) {
		this.shouxyh = shouxyh;
	}

	
	
}
