package ecustom.webservice.server.oa;

import weaver.workflow.webservices.WorkflowRequestInfo;

public class CustomWorkflowRequestInfo extends WorkflowRequestInfo {

	private static final long serialVersionUID = 5352898911328990252L;
	
	private String applicantCode;

	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	
}
