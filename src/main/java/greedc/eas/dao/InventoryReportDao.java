package greedc.eas.dao;

import ecustom.util.CustomUtil;
import weaver.conn.RecordSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryReportDao {

	private static final List<Map<String, String>> IGNORE_LIST_FOR_EAS_7_5 = new ArrayList<Map<String, String>>();

	static {
		IGNORE_LIST_FOR_EAS_7_5.add(new HashMap<String, String>());
		IGNORE_LIST_FOR_EAS_7_5.get(0).put("area", "S1");
		IGNORE_LIST_FOR_EAS_7_5.get(0).put("building", "28栋");

		IGNORE_LIST_FOR_EAS_7_5.add(new HashMap<String, String>());
		IGNORE_LIST_FOR_EAS_7_5.get(1).put("area", "S3");

		IGNORE_LIST_FOR_EAS_7_5.add(new HashMap<String, String>());
		IGNORE_LIST_FOR_EAS_7_5.get(2).put("area", "S4");

		IGNORE_LIST_FOR_EAS_7_5.add(new HashMap<String, String>());
		IGNORE_LIST_FOR_EAS_7_5.get(3).put("area", "S5");

		IGNORE_LIST_FOR_EAS_7_5.add(new HashMap<String, String>());
		IGNORE_LIST_FOR_EAS_7_5.get(4).put("area", "S6");

		IGNORE_LIST_FOR_EAS_7_5.add(new HashMap<String, String>());
		IGNORE_LIST_FOR_EAS_7_5.get(5).put("area", "S7");
	}

	public List<Map<String, Object>> getRowsByRoomModel(String startDate, String endDate) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		String sql = SQL_ROOM_MODEL_EAS7_5.replaceAll("%dateFrom%", startDate).replaceAll("%dateTo%", endDate);
		System.out.println(sql);
		rows.addAll(getRows(sql, "EAS", false));

		sql = SQL_ROOM_MODEL_EASWEB.replaceAll("%dateFrom%", startDate).replaceAll("%dateTo%", endDate);
		System.out.println(sql);
		rows.addAll(getRows(sql, "EASWEB", true));

		return rows;
	}

	public List<Map<String,Object>> getRowsByBuilding(String startDate, String endDate) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		String sql = SQL_BUILDING_EAS7_5.replaceAll("%dateFrom%", startDate).replaceAll("%dateTo%", endDate);
		System.out.println(sql);
		rows.addAll(getRows(sql, "EAS", false));

		sql = SQL_BUILDING_EASWEB.replaceAll("%dateFrom%", startDate).replaceAll("%dateTo%", endDate);
		System.out.println(sql);
		rows.addAll(getRows(sql, "EASWEB", true));

		return rows;
	}

	private List<Map<String, Object>> getRows(String sql, String datasourceName, boolean isEASWeb) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		RecordSet rs = new RecordSet();
		if (rs.executeSql(sql, datasourceName)) {
			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("projectName", rs.getString("projectName"));
				row.put("area", getArea(rs, isEASWeb));
				row.put("productType", rs.getString("productType"));
				row.put("building", rs.getString("building"));
				row.put("roomModel", rs.getString("roomModel"));
				row.put("totalQuantity", CustomUtil.getDouble(rs.getString("totalQuantity"), null));
				row.put("totalArea", CustomUtil.getDouble(rs.getString("totalArea"), null));
				row.put("salesQuantity", CustomUtil.getDouble(rs.getString("salesQuantity"), null));
				row.put("salesArea", CustomUtil.getDouble(rs.getString("salesArea"), null));
				row.put("salesAmount", CustomUtil.getDouble(rs.getString("salesAmount"), null));
				row.put("persistQuantity", CustomUtil.getDouble(rs.getString("persistQuantity"), null));
				row.put("persistArea", CustomUtil.getDouble(rs.getString("persistArea"), null));
				row.put("inventoryQuantity", CustomUtil.getDouble(rs.getString("inventoryQuantity"), null));
				row.put("inventoryArea", CustomUtil.getDouble(rs.getString("inventoryArea"), null));
				if (!isEASWeb && isIgnoreFor7_5(row)) {
					continue ;
				}
				rows.add(row);
			}
		}
		return rows;
	}

	private boolean isIgnoreFor7_5(Map<String, Object> row) {
		boolean ignore = false;
		for (Map<String, String> ignoreRow : IGNORE_LIST_FOR_EAS_7_5) {
			boolean allEq = true;
			for (String ignoreField : ignoreRow.keySet()) {
				allEq = allEq && row.get(ignoreField).equals(ignoreRow.get(ignoreField));
			}
			if (allEq) {
				ignore = true;
				break ;
			}
		}
		return ignore;
	}

	private String getArea(RecordSet rs, boolean isEASWeb) {
		if (isEASWeb) {
			String[] strArr = rs.getString("projectName2").split("_");
			return strArr.length > 1 ? strArr[1] : strArr[0];
		} else {
			return rs.getString("area");
		}
	}

	private static final String SQL_ROOM_MODEL_EAS7_5 = "SELECT " +
			" SELLPROJECT.FNAME_L2 projectName, " +
			" T_FDC_ProductType.FNAME_L2 productType, " +
			" T_SHE_Subarea.fname_l2 area, " +
			" T_SHE_RoomModel.fname_l2 roomModel, " +
			" COUNT (QUAN.FID) totalQuantity, " +
			" SUM (QUAN.AREA) totalArea, " +
			" COUNT (XIAOSHOU.FID) salesQuantity, " +
			" SUM (XIAOSHOU.AREA) salesArea, " +
			" SUM (XIAOSHOU.AMOUNT) salesAmount, " +
			" COUNT (BAO.FID) persistQuantity, " +
			" SUM (BAO.AREA) persistArea, " +
			" COUNT (KUCUN.FID) inventoryQuantity, " +
			" SUM (KUCUN.AREA) inventoryArea " +
			"FROM " +
			" T_SHE_Room ROOM " +
			"LEFT JOIN T_SHE_Building ON ROOM.fbuildingid = T_SHE_Building.fid " +
			"LEFT JOIN T_SHE_RoomModel T_SHE_RoomModel ON ROOM.FRoomModelID = T_SHE_RoomModel.FID " +
			"LEFT JOIN T_SHE_Subarea T_SHE_Subarea ON T_SHE_Subarea.fid = T_SHE_Building.fsubareaid " +
			"LEFT JOIN T_FDC_ProductType T_FDC_ProductType ON ROOM.FproductTypeID = T_FDC_ProductType.FID " +
			"INNER JOIN T_SHE_SellProject SELLPROJECT ON T_SHE_Building.FSellProjectID = SELLPROJECT.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			") QUAN ON QUAN.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA, " +
			"  ROOM.FdealTotalAmount AMOUNT " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  ( " +
			"   room.FSELLSTATE = 'Sign' " +
			"   OR room.FSELLSTATE = 'Purchase' " +
			"  ) " +
			" AND ROOM.FtoPurchaseDate >= TO_DATE ('%dateFrom%', 'yyyy-MM-dd') " +
			" AND ROOM.FtoPurchaseDate <= TO_DATE ('%dateTo%', 'yyyy-MM-dd')" +
			") XIAOSHOU ON XIAOSHOU.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  room.FSELLSTATE = 'KeepDown' " +
			") BAO ON BAO.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA, " +
			"  ROOM.FstandardTotalAmount AMOUNT " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  room.FSELLSTATE = 'Onshow' " +
			" OR room.FSELLSTATE = 'Init' " +
			" OR ( " +
			"  ( " +
			"   room.FSELLSTATE = 'Sign' " +
			"   OR room.FSELLSTATE = 'Purchase' " +
			"  ) AND (ROOM.FtoPurchaseDate > TO_DATE ('%dateTo%', 'yyyy-MM-dd'))" +
			"  AND (ROOM.FtoPurchaseDate > TO_DATE ('%dateFrom%', 'yyyy-MM-dd'))" +
			" ) " +
			") KUCUN ON KUCUN.FID = ROOM.FID " +
			"WHERE ROOM.fname_l2 NOT LIKE '%格力海岸-S2-高层-28栋%'" +
			" AND ROOM.fname_l2 NOT LIKE '%平沙九号花园-二期%' " +
		    " AND ROOM.fname_l2 NOT LIKE '%格力海岸-S1-车位%'/*排除格力海岸S1车位*/" +
			" AND ROOM.fname_l2 NOT LIKE '%格力海岸-S2-车 位%'/*排除格力海岸S2车位*/" +
			"GROUP BY " +
			" T_SHE_RoomModel.fname_l2, " +
			" T_FDC_ProductType.FNAME_L2, " +
			" T_SHE_Subarea.fname_l2, " +
			" SELLPROJECT.FNAME_L2 " +
			"ORDER BY " +
			" T_SHE_RoomModel.fname_l2";

	private static final String SQL_BUILDING_EAS7_5 = "SELECT " +
			" SELLPROJECT.FNAME_L2 projectName, " +
			" T_FDC_ProductType.FNAME_L2 productType, " +
			" T_SHE_Subarea.fname_l2 area, " +
			" ROOM2.LOUDONG building, " +
			" COUNT (QUAN.FID) totalQuantity, " +
			" SUM (QUAN.AREA) totalArea, " +
			" COUNT (XIAOSHOU.FID) salesQuantity, " +
			" SUM (XIAOSHOU.AREA) salesArea, " +
			" SUM (XIAOSHOU.AMOUNT) salesAmount, " +
			" COUNT (BAO.FID) persistQuantity, " +
			" SUM (BAO.AREA) persistArea, " +
			" COUNT (KUCUN.FID) inventoryQuantity, " +
			" SUM (KUCUN.AREA) inventoryArea " +
			"FROM " +
			" T_SHE_Room ROOM " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM1.fid FID, " +
			"  ROOM1.fname_l2 FNAME, " +
			"  CASE " +
			" WHEN ( " +
			"  ROOM1.fname_l2 LIKE '%格力广场-一期C区%' " +
			"  OR ROOM1.fname_l2 LIKE '%格力广场-二期-商铺%' " +
			"  OR ROOM1.fname_l2 LIKE '%格力广场-二期-住宅%' " +
			"  OR ROOM1.fname_l2 LIKE '%格力海岸-S1%' " +
			"  OR ROOM1.fname_l2 LIKE '%格力海岸-S2%' " +
			"  OR ROOM1.fname_l2 LIKE '%平沙九号花园-一期%' " +
			" ) THEN " +
			"  T_SHE_BuildingUnit.FNAME_L2 " +
			" ELSE " +
			"  T_SHE_Building1.fname_l2 " +
			" END LOUDONG " +
			" FROM " +
			"  T_SHE_Room ROOM1 " +
			" LEFT JOIN T_SHE_Building T_SHE_Building1 ON room1.fbuildingid = T_SHE_Building1.fid " +
			" LEFT JOIN T_SHE_BuildingUnit T_SHE_BuildingUnit ON ROOM1.FBuildUnitID = T_SHE_BuildingUnit.FID " +
			") ROOM2 ON ROOM.FID = ROOM2.FID " +
			"LEFT JOIN T_SHE_Building T_SHE_Building ON room.fbuildingid = T_SHE_Building.fid " +
			"LEFT JOIN T_SHE_Subarea T_SHE_Subarea ON T_SHE_Subarea.fid = T_SHE_Building.fsubareaid " +
			"LEFT JOIN T_FDC_ProductType T_FDC_ProductType ON ROOM.FproductTypeID = T_FDC_ProductType.FID " +
			"INNER JOIN T_SHE_SellProject SELLPROJECT ON T_SHE_Building.FSellProjectID = SELLPROJECT.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			") QUAN ON QUAN.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA, " +
			"  ROOM.FdealTotalAmount AMOUNT " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  ( " +
			"   room.FSELLSTATE = 'Sign' " +
			"   OR room.FSELLSTATE = 'Purchase' " +
			"  ) " +
			" AND ( " +
			"  ROOM.FtoPurchaseDate >=TO_DATE ('%dateFrom%', 'yyyy-MM-dd') " +
			"  AND ROOM.FtoPurchaseDate <=TO_DATE ('%dateTo%', 'yyyy-MM-dd') " +
			" ) " +
			") XIAOSHOU ON XIAOSHOU.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  room.FSELLSTATE = 'KeepDown' " +
			") BAO ON BAO.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA, " +
			"  ROOM.FstandardTotalAmount AMOUNT " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  room.FSELLSTATE = 'Onshow' " +
			" OR room.FSELLSTATE = 'Init' " +
			" OR ( " +
			"  ( " +
			"   room.FSELLSTATE = 'Sign' " +
			"   OR room.FSELLSTATE = 'Purchase' " +
			"  ) " +
			"  AND ( " +
			"   ROOM.FtoPurchaseDate > TO_DATE ('%dateTo%', 'yyyy-MM-dd') " +
			"  ) " +
			" ) " +
			") KUCUN ON KUCUN.FID = ROOM.FID " +
			"WHERE ROOM.fname_l2 NOT LIKE '%格力海岸-S2-高层-28栋%'" +
			" AND ROOM.fname_l2 NOT LIKE '%平沙九号花园-二期%' " +
		    " AND ROOM.fname_l2 NOT LIKE '%格力海岸-S1-车位%'/*排除格力海岸S1车位*/" +
			" AND ROOM.fname_l2 NOT LIKE '%格力海岸-S2-车 位%'/*排除格力海岸S2车位*/" +
			"GROUP BY " +
			" T_FDC_ProductType.FNAME_L2, " +
			" T_SHE_Subarea.fname_l2, " +
			" SELLPROJECT.FNAME_L2, " +
			" ROOM2.LOUDONG " +
			"ORDER BY " +
			" ROOM2.LOUDONG, " +
			" T_FDC_ProductType.FNAME_L2 ASC";

	private static final String SQL_ROOM_MODEL_EASWEB = "SELECT  " +
			" SELLPROJECT.FCONTRACTPROJECTNAME projectName,  " +
			" SELLPROJECT.FDISPLAYNAME_L2 projectName2,  " +
			" T_FDC_ProductType.FNAME_L2 productType,  " +
			" T_SHE_Subarea.fname_l2 area,  " +
			" T_SHE_RoomTypeGroup.fname_l2 roomModel,  " +
			" COUNT (QUAN.FID) totalQuantity,  " +
			" SUM (QUAN.AREA) totalArea,  " +
			" COUNT (XIAOSHOU.FID) salesQuantity,  " +
			" SUM (XIAOSHOU.AREA) salesArea,  " +
			" SUM (XIAOSHOU.XSAMOUNT) salesAmount,  " +
			" COUNT (BAO.FID) persistQuantity,  " +
			" SUM (BAO.AREA) persistArea,  " +
			" COUNT (KUCUN.FID) inventoryQuantity,  " +
			" SUM (KUCUN.AREA) inventoryArea  " +
			"FROM  " +
			" T_SHE_Room ROOM  " +
			"LEFT JOIN T_SHE_Building T_SHE_Building ON room.fbuildingid = T_SHE_Building.fid  " +
			"LEFT JOIN T_SHE_RoomModel T_SHE_RoomModel ON ROOM.FRoomModelID = T_SHE_RoomModel.FID  " +
			"LEFT JOIN T_SHE_Subarea T_SHE_Subarea ON T_SHE_Subarea.fid = T_SHE_Building.fsubareaid  " +
			"LEFT JOIN T_FDC_ProductType T_FDC_ProductType ON ROOM.FproductTypeID = T_FDC_ProductType.FID  " +
			"LEFT JOIN T_SHE_RoomTypeGroup T_SHE_RoomTypeGroup ON T_SHE_RoomModel.froommodeltypegroupid = T_SHE_RoomTypeGroup.FID " +
			"INNER JOIN T_SHE_SellProject SELLPROJECT ON T_SHE_Building.FSellProjectID = SELLPROJECT.FID  " +
			"LEFT JOIN (  " +
			" SELECT  " +
			"  ROOM.FID FID,  " +
			"  ROOM.FBUILDINGAREA AREA  " +
			" FROM  " +
			"  T_SHE_Room ROOM  " +
			") QUAN ON QUAN.FID = ROOM.FID  " +
			"LEFT JOIN (  " +
			" SELECT  " +
			"  ROOM.FID FID,  " +
			"  ROOM.FBUILDINGAREA AREA,  " +
			"  ROOM.FdealTotalAmount AMOUNT,  " +
			"  RG.FContractTotalAmount XSAMOUNT  " +
			" FROM  " +
			"  T_SHE_Room ROOM  " +
			" LEFT JOIN T_SHE_PurchaseManage RG ON ROOM.FID = RG.FRoomID  " +
			" WHERE  " +
			"  (  " +
			"   room.FSELLSTATE = 'Sign'  " +
			"   OR room.FSELLSTATE = 'Purchase'  " +
			"  )  " +
			" AND RG.FState = '4AUDITTED'  " +
			" AND RG.fbizdate >=TO_DATE('%dateFrom%', 'yyyy-MM-dd')  " +
			" AND RG.fbizdate <=TO_DATE('%dateTo%', 'yyyy-MM-dd')  " +
			") XIAOSHOU ON XIAOSHOU.FID = ROOM.FID  " +
			"LEFT JOIN (  " +
			" SELECT  " +
			"  ROOM.FID FID,  " +
			"  ROOM.FBUILDINGAREA AREA  " +
			" FROM  " +
			"  T_SHE_Room ROOM  " +
			" WHERE  " +
			"  room.FSELLSTATE = 'KeepDown'  " +
			") BAO ON BAO.FID = ROOM.FID  " +
			"LEFT JOIN (  " +
			" SELECT  " +
			"  ROOM.FID FID,  " +
			"  ROOM.FBUILDINGAREA AREA,  " +
			"  ROOM.FstandardTotalAmount AMOUNT  " +
			" FROM  " +
			"  T_SHE_Room ROOM  " +
			" WHERE  " +
			"  room.FSELLSTATE = 'Onshow'  " +
			" OR room.FSELLSTATE = 'Init'  " +
			" OR (  " +
			"  (  " +
			"   room.FSELLSTATE = 'Sign'  " +
			"   OR room.FSELLSTATE = 'Purchase'  " +
			"  )" +
			"  AND ROOM.FtoPurchaseDate >TO_DATE('%dateFrom%', 'yyyy-MM-dd')  " +
			" )  " +
			") KUCUN ON KUCUN.FID = ROOM.FID  " +
			"GROUP BY  " +
			" T_SHE_RoomTypeGroup.fname_l2,  " +
			" T_FDC_ProductType.FNAME_L2,  " +
			" T_SHE_Subarea.fname_l2,  " +
			" SELLPROJECT.FCONTRACTPROJECTNAME,  " +
			" SELLPROJECT.FDISPLAYNAME_L2  " +
			"ORDER BY  " +
			" T_SHE_RoomTypeGroup.fname_l2";

	private static final String SQL_BUILDING_EASWEB = "SELECT " +
			" SELLPROJECT.FCONTRACTPROJECTNAME projectName, " +
			" SELLPROJECT.FDISPLAYNAME_L2 projectName2, " +
			" T_FDC_ProductType.FNAME_L2 productType, " +
			" T_SHE_Subarea.fname_l2 area, " +
			" T_SHE_Building.fname_l2 building, " +
			" COUNT (QUAN.FID) totalQuantity, " +
			" SUM (QUAN.AREA) totalArea, " +
			" COUNT (XIAOSHOU.FID) salesQuantity, " +
			" SUM (XIAOSHOU.AREA) salesArea, " +
			" SUM (XIAOSHOU.XSAMOUNT) salesAmount, " +
			" COUNT (BAO.FID) persistQuantity, " +
			" SUM (BAO.AREA) persistArea, " +
			" COUNT (KUCUN.FID) inventoryQuantity, " +
			" SUM (KUCUN.AREA) inventoryArea " +
			"FROM " +
			" T_SHE_Room ROOM " +
			"LEFT JOIN T_SHE_Building T_SHE_Building ON room.fbuildingid = T_SHE_Building.fid " +
			"LEFT JOIN T_SHE_Subarea T_SHE_Subarea ON T_SHE_Subarea.fid = T_SHE_Building.fsubareaid " +
			"LEFT JOIN T_FDC_ProductType T_FDC_ProductType ON ROOM.FproductTypeID = T_FDC_ProductType.FID " +
			"INNER JOIN T_SHE_SellProject SELLPROJECT ON T_SHE_Building.FSellProjectID = SELLPROJECT.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			") QUAN ON QUAN.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA, " +
			"  ROOM.FdealTotalAmount AMOUNT, " +
			"  RG.FContractTotalAmount XSAMOUNT " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" LEFT JOIN T_SHE_PurchaseManage RG ON ROOM.FID = RG.FRoomID " +
			" WHERE " +
			"  ( " +
			"   room.FSELLSTATE = 'Sign' " +
			"   OR room.FSELLSTATE = 'Purchase' " +
			"  ) " +
			" AND RG.FState = '4AUDITTED' " +
			" AND RG.fbizdate >= TO_DATE ('%dateFrom%', 'yyyy-MM-dd') " +
			" AND RG.fbizdate <= TO_DATE ('%dateTo%', 'yyyy-MM-dd') " +
			") XIAOSHOU ON XIAOSHOU.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  room.FSELLSTATE = 'KeepDown' " +
			") BAO ON BAO.FID = ROOM.FID " +
			"LEFT JOIN ( " +
			" SELECT " +
			"  ROOM.FID FID, " +
			"  ROOM.FBUILDINGAREA AREA, " +
			"  ROOM.FstandardTotalAmount AMOUNT " +
			" FROM " +
			"  T_SHE_Room ROOM " +
			" WHERE " +
			"  room.FSELLSTATE = 'Onshow' " +
			" OR room.FSELLSTATE = 'Init' " +
			" OR ( " +
			"  room.FSELLSTATE = 'Sign' " +
			"  OR room.FSELLSTATE = 'Purchase' " +
			" ) AND (ROOM.FtoPurchaseDate > TO_DATE ('%dateTo%', 'yyyy-MM-dd'))" +
			") KUCUN ON KUCUN.FID = ROOM.FID " +
			"GROUP BY " +
			" T_SHE_Building.fid, " +
			" T_FDC_ProductType.FNAME_L2, " +
			" T_SHE_Subarea.fname_l2, " +
			" SELLPROJECT.FDISPLAYNAME_L2, " +
			" T_SHE_Building.fname_l2, " +
			" SELLPROJECT.FCONTRACTPROJECTNAME " +
			"ORDER BY " +
			" T_SHE_Building.fname_l2, " +
			" T_FDC_ProductType.FNAME_L2";
}
