package greedc.entities;

/**
 * 个人季度绩效考核表-行为能力指标明细表，Sequence = UF_PERSONINDEX_DT2_ID
 * @author William
 */
public class PersonIndexDt2 {

	private Integer id;	// ID
	private Integer mainId;	// 主表ID
	private String xingwnl;	// 行为能力指标
	private String shuom;	// 行为能力指标说明
	private Integer zip;	// 自评（1-5分）
	private Integer shendpf;	// 审定评分
	private Integer order;	// 排序
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	public String getXingwnl() {
		return xingwnl;
	}
	public void setXingwnl(String xingwnl) {
		this.xingwnl = xingwnl;
	}
	public String getShuom() {
		return shuom;
	}
	public void setShuom(String shuom) {
		this.shuom = shuom;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	public Integer getShendpf() {
		return shendpf;
	}
	public void setShendpf(Integer shendpf) {
		this.shendpf = shendpf;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
