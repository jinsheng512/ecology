package ecustom.entities;

public class WorkflowBase {

	private Integer id;
	private Integer formId;
	private String workflowName;
	private Integer workflowType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public Integer getWorkflowType() {
		return workflowType;
	}
	public void setWorkflowType(Integer workflowType) {
		this.workflowType = workflowType;
	}
}
