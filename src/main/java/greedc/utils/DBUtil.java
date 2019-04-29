package greedc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import weaver.general.StaticObj;
import weaver.interfaces.datasource.DataSource;

public class DBUtil {

	public static Connection getConnection(String dsName) {
		DataSource ds = (DataSource) StaticObj.getServiceByFullname(("datasource." + dsName), DataSource.class);
		Connection conn = ds.getConnection();
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {try {rs.close();} catch (SQLException e) { e.printStackTrace(); } }
		if (stmt != null) {try {stmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if (conn != null) {try {conn.close();} catch (SQLException e) { e.printStackTrace(); } }
	}
}
