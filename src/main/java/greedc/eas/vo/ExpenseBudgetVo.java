package greedc.eas.vo;

/**
 * 费用预算清单条目。<br/>
 * 目前可用于：项目付款计划会签表。
 * @author 曹水涛
 */
public class ExpenseBudgetVo {

	private Integer seqNo;	// 序列号
	private Integer requestId;	// OA流程实例ID
	private String projectId;	// 项目ID
	private String projectName;	// 项目名称
	private String payCo;	// 付款单位
	private Double accPayable;	// 本月应付合计
	private String proportion;	// 本月付款比例
	private Double payTotal;	// 上月付款合计
	private Integer conNum;	//合同数量
	private String statisticalDept;	// 统计部门
	private String year;
	private String month;
	private String detailJson; // 付款计划明细数据
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPayCo() {
		return payCo;
	}
	public void setPayCo(String payCo) {
		this.payCo = payCo;
	}
	public Double getAccPayable() {
		return accPayable;
	}
	public void setAccPayable(Double accPayable) {
		this.accPayable = accPayable;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public Double getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(Double payTotal) {
		this.payTotal = payTotal;
	}
	public Integer getConNum() {
		return conNum;
	}
	public void setConNum(Integer conNum) {
		this.conNum = conNum;
	}
	public String getDetailJson() {
		return detailJson;
	}
	public void setDetailJson(String detailJson) {
		this.detailJson = detailJson;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getStatisticalDept() {
		return statisticalDept;
	}
	public void setStatisticalDept(String statisticalDept) {
		this.statisticalDept = statisticalDept;
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
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
