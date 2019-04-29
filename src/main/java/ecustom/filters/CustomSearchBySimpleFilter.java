package ecustom.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomSearchBySimpleFilter implements Filter {

	private String filterCustomId = null;

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 参数格式：id1,id2
		filterCustomId = config.getInitParameter("filterCustomId");
		if (filterCustomId != null) {
			filterCustomId = "," + filterCustomId + ",";
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String customId = request.getParameter("customid");
		if (filterCustomId == null || customId == null
				|| !filterCustomId.contains("," + customId + ",")) {
			chain.doFilter(request, response);
			return ;
		}
		
		String queryString = request.getQueryString();
		response.sendRedirect("/formmode/search/CustomSearchBySimpleIframeNew.jsp?" + queryString);
	}

}
