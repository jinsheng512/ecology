package ecustom.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;

import ecustom.Log;

/**
 * Servlet 代理类。
 * @author William
 */
public class AgentServlet extends HttpServlet {

	private static final long serialVersionUID = -7323213237834450419L;
	private static final Log log = new Log(AgentServlet.class);
	private static final String RESPONSE_TYPE_JSON = "json";
	
	private String servletPackage = null;

	public void init(ServletConfig config) {
		this.servletPackage = config.getInitParameter("servletPackage");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		agent(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		agent(request, response);
	}

	private void agent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	// 设置请求编码
		response.setCharacterEncoding("UTF-8");	// 设置输出编码
		response.setContentType("text/html; charset=UTF-8");	// 设置浏览器解析编码

		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		
		if (log.isDebug()) {
			log.debug("requestUrl = " + pathInfo);
			@SuppressWarnings("unchecked")
			Enumeration<String> pNames = request.getParameterNames();
			while (pNames.hasMoreElements()) {
				String name = pNames.nextElement();
				log.debug(name + " = " + request.getParameter(name));
			}
		}
		
		if (pathInfo.contains("-")) {
			String[] pathInfos = pathInfo.split("-");
			String className = null;
			if (this.servletPackage == null || this.servletPackage.isEmpty()) {
				className = pathInfos[0].substring(1) + "Servlet";
			} else {
				className = this.servletPackage + "." + pathInfos[0].substring(1) + "Servlet";
			}
			String methodName = pathInfos[1].substring(0, pathInfos[1].indexOf('.'));
			String responseType = pathInfos[1].substring(pathInfos[1].indexOf('.') + 1);
			try {
				BaseServlet servlet = (BaseServlet)Class.forName(className).newInstance();
				agentInvoke(servlet, "init", request, response);
				Object content = agentInvoke(servlet, methodName);
				processResponse(request, response, responseType, content);
			} catch (Exception e) {
				e.printStackTrace();
				if (RESPONSE_TYPE_JSON.equalsIgnoreCase(responseType)) {
					processResponse(request, response, responseType,
							new Response(false, e.getCause() == null ? e.getMessage() : e.getCause().getMessage()));
				}
			}
		} else {
			throw new ServletException("请求路径解析失败，路径结构应该为[" + contextPath + servletPath + "/xxx-xxx.xxx]，实际路径: [" + contextPath + servletPath + pathInfo + "]");
		}
	}

	private Object agentInvoke(BaseServlet servlet, String methodName, HttpServletRequest request, HttpServletResponse response) {
		try {
			Method method = servlet.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			return method.invoke(servlet, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object agentInvoke(BaseServlet servlet, String methodName) throws Exception {
		Method method = servlet.getClass().getMethod(methodName);
		return method.invoke(servlet);
	}

	private void processResponse(HttpServletRequest request, HttpServletResponse response, String responseType, Object content) throws IOException, ServletException {
		if (responseType.equalsIgnoreCase("html")) {
			responseHtml(request, response, content.toString());
		} else if (responseType.equalsIgnoreCase("json")) {
			responseJson(response, content);
		} else if (responseType.equalsIgnoreCase("text")) {
			response.getWriter().write((String)content);
		}
	}

	private void responseHtml(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		String targetType = "";
		if (url.indexOf(':') > 0) {
			targetType = url.substring(0, url.indexOf(':') + 1);
			url = url.substring(url.indexOf(':') + 1);
		}
		if (targetType.equalsIgnoreCase(BaseServlet.FORWARD)) {
			request.getRequestDispatcher("/jsp/" + url).forward(request, response);
		} else if (targetType.equalsIgnoreCase(BaseServlet.FORWARD_SEND)) {
			request.getRequestDispatcher("/" + url).forward(request, response);
		} else if (targetType.equalsIgnoreCase(BaseServlet.REDIRECT)) {
			response.sendRedirect(request.getContextPath() + "/jsp/" + url);
		} else if (targetType.equalsIgnoreCase(BaseServlet.REDIRECT_SEND)) {
			response.sendRedirect(request.getContextPath() + "/" + url);
		} else {
			request.getRequestDispatcher("/jsp/" + url).forward(request, response);
		}
	}

	private void responseJson(HttpServletResponse response, Object json) throws ServletException, IOException {
		String retText = null;
		if (json instanceof String) {
			retText = (String)json;
		} else {
			retText = new GsonBuilder().create().toJson(json);
		}
		
		if (retText == null || retText.equalsIgnoreCase("null")) {
			retText = "{}";
		}
		log.debug(retText);
		response.getWriter().write(retText);
	}
}