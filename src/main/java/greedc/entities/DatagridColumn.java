package greedc.entities;

public class DatagridColumn implements Comparable<DatagridColumn> {

	private Integer seqNo;
	private Integer billId;
	private String title;
	private String field;
	private Integer width;
	private String align;
	private Double showOrder;	
	private String formatter;
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Double getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Double showOrder) {
		this.showOrder = showOrder;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	@Override
	public int compareTo(DatagridColumn o) {
		if (this.showOrder == null && o.getShowOrder() == null) {
			return 0;
		}
		if (this.showOrder != null && o.getShowOrder() == null) {
			return -1;
		}
		if (this.showOrder == null && o.getShowOrder() != null) {
			return 1;
		}
		if (this.showOrder.doubleValue() == o.getShowOrder().doubleValue()) {
			return 0;
		}
		return this.showOrder < o.getShowOrder() ? -1 : 1;
	}
}
