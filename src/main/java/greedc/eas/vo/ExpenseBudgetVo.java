package greedc.eas.vo;

/**
 * ����Ԥ���嵥��Ŀ��<br/>
 * Ŀǰ�����ڣ���Ŀ����ƻ���ǩ��
 * @author ��ˮ��
 */
public class ExpenseBudgetVo {

	private Integer seqNo;	// ���к�
	private Integer requestId;	// OA����ʵ��ID
	private String projectId;	// ��ĿID
	private String projectName;	// ��Ŀ����
	private String payCo;	// ���λ
	private Double accPayable;	// ����Ӧ���ϼ�
	private String proportion;	// ���¸������
	private Double payTotal;	// ���¸���ϼ�
	private Integer conNum;	//��ͬ����
	private String statisticalDept;	// ͳ�Ʋ���
	private String year;
	private String month;
	private String detailJson; // ����ƻ���ϸ����
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
