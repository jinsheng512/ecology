package ecustom.services;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;

import ecustom.Log;

/**
 * 域认证服务类。
 * @author William
 */
public class LdapAuthService {
	
	Log log = new Log(LdapAuthService.class);
	
	private String host;
	private String security;
	private String domain;
	private Integer port;
	
	public LdapAuthService(String host, Integer port, String domain, String security) {
		this.host = host;
		this.security = security;
		this.domain = domain;
		this.port = port;
	}

	/**
	 * 域用户验证。
	 * @param userName	域用户名
	 * @param password	域密码
	 * @return	true-验证成功；false-验证失败
	 */
	public boolean checkUser(String userName, String password) {
		InitialDirContext ctx = null;
		boolean status = false;
		try {
	        log.debug("开始进行AD域认证[userName=" + userName + "]");
            ctx = new InitialDirContext(getEnv(userName, password));// 初始化上下文
            status = true;
            log.debug("AD域认证成功[userName=" + userName + "]");
        } catch (AuthenticationException e) {
			String message = e.getMessage();
			int dataIndex = message.indexOf(" data ");
			if (dataIndex > 0) {
				message = message.substring(dataIndex + " data ".length());
				message = message.substring(0, message.indexOf(","));
			}
            log.error("AD域认证失败，用户名或密码不正确[userName=" + userName + ", errMsg=" + message + "]");
            log.debug("[userName=" + userName + ", password=" + password + "]");
        } catch (CommunicationException e) {
        	log.error("连AD域服务器失败[host=" + this.host + ", port=" + this.port + ", domain=" + this.domain + "]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        	close(ctx);
        }
		return status;
	}
	
	/**
	 * 获取域用户连接参数。
	 * @param userName	用户名
	 * @param password	密码
	 * @return
	 */
	private Hashtable<String, String> getEnv(String userName, String password) {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.PROVIDER_URL, "ldap://" + this.host + ":" + this.port);
		env.put(Context.SECURITY_PRINCIPAL, this.domain + "\\" + userName);
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.SECURITY_AUTHENTICATION, this.security);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		return env;
	}
	
	/**
	 * 关闭域连接。
	 * @param ctx
	 */
	private void close(InitialDirContext ctx) {
		if(null != ctx){
            try {
                ctx.close();
                ctx = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}