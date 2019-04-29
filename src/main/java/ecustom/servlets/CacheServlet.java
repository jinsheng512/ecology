package ecustom.servlets;

import ecustom.util.CustomUtil;
import weaver.general.StaticObj;

public class CacheServlet extends BaseServlet {

	public Object removeCache() {
		String name = request().getParameter("CustomerInfoInfo");
		if (CustomUtil.isNotBlank(name)) {
			StaticObj.getInstance().removeObject("CustomerInfoInfo");
		}
		return new Response(true, "清除缓存" + name + "成功");
	}
}
