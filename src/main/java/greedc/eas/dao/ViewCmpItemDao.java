package greedc.eas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import greedc.eas.entities.ViewCmpItem;
import greedc.utils.DBUtil;

public class ViewCmpItemDao {

	public List<ViewCmpItem> getAll() {
		List<ViewCmpItem> items = new ArrayList<ViewCmpItem>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("EAS");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT ITEM, ITEMNUMBER, CATNUMBER, CATNAME, ORGNUMBER, ORGNAME FROM VIEW_CMPITEM");
			while (rs.next()) {
				ViewCmpItem item = new ViewCmpItem();
				item.setItem(rs.getString("ITEM"));
				item.setItemNumber(rs.getString("ITEMNUMBER"));
				item.setCatNumber(rs.getString("CATNUMBER"));
				item.setCatName(rs.getString("CATNAME"));
				item.setOrgNumber(rs.getString("ORGNUMBER"));
				item.setOrgName(rs.getString("ORGNAME"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(conn, stmt, rs);
		}
		return items;
	}
}
