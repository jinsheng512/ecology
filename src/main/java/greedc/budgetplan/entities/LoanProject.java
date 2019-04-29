package greedc.budgetplan.entities;


import javax.persistence.Table;

/**
 * 
 * 项目信息
 *
 */

@Table(name = "uf_project", catalog = "ecology")
public class LoanProject {
	
	
	private Integer id;
	private String xiangmmc;
	private String diy;
	private String xaingmjszt;
	private String xiangmxz;
	
	private Double yongdmj;
	private Double jianzmj;
	private String jihztz;
	private String xiangmlx;
	
	private String gouytdsyq;
	private String huanp;
	private String jiansgcghxkz;
	private String jianzgcsgxkz;
	private String jianzydghxkz;
	private String fuj;
	public String getXiangmmc() {
		return xiangmmc;
	}
	public void setXiangmmc(String xiangmmc) {
		this.xiangmmc = xiangmmc;
	}
	public String getDiy() {
		return diy;
	}
	public void setDiy(String diy) {
		this.diy = diy;
	}
	public String getXaingmjszt() {
		return xaingmjszt;
	}
	public void setXaingmjszt(String xaingmjszt) {
		this.xaingmjszt = xaingmjszt;
	}
	public String getXiangmxz() {
		return xiangmxz;
	}
	public void setXiangmxz(String xiangmxz) {
		this.xiangmxz = xiangmxz;
	}
	public Double getYongdmj() {
		return yongdmj;
	}
	public void setYongdmj(Double yongdmj) {
		this.yongdmj = yongdmj;
	}
	public Double getJianzmj() {
		return jianzmj;
	}
	public void setJianzmj(Double jianzmj) {
		this.jianzmj = jianzmj;
	}
	public String getJihztz() {
		return jihztz;
	}
	public void setJihztz(String jihztz) {
		this.jihztz = jihztz;
	}
	public String getXiangmlx() {
		return xiangmlx;
	}
	public void setXiangmlx(String xiangmlx) {
		this.xiangmlx = xiangmlx;
	}
	public String getGouytdsyq() {
		return gouytdsyq;
	}
	public void setGouytdsyq(String gouytdsyq) {
		this.gouytdsyq = gouytdsyq;
	}
	public String getHuanp() {
		return huanp;
	}
	public void setHuanp(String huanp) {
		this.huanp = huanp;
	}
	public String getJiansgcghxkz() {
		return jiansgcghxkz;
	}
	public void setJiansgcghxkz(String jiansgcghxkz) {
		this.jiansgcghxkz = jiansgcghxkz;
	}
	public String getJianzgcsgxkz() {
		return jianzgcsgxkz;
	}
	public void setJianzgcsgxkz(String jianzgcsgxkz) {
		this.jianzgcsgxkz = jianzgcsgxkz;
	}
	public String getJianzydghxkz() {
		return jianzydghxkz;
	}
	public void setJianzydghxkz(String jianzydghxkz) {
		this.jianzydghxkz = jianzydghxkz;
	}
	public String getFuj() {
		return fuj;
	}
	public void setFuj(String fuj) {
		this.fuj = fuj;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
