package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.services.UFJidpfkhrService;

/**
 * 季度评分被考核建模请求服务类。
 * Created by William on 2017-6-27.
 */
public class UFJidpfkhrServlet extends BaseServlet {

	public Response getCountByDept() {
		int deptId = CustomUtil.getInteger(getParameter("deptId", true));
		UFJidpfkhrService service = new UFJidpfkhrService();
		int count = service.count(deptId);
		return new Response(true, null, count);
	}
}
