package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 会计科目
 *
 */

@Table(name = "	uf_kjsbject", catalog = "ecology")
public class Loankjsubject {
	
	private Integer id;
	private String 	kuaijkm;
	private String 	kuaijkmbm;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKuaijkm() {
		return kuaijkm;
	}
	public void setKuaijkm(String kuaijkm) {
		this.kuaijkm = kuaijkm;
	}
	public String getKuaijkmbm() {
		return kuaijkmbm;
	}
	public void setKuaijkmbm(String kuaijkmbm) {
		this.kuaijkmbm = kuaijkmbm;
	}
	
	
	
	
}
