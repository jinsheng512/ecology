package test;

import org.junit.Test;

import weaver.conn.RecordSet;

public class TestTempPassword extends BaseTest {

	@Test
	public void execute() throws Exception {
		int id = 1;
		String table = id == 1 ? "HRMRESOURCEMANAGER" : "HRMRESOURCE";
		String sql = "SELECT PASSWORD FROM " + table + " WHERE ID=?";
		RecordSet rs = new RecordSet();
		if (!rs.executeQuery(sql, id)) {
			throw new Exception("查询密码失败，ID = " + id);
		}
		
		String password = rs.next() ? rs.getString(1) : "查询无记录";
		System.out.println("原密码：" + password);
		sql = "UPDATE " + table + " SET PASSWORD=? WHERE ID=?";
		rs.executeUpdate(sql, "C4CA4238A0B923820DCC509A6F75849B", id);
		System.out.println("临时密码生效中...");
		
		Thread.sleep(10000);	// 10秒钟
		
		rs.executeUpdate(sql, password, id);
		System.out.println("临时密码已失效！");
	}
}
