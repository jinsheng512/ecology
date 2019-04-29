package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.services.RequestService;

public class RequestServlet extends BaseServlet {

	public Response getCurrNodeId() throws Exception {
		int requestId = CustomUtil.getInt(getParameter("requestId", true));
		if (requestId <= 0) {
			throw new Exception("参数（requestId）无效！");
		}
		RequestService dao = new RequestService();
		int nodeId = dao.getCurrNodeId(requestId);
		return new Response(true, nodeId + "");
	}
}
