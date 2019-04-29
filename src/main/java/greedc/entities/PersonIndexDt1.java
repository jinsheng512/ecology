package greedc.entities;

/**
 * 个人季度绩效考核表-工作业绩指标明细表，Sequence = UF_PERSONINDEX_DT1_ID
 * @author William
 */
public class PersonIndexDt1 {

	private Integer id;	// ID
	private Integer mainId;	// 主表ID
	private String fenl;	// 分类
	private String yewmb;	// 业务目标
	private String gongzmb;	// 工作目标
	private Integer quanz;	// 权重（%）
	private Integer wancqk;	// 完成情况（百分制）
	private Double zip;	// 自评
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
	public String getFenl() {
		return fenl;
	}
	public void setFenl(String fenl) {
		this.fenl = fenl;
	}
	public String getYewmb() {
		return yewmb;
	}
	public void setYewmb(String yewmb) {
		this.yewmb = yewmb;
	}
	public String getGongzmb() {
		return gongzmb;
	}
	public void setGongzmb(String gongzmb) {
		this.gongzmb = gongzmb;
	}
	public Integer getQuanz() {
		return quanz;
	}
	public void setQuanz(Integer quanz) {
		this.quanz = quanz;
	}
	public Integer getWancqk() {
		return wancqk;
	}
	public void setWancqk(Integer wancqk) {
		this.wancqk = wancqk;
	}
	public Double getZip() {
		return zip;
	}
	public void setZip(Double zip) {
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
