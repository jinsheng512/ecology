package greedc.eas.vo;

/**
 * 费用预算清单条目分录信息。<br/>
 * 目前可用于：项目付款计划会签表。
 * @author William
 */
public class EBProjectEntryVo {
	
	private Integer seqNo;	// 分录信息序列号
	private Integer mainSeqNo;	// 主表序列号
	private String bianmxh;	// 编码序号
	private Integer goux;	// 勾选
	private String gongcxm;	// 工程项目
	private String hetbm;	// 合同编码
	private String hetmc;	// 合同名称
	private Double hetje;	// 合同金额
	private Double tiaozhje;	// 调整后金额
	private String xingxjdms;	// 形象进度描述
	private Double yuecljje;	// 月初累计金额
	private String yuecljbl;	// 月初累计比例
	private String benywcjd;	// 本月完成进度
	private String benyjdfk;	// 本月进度付款
	private Double je;	// 金额
	private String yifkbl;	// 已支付比例
	private Double benyjhfke;	// 本月计划付款额
	private String leijbl;	// 累计比例
	private String kuanxlb;	// 款项类别
	private String beiz;	// 备注
	private String shenpryj;	// 审批人意见
	private String shoukdwqc;	// 收款单位全名（施工单位）
	private String shijskdw;	// 实际收款单位
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public Integer getMainSeqNo() {
		return mainSeqNo;
	}
	public void setMainSeqNo(Integer mainSeqNo) {
		this.mainSeqNo = mainSeqNo;
	}
	public String getBianmxh() {
		return bianmxh;
	}
	public void setBianmxh(String bianmxh) {
		this.bianmxh = bianmxh;
	}
	public String getGongcxm() {
		return gongcxm;
	}
	public void setGongcxm(String gongcxm) {
		this.gongcxm = gongcxm;
	}
	public String getHetbm() {
		return hetbm;
	}
	public void setHetbm(String hetbm) {
		this.hetbm = hetbm;
	}
	public String getHetmc() {
		return hetmc;
	}
	public void setHetmc(String hetmc) {
		this.hetmc = hetmc;
	}
	public Double getHetje() {
		return hetje;
	}
	public void setHetje(Double hetje) {
		this.hetje = hetje;
	}
	public Double getTiaozhje() {
		return tiaozhje;
	}
	public void setTiaozhje(Double tiaozhje) {
		this.tiaozhje = tiaozhje;
	}
	public String getXingxjdms() {
		return xingxjdms;
	}
	public void setXingxjdms(String xingxjdms) {
		this.xingxjdms = xingxjdms;
	}
	public Double getYuecljje() {
		return yuecljje;
	}
	public void setYuecljje(Double yuecljje) {
		this.yuecljje = yuecljje;
	}
	public String getYuecljbl() {
		return yuecljbl;
	}
	public void setYuecljbl(String yuecljbl) {
		this.yuecljbl = yuecljbl;
	}
	public String getBenywcjd() {
		return benywcjd;
	}
	public void setBenywcjd(String benywcjd) {
		this.benywcjd = benywcjd;
	}
	public String getBenyjdfk() {
		return benyjdfk;
	}
	public void setBenyjdfk(String benyjdfk) {
		this.benyjdfk = benyjdfk;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public String getYifkbl() {
		return yifkbl;
	}
	public void setYifkbl(String yifkbl) {
		this.yifkbl = yifkbl;
	}
	public Double getBenyjhfke() {
		return benyjhfke;
	}
	public void setBenyjhfke(Double benyjhfke) {
		this.benyjhfke = benyjhfke;
	}
	public String getLeijbl() {
		return leijbl;
	}
	public void setLeijbl(String leijbl) {
		this.leijbl = leijbl;
	}
	public String getKuanxlb() {
		return kuanxlb;
	}
	public void setKuanxlb(String kuanxlb) {
		this.kuanxlb = kuanxlb;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}
	public String getShenpryj() {
		return shenpryj;
	}
	public void setShenpryj(String shenpryj) {
		this.shenpryj = shenpryj;
	}
	public String getShoukdwqc() {
		return shoukdwqc;
	}
	public void setShoukdwqc(String shoukdwqc) {
		this.shoukdwqc = shoukdwqc;
	}
	public String getShijskdw() {
		return shijskdw;
	}
	public void setShijskdw(String shijskdw) {
		this.shijskdw = shijskdw;
	}
	public Integer getGoux() {
		return goux;
	}
	public void setGoux(Integer goux) {
		this.goux = goux;
	}
}
