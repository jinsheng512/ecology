package ecustom.servlets;

import ecustom.services.BigFieldService;
import ecustom.util.CustomUtil;

public class BigFieldServlet extends BaseServlet {

	public String getValue() {
		int seqNo = CustomUtil.getInteger(request().getParameter("seqNo"));
		BigFieldService service = new BigFieldService();
		return service.getValue(seqNo);
	}
}
