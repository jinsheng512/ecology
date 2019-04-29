package ecustom.ecology8.webservice.workflow;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("row")
public class WSRow {

	private Integer id;
	private WSField[] fields;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WSField[] getFields() {
		return fields;
	}

	public void setFields(WSField[] fields) {
		this.fields = fields;
	}
}
