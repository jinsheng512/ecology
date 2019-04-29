package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BORROWING_DT2", catalog = "ecology")
public class LoanFinancingDt2 {
	
	@Id
	private Integer qijiann;          //期间年
	private Integer qijiany;         //期间月
	private Double lixje;        //利息金额
	private String beiz;        //备注
	
	
	public Integer getQijiann() {
		return qijiann;
	}
	public void setQijiann(Integer qijiann) {
		this.qijiann = qijiann;
	}
	public Integer getQijiany() {
		return qijiany;
	}
	public void setQijiany(Integer qijiany) {
		this.qijiany = qijiany;
	}
	public Double getLixje() {
		return lixje;
	}
	public void setLixje(Double lixje) {
		this.lixje = lixje;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}

	
	
}
