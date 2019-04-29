package ecustom.web;

/**
 * Created by William on 2017-7-26.
 */
public class RequestFilterResult {

	public static final int TYPE_CHAIN = 1;
	public static final int TYPE_FORWORD = 2;
	public static final int TYPE_REDIRECT = 3;

	private int type = 1;
	private String url;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
