package ecustom.easyui.vo;

public class DatagridColumnVo implements Comparable<DatagridColumnVo>{

	private String field;
	private String title;
	private Integer width;
	private String formatter;
	private String align;
	private Integer order;
	
	public DatagridColumnVo(String field, String title) {
		this.field = field;
		this.title = title;
	}
	public DatagridColumnVo(String field, String title, Integer width) {
		this.field = field;
		this.title = title;
		this.width = width;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public int compareTo(DatagridColumnVo o) {
		if (this.order == null || o.order == null
				|| this.order == o.order) {
			return 0;
		}
		return this.order > o.order ? 1 : -1;
	}
}
