package greedc.budgetplan.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "uf_BPFinancing", catalog = "ecology")
public class UFBPFinancing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	//	内置字段
	private String year;	// 年份	
	private String month;	// 月份
	private String yearMonth;	// 期间
	private String inRemarks;	// 筹资收入备注
	private String outRemarks;	// 筹资支出备注
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getInRemarks() {
		return inRemarks;
	}
	public void setInRemarks(String inRemarks) {
		this.inRemarks = inRemarks;
	}
	public String getOutRemarks() {
		return outRemarks;
	}
	public void setOutRemarks(String outRemarks) {
		this.outRemarks = outRemarks;
	}
	

}
