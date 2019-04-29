package greedc.vo;

public class RequestLogVo {

	private Integer requestId;
	private Integer operator;
	private String name;
	private String deptName;
	private String nodeName;
	private String opDate;
	private String opTime;
	private String logContent;
	private String reNames;	// 接收人
	private String opTypeId;	// 操作类型
	private String opTypeName;	// 操作类型名称
	private String billCode;	// 表单编号
	
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getReNames() {
		return reNames;
	}
	public void setReNames(String reNames) {
		this.reNames = reNames;
	}
	public String getOpTypeName() {
		return opTypeName;
	}
	public void setOpTypeName(String opTypeName) {
		this.opTypeName = opTypeName;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public String getOpTypeId() {
		return opTypeId;
	}
	public void setOpTypeId(String opTypeId) {
		this.opTypeId = opTypeId;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
}
