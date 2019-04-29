package weaver.fna.eas;

public class UserInfo {
	
	private String userName = "";
	private String password = "";
	private String language = "";
	private String authPattern = "";
	/**
	 * 用户名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 用户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 语言：L2：简体中文；L3：繁体中文；
	 * @return
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * 语言：L2：简体中文；L3：繁体中文；
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * 验证方式：默认BaseDB
	 * @return
	 */
	public String getAuthPattern() {
		if("".equals(authPattern)){
			authPattern = "BaseDB";
		}
		return authPattern;
	}
	/**
	 * 验证方式：默认BaseDB
	 * @param authPattern
	 */
	public void setAuthPattern(String authPattern) {
		this.authPattern = authPattern;
	}
}







































