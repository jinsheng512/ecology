package ecustom.ecology8.webservice.workflow;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WorkflowServiceXml {

	@WebMethod
	public abstract String createRequest(String xml);
}
