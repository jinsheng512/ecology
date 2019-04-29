package ecustom.servlets;

import java.util.HashMap;
import java.util.Map;

public class Response {

	private boolean status;
	private String message;
	private Object obj;
	private Map<String, Object> attr;
	
	public Response(boolean status) {
		this.status = status;
	}

	public Response(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public Response(boolean status, String message, Object obj) {
		super();
		this.status = status;
		this.message = message;
		this.obj = obj;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Map<String, Object> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

	public void setAttr(String name, Object value) {
		if (attr == null) {
			attr = new HashMap<String, Object>();
		}
		attr.put(name, value);
	}
}
