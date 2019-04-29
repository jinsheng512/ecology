package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 金融机构
 *
 */

@Table(name = "uf_bank", catalog = "ecology")
public class LoanBank {
	
	private Integer id;
	private String 	jinrjg;
	private String 	suozd;
	private String diz;
	private String lianxr;
	private String lianxidh;
	
	
	public String getJinrjg() {
		return jinrjg;
	}
	public void setJinrjg(String jinrjg) {
		this.jinrjg = jinrjg;
	}
	public String getSuozd() {
		return suozd;
	}
	public void setSuozd(String suozd) {
		this.suozd = suozd;
	}
	public String getDiz() {
		return diz;
	}
	public void setDiz(String diz) {
		this.diz = diz;
	}
	public String getLianxr() {
		return lianxr;
	}
	public void setLianxr(String lianxr) {
		this.lianxr = lianxr;
	}
	public String getLianxidh() {
		return lianxidh;
	}
	public void setLianxidh(String lianxidh) {
		this.lianxidh = lianxidh;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
