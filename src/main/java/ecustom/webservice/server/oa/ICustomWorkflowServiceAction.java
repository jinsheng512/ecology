package ecustom.webservice.server.oa;


public interface ICustomWorkflowServiceAction {

	void doBefore(CustomWorkflowRequestInfo request) throws Exception;
	
	void doAfter(String requestId) throws Exception;
}
