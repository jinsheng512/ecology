package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BORROWING_DT1", catalog = "ecology")
public class LoanFinancingDt1 {
	
	private Integer mainid;     //状态
	private String yujhkrq;          //预计还款日期
	private String shijhkrq;         //实际还款日期
	private Double huankje;        //还款金额
	private String duiyywdj;      //对应业务单据
	private String huankfs;        //还款方式
	private Integer zhuangt;     //状态
	private String beiz;        //备注
	public String getYujhkrq() {
		return yujhkrq;
	}
	public void setYujhkrq(String yujhkrq) {
		this.yujhkrq = yujhkrq;
	}
	public String getShijhkrq() {
		return shijhkrq;
	}
	public void setShijhkrq(String shijhkrq) {
		this.shijhkrq = shijhkrq;
	}
	public Double getHuankje() {
		return huankje;
	}
	public void setHuankje(Double huankje) {
		this.huankje = huankje;
	}
	public String getDuiyywdj() {
		return duiyywdj;
	}
	public void setDuiyywdj(String duiyywdj) {
		this.duiyywdj = duiyywdj;
	}
	public String getHuankfs() {
		return huankfs;
	}
	public void setHuankfs(String huankfs) {
		this.huankfs = huankfs;
	}
	public Integer getZhuangt() {
		return zhuangt;
	}
	public void setZhuangt(Integer zhuangt) {
		this.zhuangt = zhuangt;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}
	public Integer getMainid() {
		return mainid;
	}
	public void setMainid(Integer mainid) {
		this.mainid = mainid;
	}
	
	
}
