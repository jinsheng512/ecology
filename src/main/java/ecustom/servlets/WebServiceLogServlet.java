package ecustom.servlets;

import java.util.List;

import ecustom.entities.WebServiceLog;
import ecustom.services.BigFieldService;
import ecustom.services.WebServiceLogService;
import ecustom.util.CustomUtil;
import ecustom.xml.XmlFormatter;

public class WebServiceLogServlet extends BaseServlet {

	public Object findAll() {
		WebServiceLogService service = new WebServiceLogService();
		List<WebServiceLog> list = service.findAll();
		return list;
	}
	
	public String getWSBody() {
		int seqNo = CustomUtil.getInteger(request().getParameter("seqNo"));
		BigFieldService service = new BigFieldService();
		return XmlFormatter.formatXml(service.getValue(seqNo));
	}
	
	public Response delete() {
		int seqNo = CustomUtil.getInteger(request().getParameter("seqNo"));
		WebServiceLogService service = new WebServiceLogService();
		return new Response(service.delete(seqNo));
	}
}
