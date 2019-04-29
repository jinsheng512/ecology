package ecustom.web;

import ecustom.util.CustomUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求过滤。
 * Created by William on 2017-7-26.
 */
public class RequestFilter implements Filter {

	private String processClass;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.processClass = filterConfig.getInitParameter("processClass");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		try {
			RequestFilterService service = (RequestFilterService)Class.forName(getProcessClass()).newInstance();
			RequestFilterResult result = service.process(httpServletRequest, httpServletResponse);
			if (result.getType() == RequestFilterResult.TYPE_FORWORD) {
				request.getRequestDispatcher(result.getUrl()).forward(httpServletRequest, httpServletResponse);
			} else if (result.getType() == RequestFilterResult.TYPE_REDIRECT) {
				httpServletResponse.sendRedirect(result.getUrl());
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {

	}

	public String getProcessClass() throws ServletException {
		if (CustomUtil.isBlank(processClass)) {
			throw new ServletException("processClass 参数不能为空！");
		}
		return processClass;
	}

	public void setProcessClass(String processClass) {
		this.processClass = processClass;
	}
}
