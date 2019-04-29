package ecustom.ecology8.webservice.workflow;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("response")
public class WSResponse {

	private Integer requestId;
	private String message;
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
