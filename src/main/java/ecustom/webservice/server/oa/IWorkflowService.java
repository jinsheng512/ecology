package ecustom.webservice.server.oa;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IWorkflowService {

	@WebMethod(operationName="doCreateWorkflowRequest", action="urn:ecustom.webservice.server.oa.IWorkflowService.doCreateWorkflowRequest")
	public String doCreateWorkflowRequest(CustomWorkflowRequestInfo requestInfo, int int0);
}
