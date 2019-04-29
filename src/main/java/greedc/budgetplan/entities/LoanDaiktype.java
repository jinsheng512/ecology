package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 贷款类型
 *
 */

@Table(name = "	uf_daiktype", catalog = "ecology")
public class LoanDaiktype {
	
	private Integer id;
	private String 	daiklx;
	private String 	daiklxbm;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDaiklx() {
		return daiklx;
	}
	public void setDaiklx(String daiklx) {
		this.daiklx = daiklx;
	}
	public String getDaiklxbm() {
		return daiklxbm;
	}
	public void setDaiklxbm(String daiklxbm) {
		this.daiklxbm = daiklxbm;
	}

	
	
	
	
}
