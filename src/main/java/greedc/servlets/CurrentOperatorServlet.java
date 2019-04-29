package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import ecustom.workflow.dao.CurrentOperatorDao;

/**
 * 流程当前操作者请求处理类。
 * @author William
 */
public class CurrentOperatorServlet extends BaseServlet {

	/**
	 * 获取已经流转过的节点数量，含待办节点。
	 * @return
	 */
	public Response getFlowedNode() {
		int requestId = CustomUtil.getInt(getParameter("requestId", true));
		int flowedNode = new CurrentOperatorDao().getFlowedNode(requestId);
		Response resp = new Response(true, "" + flowedNode);
		return resp;
	}
}
