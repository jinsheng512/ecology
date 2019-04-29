package ecustom.dao;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import ecustom.entities.HrmAnnualManagement;

public class HrmAnnualManagementDao {

	public List<HrmAnnualManagement> list(int resourceId, String date) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT T1.ID, T1.RESOURCEID, T1.ANNUALYEAR, T1.ANNUALDAYS, T1.STATUS FROM HrmAnnualManagement T1"
				+ " INNER JOIN HRMANNUALPERIOD T2 ON T2.ANNUALYEAR=T1.ANNUALYEAR"
				+ " WHERE T2.STARTDATE<=? AND T2.ENDDATE>=? AND T1.RESOURCEID=? AND T1.STATUS=1";
		List<HrmAnnualManagement> rows = new ArrayList<HrmAnnualManagement>();
		if (rs.executeQuery(sql, date, date, resourceId)) {
			while (rs.next()) {
				HrmAnnualManagement entry = new HrmAnnualManagement();
				entry.setId(rs.getInt("ID"));
				entry.setResourceId(rs.getInt("RESOURCEID"));
				entry.setAnnualYear(rs.getString("ANNUALYEAR"));
				entry.setAnnualDays(rs.getDouble("ANNUALDAYS"));
				entry.setStatus(rs.getInt("STATUS"));
				rows.add(entry);
			}
		}
		return rows;
	}
}
