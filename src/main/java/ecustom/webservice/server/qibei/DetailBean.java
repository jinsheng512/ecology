package ecustom.webservice.server.qibei;

import java.io.Serializable;

public class DetailBean  implements Serializable{
	
	private String dingdh;
	private String hetwb;
	
	
	public String getDingdh() {
		return dingdh;
	}
	public void setDingdh(String dingdh) {
		this.dingdh = dingdh;
	}
	public String getHetwb() {
		return hetwb;
	}
	public void setHetwb(String hetwb) {
		this.hetwb = hetwb;
	}

}
