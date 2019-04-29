package ecustom.vo;

public class GenerateDescriptionFieldVo implements Comparable<GenerateDescriptionFieldVo> {

	private Integer fieldId;
	private String fieldName;
	private String tableName;
	private String labelName;
	private String paramLabelName;
	private String paramDesc;
	private String remarks;
	private Integer active;
	private Double order;
	
	/**
	 * 获取字段ID。
	 * @return
	 */
	public Integer getFieldId() {
		return fieldId;
	}
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}
	
	/**
	 * 获取字段名。
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
	 * 获取字段所属表名。
	 * @return
	 */
	public String getTabelName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * 获取系统设置的字段中文名。
	 * @return
	 */
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	/**
	 * 获取参数中文名。
	 * @return
	 */
	public String getParamLabelName() {
		return paramLabelName;
	}
	public void setParamLabelName(String paramLabelName) {
		this.paramLabelName = paramLabelName;
	}
	
	/**
	 * 获取传值说明。
	 * @return
	 */
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	
	/**
	 * 获取备注信息。
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 获取启用状态。<br/>
	 * 1-启用，0-不启用。
	 * @return
	 */
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	/**
	 * 获取字段排序。
	 * @return
	 */
	public Double getOrder() {
		return order;
	}
	public void setOrder(Double order) {
		this.order = order;
	}
	
	@Override
	public int compareTo(GenerateDescriptionFieldVo o) {
		
		// 主表在前，明细表在后
		if (this.tableName == null && o.tableName != null && o.tableName.trim().length() > 0) {
			return -1;
		}
		if (o.tableName == null && this.tableName != null && this.tableName.trim().length() > 0) {
			return 1;
		}
		
		// 判断排序字段
		if (this.order != null && o.order != null) {
			return this.order < o.order ? -1 : 1;
		}
		if (this.order == null && o.order != null) {
			return 1;
		}
		if (this.order != null && o.order == null) {
			return -1;
		}
		
		// 判断字段ID
		return this.fieldId < o.fieldId ? -1 : 1;
	}
}
