package test.greedc.eas.dao;

import ecustom.util.GsonUtil;
import greedc.eas.dao.InventoryReportDao;
import org.junit.Test;
import test.BaseTest;

import java.util.List;
import java.util.Map;

public class TestInventoryReportDao extends BaseTest {

	@Test
	public void getRowsByBuilding() {
		InventoryReportDao dao = new InventoryReportDao();
		List<Map<String, Object>> rows = dao.getRowsByBuilding("2017-01-01", "2017-12-31");
		System.out.println(GsonUtil.toJSONString(rows));
	}

	@Test
	public void getRowsByRoomModel() {
		InventoryReportDao dao = new InventoryReportDao();
		List<Map<String, Object>> rows = dao.getRowsByRoomModel("2017-01-01", "2017-12-31");
		System.out.println(GsonUtil.toJSONString(rows));
	}
}
