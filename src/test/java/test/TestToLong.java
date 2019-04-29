package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import weaver.conn.ConnectionPool;
import weaver.conn.WeaverConnection;

public class TestToLong extends BaseTest {

	@Test
	public void execute() {
		WeaverConnection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		String fv = "{\"proPay4_6\":\"原币\",\"proPay10_2\":\"\",\"proPay4_7\":\"本币\",\"proPay10_1\":\"水费\",\"proPay4_8\":\"原币\",\"proPay10_4\":\"0\",\"proPay4_9\":\"本币\",\"proPay10_3\":\"\",\"proPay10_0\":\"应扣款项\",\"proPay8_0\":\"\",\"proPay8_1\":\"奖励\",\"proPay11_1\":\"电费\",\"proPay11_0\":\"\",\"proPay11_3\":\"\",\"proPay11_2\":\"\",\"proPay11_4\":\"0\",\"proPay8_9\":\"0\",\"proPay11_5\":\"\",\"proPay8_8\":\"0\",\"proPay11_6\":\"\",\"proPay8_7\":\"\",\"proPay11_7\":\"\",\"proPay8_6\":\"\",\"proPay11_8\":\"0\",\"proPay8_5\":\"\",\"proPay8_4\":\"\",\"proPay11_9\":\"0\",\"proPay8_3\":\"\",\"proPay8_2\":\"\",\"proPay10_9\":\"0\",\"proPay4_1\":\"\",\"proPay4_0\":\"\",\"proPay4_3\":\"本币\",\"proPay10_5\":\"\",\"proPay4_2\":\"原币\",\"proPay10_6\":\"\",\"proPay4_5\":\"本币\",\"proPay10_7\":\"\",\"proPay4_4\":\"原币\",\"proPay10_8\":\"0\",\"proPay19_10\":\"\",\"proPay19_11\":\"\",\"proPay8_11\":\"0\",\"proPay8_10\":\"0\",\"proPay16_2\":\"0\",\"proPay15_1\":\"\",\"proPay16_1\":\"\",\"proPay15_0\":\"应扣甲供材款\",\"proPay15_3\":\"\",\"proPay16_0\":\"实付款\",\"proPay15_2\":\"0\",\"proPay15_5\":\"0\",\"proPay16_6\":\"78186.00\",\"proPay17_9\":\"\",\"proPay14_9\":\"0\",\"proPay15_4\":\"0\",\"proPay16_5\":\"78186\",\"proPay17_8\":\"\",\"proPay0_0\":\"合同名称\",\"proPay13_1\":\"配合费\",\"proPay15_7\":\"0\",\"proPay16_4\":\"78186.00\",\"proPay17_7\":\"\",\"proPay0_1\":\"格力海岸S4地块地下室-车位分割测绘业务合同书\",\"proPay13_0\":\"\",\"proPay15_6\":\"\",\"proPay16_3\":\"0\",\"proPay17_6\":\"\",\"proPay12_0\":\"\",\"proPay0_2\":\"\",\"proPay14_6\":\"0\",\"proPay15_9\":\"0\",\"proPay17_5\":\"\",\"proPay0_3\":\"\",\"proPay14_5\":\"0\",\"proPay15_8\":\"0\",\"proPay16_9\":\"156372\",\"proPay17_4\":\"\",\"proPay12_2\":\"\",\"proPay0_4\":\"\",\"proPay14_8\":\"0\",\"proPay16_8\":\"156372\",\"proPay17_3\":\"\",\"proPay12_1\":\"罚款\",\"proPay0_5\":\"\",\"proPay14_7\":\"0\",\"proPay16_7\":\"78186.00\",\"proPay17_2\":\"\",\"proPay0_6\":\"合同造价原币\",\"proPay14_2\":\"0\",\"proPay17_1\":\"\",\"proPay0_7\":\"78186.00\",\"proPay14_1\":\"小计\",\"proPay17_0\":\"余款\",\"proPay0_8\":\"\",\"proPay14_4\":\"0\",\"proPay14_3\":\"0\",\"proPay0_9\":\"合同造价本币\",\"proPay19_2\":\"\",\"proPay18_7\":\"\",\"proPay12_7\":\"\",\"proPay19_3\":\"累计申请%\",\"proPay18_8\":\"\",\"proPay12_8\":\"0\",\"proPay19_0\":\"本次申请%\",\"proPay18_5\":\"\",\"proPay12_9\":\"0\",\"proPay19_1\":\"100\",\"proPay18_6\":\"\",\"proPay14_0\":\"\",\"proPay12_3\":\"\",\"proPay12_4\":\"0\",\"proPay18_9\":\"\",\"proPay12_5\":\"\",\"proPay12_6\":\"\",\"proPay13_2\":\"\",\"proPay18_0\":\"本期计划付款\",\"proPay13_3\":\"\",\"proPay19_8\":\"\",\"proPay13_4\":\"0\",\"proPay19_9\":\"\",\"proPay13_5\":\"\",\"proPay19_6\":\"形象进度%\",\"proPay18_3\":\"本期欠付款\",\"proPay13_6\":\"\",\"proPay19_7\":\"\",\"proPay18_4\":\"\",\"proPay13_7\":\"\",\"proPay19_4\":\"100\",\"proPay18_1\":\"\",\"proPay13_8\":\"0\",\"proPay19_5\":\"\",\"proPay18_2\":\"\",\"proPay13_9\":\"0\",\"proPay5_2\":\"0\",\"proPay5_1\":\"合同内工程款\",\"proPay5_4\":\"78186.00\",\"proPay5_3\":\"\",\"proPay9_9\":\"0\",\"proPay3_8\":\"截至本次累计申请\",\"proPay3_7\":\"\",\"proPay5_0\":\"进度款项\",\"proPay3_9\":\"\",\"proPay20_1\":\"100\",\"proPay9_4\":\"\",\"proPay20_0\":\"应付申请%\",\"proPay9_3\":\"\",\"proPay9_2\":\"\",\"proPay9_1\":\"违约金\",\"proPay20_5\":\"\",\"proPay7_2\":\"\",\"proPay9_8\":\"0\",\"proPay20_4\":\"100\",\"proPay7_1\":\"增加工程款\",\"proPay9_7\":\"\",\"proPay20_3\":\"累计应付申请%\",\"proPay7_0\":\"\",\"proPay9_6\":\"\",\"proPay20_2\":\"\",\"proPay9_5\":\"\",\"proPay20_8\":\"\",\"proPay7_5\":\"0\",\"proPay20_9\":\"\",\"proPay7_6\":\"\",\"proPay20_6\":\"\",\"proPay7_3\":\"\",\"proPay20_7\":\"\",\"proPay7_4\":\"\",\"proPay7_9\":\"0\",\"proPay9_0\":\"\",\"proPay7_7\":\"\",\"proPay7_8\":\"0\",\"proPay3_3\":\"\",\"proPay5_9\":\"156372\",\"proPay3_4\":\"截至上次累计申请\",\"proPay3_5\":\"\",\"proPay3_6\":\"本次申请\",\"proPay5_5\":\"78186\",\"proPay3_0\":\"合同付款次数\",\"proPay5_6\":\"78186.00\",\"proPay3_1\":\"0\",\"proPay5_7\":\"78186.00\",\"proPay3_2\":\"截至上次累计实付\",\"proPay5_8\":\"156372\",\"proPay10_10\":\"0\",\"proPay10_11\":\"0\",\"proPay12_10\":\"0\",\"proPay12_11\":\"0\",\"proPay20_11\":\"\",\"proPay17_11\":\"78186\",\"proPay14_10\":\"0\",\"proPay14_11\":\"0\",\"proPay20_10\":\"\",\"proPay17_10\":\"78186.00\",\"proPay0_10\":\"78186\",\"proPay0_11\":\"\",\"proPay15_11\":\"\",\"proPay15_10\":\"0\",\"proPay18_11\":\"\",\"proPay18_10\":\"\",\"proPay6_10\":\"0\",\"proPay6_11\":\"0\",\"proPay13_11\":\"0\",\"proPay1_9\":\"最新造价本币\",\"proPay13_10\":\"0\",\"proPay1_5\":\"\",\"proPay1_6\":\"最新造价原币\",\"proPay1_7\":\"78186.00\",\"proPay1_8\":\"\",\"proPay1_1\":\"\",\"proPay1_2\":\"\",\"proPay1_3\":\"变更指令金额本币\",\"proPay1_4\":\"\",\"proPay1_0\":\"变更指令金额原币\",\"proPay1_10\":\"78186\",\"proPay1_11\":\"\",\"proPay4_10\":\"原币\",\"proPay4_11\":\"本币\",\"proPay6_9\":\"0\",\"proPay6_8\":\"0\",\"proPay6_7\":\"\",\"proPay6_6\":\"\",\"proPay6_5\":\"\",\"proPay6_4\":\"\",\"proPay6_2\":\"\",\"proPay6_3\":\"\",\"proPay6_0\":\"\",\"proPay6_1\":\"预付款\",\"proPay7_10\":\"0\",\"proPay7_11\":\"0\",\"proPay2_3\":\"结算金额本币\",\"proPay2_2\":\"\",\"proPay2_1\":\"78186.00\",\"proPay2_0\":\"结算金额原币\",\"proPay9_11\":\"0\",\"proPay2_7\":\"0.00\",\"proPay9_10\":\"0\",\"proPay2_6\":\"本申请单已付原币\",\"proPay2_5\":\"\",\"proPay2_4\":\"78186\",\"proPay16_11\":\"0\",\"proPay16_10\":\"0\",\"proPay2_8\":\"\",\"proPay2_9\":\"本申请单已付本币\",\"proPay3_10\":\"截至本次累计实付\",\"proPay3_11\":\"\",\"proPay2_10\":\"0\",\"proPay2_11\":\"\",\"proPay5_11\":\"0\",\"proPay11_11\":\"0\",\"proPay5_10\":\"0\",\"proPay11_10\":\"0\"}";
		try {
			conn = pool.getConnection("ecology");
			stmt = conn.prepareStatement("UPDATE GREEDC_BIG_FIELD SET FLD_VALUE=?, FLD_VALUE1=' ' WHERE SEQ_NO=?");
			stmt.setString(1, fv);
			stmt.setInt(2, 52653);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if (stmt != null) try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
			if (conn != null) { pool.returnConnection("ecology", conn); }
		}
	}
}
