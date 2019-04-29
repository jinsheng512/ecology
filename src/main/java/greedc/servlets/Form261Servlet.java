package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.services.Form60Service;

/**
 * 项目合同结算单。
 * @author 曹水涛
 */
public class Form261Servlet extends BaseServlet {

	public Response getFormByRequestId() {
		int requestId = CustomUtil.getInt(getParameter("requestId", true));
		Response resp = new Response(true, "OK", new Form60Service().getFormByRequestId(requestId));
		return resp;
	}
}
