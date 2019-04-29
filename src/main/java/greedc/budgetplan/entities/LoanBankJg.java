package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 余额管理金融机构
 *
 */

@Table(name = "uf_bankgs", catalog = "ecology")
public class LoanBankJg {
	
	private Integer id;
	private String 	jinrdm;
	private String 	jinrmc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJinrdm() {
		return jinrdm;
	}
	public void setJinrdm(String jinrdm) {
		this.jinrdm = jinrdm;
	}
	public String getJinrmc() {
		return jinrmc;
	}
	public void setJinrmc(String jinrmc) {
		this.jinrmc = jinrmc;
	}
	
	
}
