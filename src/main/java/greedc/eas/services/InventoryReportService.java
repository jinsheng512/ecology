package greedc.eas.services;

import greedc.eas.dao.InventoryReportDao;

import java.util.List;
import java.util.Map;

public class InventoryReportService {

	public List<Map<String, Object>> getRowsByBuilding(String startDate, String endDate) {
		InventoryReportDao dao = new InventoryReportDao();
		List<Map<String, Object>> rows = dao.getRowsByBuilding(startDate, endDate);
		return rows;
	}

	public List<Map<String, Object>> getRowsByRoomModel(String startDate, String endDate) {
		InventoryReportDao dao = new InventoryReportDao();
		List<Map<String, Object>> rows = dao.getRowsByRoomModel(startDate, endDate);
		return rows;
	}
}
