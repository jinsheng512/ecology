package ecustom.servlets;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ecustom.util.CustomUtil;
import weaver.hrm.User;

/**
 * Servlet 基类。
 * @author William
 */
public abstract class BaseServlet {

	public final static String FORWARD = "FORWARD:";
	public final static String FORWARD_SEND = "FORWARD_SEND:";
	public final static String REDIRECT = "REDIRECT:";
	public final static String REDIRECT_SEND = "REDIRECT_SEND:";
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private User user;

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.session = request.getSession();
		this.response = response;
		user = (User)request.getSession(true).getAttribute("weaver_user@bean");
	}

	public HttpServletResponse response() {
		return response;
	}

	public HttpSession session() {
		return session;
	}

	public HttpServletRequest request() {
		return request;
	}

	public User getUser() throws Exception {
		checkSession();
		return user;
	}

	public void checkSession() throws Exception {
		if (user == null || user.getUID() <= 0) {
			throw new Exception("会话超时，请重新登录！");
		}
	}

	public String getParameter(String name) {
		return this.request.getParameter(name);
	}

	public Integer getParameterInteger(String name) {
		String value = getParameter(name);
		return CustomUtil.getInteger(value, null);
	}

	public String getParameterCK(String name) {
		return getParameter(name, true);
	}

	public int getParameterIntegerCK(String name) {
		String value = getParameter(name, true);
		Integer intValue = CustomUtil.getInteger(value, null);
		if (intValue == null) {
			throw new RuntimeException("请求参数 " + name + " 不是整数。");
		}
		return intValue.intValue();
	}

	public String getParameter(String name, boolean isCheck) {
		String value = this.request.getParameter(name);
		if (isCheck && CustomUtil.isBlank(value)) {
			throw new RuntimeException("缺少请求参数 " + name + " 或参数值为空。");
		}
		return value;
	}

	public void sendRedirect(String url) {
		try {
			response.sendRedirect("/login/Login.jsp?logintype=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}