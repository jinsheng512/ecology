package ecustom.ecology8.webservice.workflow;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("field")
public class WSField {

	private String name;
	private String value;
	private String type;
	
	public WSField() {}
	
	public WSField(String name, String value, String type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	public WSField(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
