package greedc.budgetplan.entities;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UF_BORROWING", catalog = "ecology")
public class LoanFinancing {

	@Id
	private Integer id;                     //主表id
	private Integer requestId;	// 关联审批流程的RequestID
	private String jiekdh;                   //借款单号
	private String 	lurrq;                  //	录入日期
	private String 	shouxhth;               //	授信合同号
	private String gongsmc;           // 	公司名称
	private String jinrjg;                //金融机构
	private String daiklx;             //贷款类型
	private String kuaijkm;             //会计科目
	private String 	fuxfs;              //付息方式
	private String hetll;              //合同利率
	private String jiekll;          //借款利率
	private Double guwf;                 //顾问费
	private Double jiekzcb;          //借款总成本
	private Double jiekje;           //借款金额
	private Double 	yikje;        //已还金额
	private Double yue;         //余额
	private String qisrq;       //起始日期
	private String zhongzrq;       //终止日期
	private String 	danbfs;        //担保方式
	private String danbht;	//担保合同
	private String diy;	//抵押
	private String xiangm;	//项目
	private String beiz;	//备注
	private Double shouxed;	//授信额度
	private Integer status;	//状态
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public String getJiekdh() {
		return jiekdh;
	}
	public void setJiekdh(String jiekdh) {
		this.jiekdh = jiekdh;
	}
	public String getLurrq() {
		return lurrq;
	}
	public void setLurrq(String lurrq) {
		this.lurrq = lurrq;
	}
	public String getShouxhth() {
		return shouxhth;
	}
	public void setShouxhth(String shouxhth) {
		this.shouxhth = shouxhth;
	}
	public String getGongsmc() {
		return gongsmc;
	}
	public void setGongsmc(String gongsmc) {
		this.gongsmc = gongsmc;
	}
	public String getJinrjg() {
		return jinrjg;
	}
	public void setJinrjg(String jinrjg) {
		this.jinrjg = jinrjg;
	}
	public String getDaiklx() {
		return daiklx;
	}
	public void setDaiklx(String daiklx) {
		this.daiklx = daiklx;
	}
	public String getKuaijkm() {
		return kuaijkm;
	}
	public void setKuaijkm(String kuaijkm) {
		this.kuaijkm = kuaijkm;
	}
	public String getFuxfs() {
		return fuxfs;
	}
	public void setFuxfs(String fuxfs) {
		this.fuxfs = fuxfs;
	}
	public String getHetll() {
		return hetll;
	}
	public void setHetll(String hetll) {
		this.hetll = hetll;
	}
	public String getJiekll() {
		return jiekll;
	}
	public void setJiekll(String jiekll) {
		this.jiekll = jiekll;
	}
	public Double getGuwf() {
		return guwf;
	}
	public void setGuwf(Double guwf) {
		this.guwf = guwf;
	}
	public Double getJiekzcb() {
		return jiekzcb;
	}
	public void setJiekzcb(Double jiekzcb) {
		this.jiekzcb = jiekzcb;
	}
	public Double getJiekje() {
		return jiekje;
	}
	public void setJiekje(Double jiekje) {
		this.jiekje = jiekje;
	}
	public Double getYikje() {
		return yikje;
	}
	public void setYikje(Double yikje) {
		this.yikje = yikje;
	}
	public Double getYue() {
		return yue;
	}
	public void setYue(Double yue) {
		this.yue = yue;
	}
	public String getQisrq() {
		return qisrq;
	}
	public void setQisrq(String qisrq) {
		this.qisrq = qisrq;
	}
	public String getZhongzrq() {
		return zhongzrq;
	}
	public void setZhongzrq(String zhongzrq) {
		this.zhongzrq = zhongzrq;
	}
	public String getDanbfs() {
		return danbfs;
	}
	public void setDanbfs(String danbfs) {
		this.danbfs = danbfs;
	}
	public String getDanbht() {
		return danbht;
	}
	public void setDanbht(String danbht) {
		this.danbht = danbht;
	}
	public String getDiy() {
		return diy;
	}
	public void setDiy(String diy) {
		this.diy = diy;
	}
	public String getXiangm() {
		return xiangm;
	}
	public void setXiangm(String xiangm) {
		this.xiangm = xiangm;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}
	public Double getShouxed() {
		return shouxed;
	}
	public void setShouxed(Double shouxed) {
		this.shouxed = shouxed;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

	
}
