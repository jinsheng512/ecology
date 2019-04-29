package ecustom.entities;

public class WebServiceLog {

	private Integer seqNo;
	private String pathInfo;
	private Integer requestBody;
	private Integer responseBody;
	private String requestTime;
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getPathInfo() {
		return pathInfo;
	}
	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}
	public Integer getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(Integer requestBody) {
		this.requestBody = requestBody;
	}
	public Integer getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Integer responseBody) {
		this.responseBody = responseBody;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
}
