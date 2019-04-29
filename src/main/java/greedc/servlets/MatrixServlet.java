package greedc.servlets;

import java.util.List;

import weaver.hrm.User;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import greedc.services.MatrixService;

/**
 * 矩阵服务类。
 * @author William
 */
public class MatrixServlet extends BaseServlet {

	public Response listDeptIdsByPingfr() throws Exception {
		User user = getUser();
		if (user == null || user.getUID() <= 0) {
			return new Response(false, "会话超时，请重新登录！");
		}
		MatrixService service = new MatrixService();
		List<Integer> deptIds = service.listDeptIdsByPingfr(user.getUID());
		return new Response(true, "OK", deptIds);
	}
}
