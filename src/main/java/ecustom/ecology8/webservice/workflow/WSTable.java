package ecustom.ecology8.webservice.workflow;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("row")
public class WSTable {

	private String tableName;
	private WSRow[] rows;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public WSRow[] getRows() {
		return rows;
	}

	public void setRows(WSRow[] rows) {
		this.rows = rows;
	}

}
