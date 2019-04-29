package greedc.servlets;

import ecustom.services.BigFieldService;
import ecustom.servlets.BaseServlet;
import ecustom.util.CustomUtil;
import greedc.entities.Form44;
import greedc.services.Form44Service;

public class Flow163Servlet extends BaseServlet {

	public Object getEntryData() throws Exception {
		int requestId = CustomUtil.getInt(getParameter("requestId", true));
		Form44Service formService = new Form44Service();
		Form44 form = formService.getByRequestId(requestId);
		BigFieldService bigFldService = new BigFieldService();
		String entryKeys = bigFldService.getValue(form.getEntriesSeqNo());
		return entryKeys;
	}
}
