package ecustom.entities;

public class WorkflowBillField {

	private Integer id;
	private String fieldId;
	private String fieldName;
	private String fieldDbType;
	private String detailTable;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDbType() {
		return fieldDbType;
	}
	public void setFieldDbType(String fieldDbType) {
		this.fieldDbType = fieldDbType;
	}
	public String getDetailTable() {
		return detailTable;
	}
	public void setDetailTable(String detailTable) {
		this.detailTable = detailTable;
	}
}
