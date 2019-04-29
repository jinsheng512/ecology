package greedc.servlets;

import java.util.List;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.services.HrmResourceService;
import weaver.hrm.User;
import weaver.hrm.UserManager;

public class UserServlet extends BaseServlet{
	
	public Object changeSession() {
		String id = getParameter("id");
		UserManager um = new UserManager();
		User user = um.getUserByUserIdAndLoginType(CustomUtil.getInt(id), "1");
		request().getSession(true).setAttribute("weaver_user@bean", user);
		return user;
	}
	
	public Response getDeptIdsByBelong() throws Exception {
		User user = getUser();
		if (user == null || user.getUID() == 0) {
			return new Response(false, "会话超时，请重新登录！");
		}
		HrmResourceService service = new HrmResourceService();
		List<Integer> ids = service.getDeptIdsByBelong(user.getUID());
		if (!ids.contains(user.getUserDepartment())) {
			ids.add(user.getUserDepartment());
		}
		return new Response(true, "OK", ids);
	}
	
	public Response getCountByDept() {
		int deptId = Integer.parseInt(getParameter("deptId", true));
		HrmResourceService service = new HrmResourceService();
		int counts = service.getCountByDept(deptId);
		return new Response(true, "OK", counts);
	}
}
