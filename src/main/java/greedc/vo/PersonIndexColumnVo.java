package greedc.vo;

public class PersonIndexColumnVo {
	
	private int id;
	private String xingwnl;
	private String shuom;
	private int zip;
	private double shendpf;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 行为能力指标
	 * @return
	 */
	public String getXingwnl() {
		return xingwnl;
	}
	/**
	 * 行为能力指标
	 * @return
	 */
	public void setXingwnl(String xingwnl) {
		this.xingwnl = xingwnl;
	}
	
	/**
	 * 行为能力指标说明
	 * @return
	 */
	public String getShuom() {
		return shuom;
	}
	/**
	 * 行为能力指标说明
	 * @return
	 */
	public void setShuom(String shuom) {
		this.shuom = shuom;
	}
	
	/**
	 * 自评（1-5分）
	 * @return
	 */
	public int getZip() {
		return zip;
	}
	/**
	 * 自评（1-5分）
	 * @return
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	/**
	 * 审定评分
	 * @return
	 */
	public double getShendpf() {
		return shendpf;
	}
	
	/**
	 * 审定评分
	 * @return
	 */
	public void setShendpf(double shendpf) {
		this.shendpf = shendpf;
	}
	
	
}
