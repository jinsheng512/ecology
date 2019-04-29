package ecustom.ecology8.webservice.workflow;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("request")
public class WSRequestInfo {

	private String createTime;
	private Integer creatorId;
	private String requestName;
	private Integer workflowId;
	private Integer requestId;
	private Integer requestLevel;
	private Integer messageType;
	private Integer isNextFlow;
	private WSField[] mainFields;
	private WSTable[] detailTables;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}
	public WSField[] getMainFields() {
		return mainFields;
	}
	public void setMainFields(WSField[] mainFields) {
		this.mainFields = mainFields;
	}
	public WSTable[] getDetailTables() {
		return detailTables;
	}
	public void setDetailTables(WSTable[] detailTables) {
		this.detailTables = detailTables;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getRequestLevel() {
		return requestLevel;
	}
	public void setRequestLevel(Integer requestLevel) {
		this.requestLevel = requestLevel;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public Integer getIsNextFlow() {
		return isNextFlow;
	}
	public void setIsNextFlow(Integer isNextFlow) {
		this.isNextFlow = isNextFlow;
	}
}
