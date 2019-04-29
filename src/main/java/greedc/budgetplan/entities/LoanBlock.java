package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 金融机构
 *
 */

@Table(name = "uf_bankuai", catalog = "ecology")
public class LoanBlock {
	
	private Integer id;
	private String 	bankmc;
	private String 	bankbh;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBankmc() {
		return bankmc;
	}
	public void setBankmc(String bankmc) {
		this.bankmc = bankmc;
	}
	public String getBankbh() {
		return bankbh;
	}
	public void setBankbh(String bankbh) {
		this.bankbh = bankbh;
	}
	
	
}
