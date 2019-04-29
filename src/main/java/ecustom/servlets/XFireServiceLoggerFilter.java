package ecustom.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import ecustom.entities.WebServiceLog;
import ecustom.services.BigFieldService;
import ecustom.services.WebServiceLogService;
import ecustom.util.CustomUtil;

public class XFireServiceLoggerFilter implements Filter {
	
	private String logServiceNames = null;

	public void destroy() { }

	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		RequestWrapper wrappedRequest = new RequestWrapper(request);
		ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
		String requestBody = IOUtils.toString(wrappedRequest.getInputStream(), "UTF-8");
		chain.doFilter(wrappedRequest, wrappedResponse);
		String responseBody = IOUtils.toString(wrappedResponse.getByteArrayStream(), "UTF-8");
		
		String pathInfo = request.getPathInfo();
		String queryString = request.getQueryString();
		if (CustomUtil.isNotBlank(pathInfo) && !isWsdl(queryString)) {
			String serviceName = pathInfo.substring(1);
			if (needSaveLog(serviceName)) {
				saveWSLog(pathInfo.substring(1), requestBody, responseBody);
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
		logServiceNames = config.getInitParameter("logServiceNames");
	}
	
	private boolean needSaveLog(String serviceName) {
		if (CustomUtil.isNotBlank(logServiceNames)) {
			return logServiceNames.contains(serviceName);
		}
		return false;
	}
	
	private void saveWSLog(String pathInfo, String requestBody, String responseBody) {
		BigFieldService fieldService = new BigFieldService();
		WebServiceLog entry = new WebServiceLog();
		entry.setPathInfo(pathInfo);
		entry.setRequestBody(fieldService.save(requestBody));
		entry.setResponseBody(fieldService.save(responseBody));
		WebServiceLogService logService = new WebServiceLogService();
		logService.save(entry);
	}
	
	private boolean isWsdl(String queryString) {
		return queryString != null && queryString.endsWith("wsdl");
	}
}