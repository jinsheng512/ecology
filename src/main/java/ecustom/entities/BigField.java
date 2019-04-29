package ecustom.entities;

/**
 * 大字段实体类。
 * @author William
 */
public class BigField {

	private Integer seqNo;
	private String fieldValue;
	
	public BigField() {
	}
	public BigField(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public BigField(Integer seqNo, String fieldValue) {
		this.seqNo = seqNo;
		this.fieldValue = fieldValue;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
