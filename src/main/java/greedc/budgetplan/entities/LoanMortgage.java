package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 抵押管理
 *
 */

@Table(name = "	uf_mortgage", catalog = "ecology")
public class LoanMortgage {
	
	private Integer id;
	private String 	diydh;
	private String 	diyhtbh;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDiydh() {
		return diydh;
	}
	public void setDiydh(String diydh) {
		this.diydh = diydh;
	}
	public String getDiyhtbh() {
		return diyhtbh;
	}
	public void setDiyhtbh(String diyhtbh) {
		this.diyhtbh = diyhtbh;
	}
	
	
	
	
	
}
