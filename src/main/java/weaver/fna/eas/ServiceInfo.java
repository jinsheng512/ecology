package weaver.fna.eas;

public class ServiceInfo {
	
	private String servername = "";
	private String eas = "";
	private String datacenter = "";
	private int type = 0;
	/**
	 * 服务名：http://114.215.117.218:6888/ormrpc/services/
	 * @return
	 */
	public String getServername() {
		return servername;
	}
	/**
	 * 服务名：http://114.215.117.218:6888/ormrpc/services/
	 * @param servername
	 */
	public void setServername(String servername) {
		this.servername = servername;
	}
	/**
	 * 解决方案：eas
	 * @return
	 */
	public String getEas() {
		return eas;
	}
	/**
	 * 解决方案：eas
	 * @param eas
	 */
	public void setEas(String eas) {
		this.eas = eas;
	}
	/**
	 * 数据中心
	 * @return
	 */
	public String getDatacenter() {
		return datacenter;
	}
	/**
	 * 数据中心
	 * @param datacenter
	 */
	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter;
	}
	/**
	 * 数据库类型：0：sqlserver；1：oracle；2：db2；
	 * @return
	 */
	public int getType() {
		return type;
	}
	/**
	 * 数据库类型：0：sqlserver；1：oracle；2：db2；
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
}







































