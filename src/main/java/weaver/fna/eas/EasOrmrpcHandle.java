package weaver.fna.eas;

import java.net.URL;

import _227._100._168._192.ormrpc.services.EASLogin.EASLoginProxy;
import _227._100._168._192.ormrpc.services.EASLogin.EASLoginProxyServiceLocator;
import _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxy;
import _227._100._168._192.ormrpc.services.WSGeneratePaymentFacade.WSGeneratePaymentFacadeSrvProxyServiceLocator;
import client.WSContext;
import weaver.general.BaseBean;
import weaver.general.Util;

public class EasOrmrpcHandle extends BaseBean {
	
	boolean isDebug = true;
	
	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	ServiceInfo service = null;

	public EasOrmrpcHandle() {
	}

	public EasOrmrpcHandle(ServiceInfo service) {
		this.service = service;
	}

	public String createVoucher(String params) throws Exception {
		
		BaseBean bb = new BaseBean();
		String UserName = bb.getPropValue("QC228542", "userName").trim();
		String Password = bb.getPropValue("QC228542", "Password").trim();
		String Eas = bb.getPropValue("QC228542", "slnName").trim();
		String Datacenter = bb.getPropValue("QC228542", "dcName").trim();
		String Language = bb.getPropValue("QC228542", "Language").trim();
		int Type = Util.getIntValue(bb.getPropValue("QC228542", "dbType").trim());
		String servername = bb.getPropValue("QC228542", "servername").trim();
		
		if(this.service==null){
			this.service = new ServiceInfo();
			this.service.setEas(Eas);// eas
			this.service.setDatacenter(Datacenter);// 数据中心
			this.service.setType(Type);// 数据库类型
			this.service.setServername(servername);
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(UserName);// 用户名
		userInfo.setPassword(Password);// 密码
		userInfo.setLanguage(Language);// 语言

		if(isDebug){this.writeLog("EasOrmrpcHandle.java", "doEASLogin");}
		WSContext wsContext = this.doEASLogin(userInfo);
		String retStr = "";
		String retCode = "";
		String retInfo = "";
		if(isDebug){this.writeLog("EasOrmrpcHandle.java", "wsContext="+wsContext);}
		if (wsContext != null) {
			if(isDebug){this.writeLog("EasOrmrpcHandle.java", "wsContext.getSessionId()="+wsContext.getSessionId());}
		}
		String msg = "[{\"number\":\"0000\",\"state\":\"1\",\"msg\":";
		if (wsContext == null) {
			retStr = msg+"\"EAS凭证接口登录失败！wsContext:"+wsContext+";\"}]";
		} else if (wsContext.getSessionId()==null || "".equals(wsContext.getSessionId())) {
			retStr = msg+"\"EAS凭证接口登录失败！wsContext.getSessionId():"+wsContext.getSessionId()+";\"}]";
		} else {
			this.writeLog("EasOrmrpcHandle:", "登录成功!");
			retStr = this.generatePayment(params);
		}
		return retStr;
	}

	public WSContext doEASLogin(UserInfo user) throws Exception {
		String url = this.service.getServername() + "EASLogin";
		URL endpoint = new URL(url);
		EASLoginProxy proxy = new EASLoginProxyServiceLocator().getEASLogin(endpoint);
		return proxy.login(user.getUserName(), user.getPassword(), this.service.getEas(), this.service.getDatacenter(),
				user.getLanguage(), this.service.getType());
	}
	
	public String generatePayment(String params) throws Exception {
		String url = this.service.getServername() + "WSGeneratePaymentFacade";
		URL endpoint = new URL(url);
		WSGeneratePaymentFacadeSrvProxy wSGeneratePaymentFacadeSrvProxy = new WSGeneratePaymentFacadeSrvProxyServiceLocator().getWSGeneratePaymentFacade(endpoint);
		return wSGeneratePaymentFacadeSrvProxy.generatePayment(params);
	}
}