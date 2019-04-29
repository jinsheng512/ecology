package greedc.entities;

/**
 * 个人季度绩效考核表（主表）。<br/>
 * SEQUENCE：UF_PERSONINDEX_ID；<br/>
 * TRIGGER：UF_PERSONINDEX_ID_TRIGGER。
 * @author William
 */
public class PersonIndex {
	private Integer id;
	private Integer beikhr;	// 被考核人
	private String beikhrName;
	private Integer bumfzr;	// 部门负责人
	private Integer deptId;	// 部门ID
	private String bumfzrName;
	private Integer youlpd;	// 优良评定
	private String youlpdName;
	private Integer year;
	private Integer season;	// 考核季度，0-第一季度，1-第二季度，2-第三季度，3-第四季度
	private String fazyq;	// 个人自我评定及培训发展要求
	private Double chengj;	// 员工考核成绩
	private Double chengjzp;	// 员工考核成绩-自评
	private String gongzyj;	// 工作业绩评述
	private String xingwps;	// 行为评述
	private String zongtps;	// 总体评述
	private Integer status;	// 状态，0-暂存，1-被考核人已提交，2-生效
	private Double ywzbzhj;	// 业务指标自评分合计
	private Double xwnlzhj;	// 业务指标审定分合计
	private Double ywzbshj;	// 行为能力自评分合计
	private Double xwnlshj;	// 行为能力审定分合计
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 被考核人ID
	 * @return
	 */
	public Integer getBeikhr() {
		return beikhr;
	}
	/**
	 * 被考核人ID
	 * @return
	 */
	public void setBeikhr(Integer beikhr) {
		this.beikhr = beikhr;
	}
	
	/**
	 * 被部门负责人ID
	 * @return
	 */
	public Integer getBumfzr() {
		return bumfzr;
	}
	/**
	 * 被部门负责人ID
	 * @return
	 */
	public void setBumfzr(Integer bumfzr) {
		this.bumfzr = bumfzr;
	}
	public Integer getYoulpd() {
		return youlpd;
	}
	public void setYoulpd(Integer youlpd) {
		this.youlpd = youlpd;
	}
	public String getYoulpdName() {
		return youlpdName;
	}
	public void setYoulpdName(String youlpdName) {
		this.youlpdName = youlpdName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
	/**
	 * 个人自我评定
	 * @return
	 */
	public String getFazyq() {
		return fazyq;
	}
	/**
	 * 个人自我评定
	 * @return
	 */
	public void setFazyq(String fazyq) {
		this.fazyq = fazyq;
	}
	
	public Double getChengjzp() {
		return chengjzp;
	}
	public void setChengjzp(Double chengjzp) {
		this.chengjzp = chengjzp;
	}
	/**
	 * 工作业绩评述
	 * @return
	 */
	public String getGongzyj() {
		return gongzyj;
	}
	/**
	 * 工作业绩评述
	 * @return
	 */
	public void setGongzyj(String gongzyj) {
		this.gongzyj = gongzyj;
	}
	
	/**
	 * 行为评述
	 * @return
	 */
	public String getXingwps() {
		return xingwps;
	}
	/**
	 * 行为评述
	 * @return
	 */
	public void setXingwps(String xingwps) {
		this.xingwps = xingwps;
	}
	
	/**
	 * 总体评述
	 * @return
	 */
	public String getZongtps() {
		return zongtps;
	}
	/**
	 * 总体评述
	 * @return
	 */
	public void setZongtps(String zongtps) {
		this.zongtps = zongtps;
	}
	
	/**
	 * 状态
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 状态
	 * @return
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 业务指标自评分合计
	 * @return
	 */
	public Double getYwzbzhj() {
		return ywzbzhj;
	}
	/**
	 * 业务指标自评分合计
	 * @return
	 */
	public void setYwzbzhj(Double ywzbzhj) {
		this.ywzbzhj = ywzbzhj;
	}
	
	/**
	 * 业务指标审定分合计
	 * @return
	 */
	public Double getXwnlzhj() {
		return xwnlzhj;
	}
	/**
	 * 业务指标审定分合计
	 * @return
	 */
	public void setXwnlzhj(Double xwnlzhj) {
		this.xwnlzhj = xwnlzhj;
	}
	
	/**
	 * 行为能力自评分合计
	 * @return
	 */
	public Double getYwzbshj() {
		return ywzbshj;
	}
	/**
	 * 行为能力自评分合计
	 * @return
	 */
	public void setYwzbshj(Double ywzbshj) {
		this.ywzbshj = ywzbshj;
	}
	
	/**
	 * 行为能力审定分合计
	 * @return
	 */
	public Double getXwnlshj() {
		return xwnlshj;
	}
	
	/**
	 * 行为能力审定分合计
	 * @return
	 */
	public void setXwnlshj(Double xwnlshj) {
		this.xwnlshj = xwnlshj;
	}
	
	/**
	 * 员工考核成绩
	 * @return
	 */
	public Double getChengj() {
		return chengj;
	}
	
	/**
	 * 员工考核成绩
	 * @return
	 */
	public void setChengj(Double chengj) {
		this.chengj = chengj;
	}
	public String getBeikhrName() {
		return beikhrName;
	}
	public void setBeikhrName(String beikhrName) {
		this.beikhrName = beikhrName;
	}
	public String getBumfzrName() {
		return bumfzrName;
	}
	public void setBumfzrName(String bumfzrName) {
		this.bumfzrName = bumfzrName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
