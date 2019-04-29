package greedc.servlets;

import ecustom.servlets.BaseServlet;
import ecustom.util.GsonUtil;
import greedc.eas.services.InventoryReportService;
import greedc.utils.JXLUtil;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InventoryReportServlet extends BaseServlet {

	public List<Map<String, Object>> getRowsByBuilding() {
		InventoryReportService service = new InventoryReportService();
		String startDate = getParameter("startDate", true);
		String endDate = getParameter("endDate", true);
		List<Map<String, Object>> rows = service.getRowsByBuilding(startDate, endDate);
		return rows;
	}

	public List<Map<String, Object>> getRowsByRoomModel() {
		InventoryReportService service = new InventoryReportService();
		String startDate = getParameter("startDate", true);
		String endDate = getParameter("endDate", true);
		List<Map<String, Object>> rows = service.getRowsByRoomModel(startDate, endDate);
		return rows;
	}

	public void download() throws Exception {
		String rowsJsonStr = getParameter("exportData", true);
		String fileName = getParameter("fileName", true);
		List list = GsonUtil.toObjectList(rowsJsonStr, List.class);
		OutputStream out = response().getOutputStream();
		try {
			String downName = new String((fileName + "-" + new Date().getTime()).getBytes("UTF-8"), "ISO8859-1");
			response().setContentType("octets/stream");
			response().setHeader("Content-Disposition", "attachment;filename=" + downName + ".xls");

			JXLUtil util = new JXLUtil();
			util.createWookbook(fileName, list, out);

		} finally {
			out.close();
		}
	}
}
