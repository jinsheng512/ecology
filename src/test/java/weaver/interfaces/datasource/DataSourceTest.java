package weaver.interfaces.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import weaver.general.GCONST;
import weaver.general.StaticObj;

public class DataSourceTest {

	@Test
	public void getConnection() {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
		
		DataSource ds = (DataSource) StaticObj.getServiceByFullname(("datasource.EAS"), DataSource.class);
		Connection conn = ds.getConnection();
		try {
			System.out.println("conn: " + conn);
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			
		}
	}
}
