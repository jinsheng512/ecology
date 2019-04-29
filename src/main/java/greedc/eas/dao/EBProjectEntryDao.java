package greedc.eas.dao;

import greedc.eas.vo.EBProjectEntryVo;
import greedc.eas.vo.ExpenseBudgetVo;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

public class EBProjectEntryDao {
	
	public List<EBProjectEntryVo> findByMainSeqNo(int seqNo, int goux) {
		RecordSet rs = new RecordSet();
		List<EBProjectEntryVo> vos = new ArrayList<EBProjectEntryVo>();
		String sql = "SELECT SEQ_NO, MAIN_SEQ_NO, BIANMXH, GOUX, GONGCXM, HETBM, HETMC, HETJE,"
				+ " TIAOZHJE, XINGXJDMS, YUECLJJE, YUECLJBL, BENYWCJD, BENYJDFK, JE, YIFKBL,"
				+ " BENYJHFKE, LEIJBL, KUANXLB, BEIZ, SHENPRYJ, SHOUKDWQC, SHIJSKDW"
				+ " FROM GREEDC_EB_PRO_ENTRY WHERE %s MAIN_SEQ_NO=?";
		boolean exeFlag = false;
		if (goux >= 0) {
			sql = String.format(sql, "GOUX=? AND");
			exeFlag = rs.executeQuery(sql, goux, seqNo);
		} else {
			sql = String.format(sql, "");
			exeFlag = rs.executeQuery(sql, seqNo);
		}
		if (exeFlag) {
			while (rs.next()) {
				EBProjectEntryVo vo = new EBProjectEntryVo();
				vo.setSeqNo(rs.getInt("SEQ_NO"));
				vo.setMainSeqNo(rs.getInt("MAIN_SEQ_NO"));
				vo.setBianmxh(rs.getString("BIANMXH"));
				vo.setGoux(rs.getInt("GOUX"));
				vo.setGongcxm(rs.getString("GONGCXM"));
				vo.setHetbm(rs.getString("HETBM"));
				vo.setHetmc(rs.getString("HETMC"));
				vo.setHetje(rs.getDouble("HETJE"));
				vo.setTiaozhje(rs.getDouble("TIAOZHJE"));
				vo.setXingxjdms(rs.getString("XINGXJDMS"));
				vo.setYuecljje(rs.getDouble("YUECLJJE"));
				vo.setYuecljbl(rs.getString("YUECLJBL"));
				vo.setBenywcjd(rs.getString("BENYWCJD"));
				vo.setBenyjdfk(rs.getString("BENYJDFK"));
				vo.setJe(rs.getDouble("JE"));
				vo.setYifkbl(rs.getString("YIFKBL"));
				vo.setBenyjhfke(rs.getDouble("BENYJHFKE"));
				vo.setLeijbl(rs.getString("LEIJBL"));
				vo.setKuanxlb(rs.getString("KUANXLB"));
				vo.setBeiz(rs.getString("BEIZ"));
				vo.setShenpryj(rs.getString("SHENPRYJ"));
				vo.setShoukdwqc(rs.getString("SHOUKDWQC"));
				vo.setShijskdw(rs.getString("SHIJSKDW"));
				vos.add(vo);
			}
		}
		return vos;
	}
	
	public Integer getNextSeqNo() throws Exception {
		RecordSet rs = new RecordSet();
		if (rs.execute("SELECT GREEDC_EB_PRO_ENTRY_SEQ.NEXTVAL FROM DUAL") && rs.next()) {
			return rs.getInt(1);
		}
		throw new Exception("生成序列号失败！");
	}
	
	public boolean save(ExpenseBudgetVo vo, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO GREEDC_PRO_EXP_BUDGET(SEQ_NO, REQUEST_ID, PROJECT_NAME, PAY_CO,"
				+ " ACC_PAYABLE, PROPORTION, PAY_TOTAL, CON_NUM, STATISTICAL_DEPT, YEAR_, MONTH_)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return rst.executeUpdate(sql, vo.getSeqNo(), vo.getRequestId(), vo.getProjectName(), vo.getPayCo(),
				vo.getAccPayable(), vo.getProportion(), vo.getPayTotal(), vo.getConNum(),
				vo.getStatisticalDept(), vo.getYear(), vo.getMonth());
	}
	
	public boolean save(EBProjectEntryVo vo, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO GREEDC_EB_PRO_ENTRY(SEQ_NO, MAIN_SEQ_NO, BIANMXH, GOUX, GONGCXM, HETBM, HETMC,"
				+ " HETJE, TIAOZHJE, XINGXJDMS, YUECLJJE, YUECLJBL, BENYWCJD, BENYJDFK, JE, YIFKBL, BENYJHFKE,"
				+ " LEIJBL, KUANXLB, BEIZ, SHENPRYJ, SHOUKDWQC, SHIJSKDW) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return rst.executeUpdate(sql, vo.getSeqNo(), vo.getMainSeqNo(), vo.getBianmxh(), vo.getGoux(),
				vo.getGongcxm(), vo.getHetbm(), vo.getHetmc(), vo.getHetje(), vo.getTiaozhje(),
				vo.getXingxjdms(), vo.getYuecljje(), vo.getYuecljbl(), vo.getBenywcjd(), vo.getBenyjdfk(),
				vo.getJe(), vo.getYifkbl(), vo.getBenyjhfke(), vo.getLeijbl(), vo.getKuanxlb(), vo.getBeiz(),
				vo.getShenpryj(), vo.getShoukdwqc(), vo.getShijskdw());
	}
	
	public boolean updateGoux(EBProjectEntryVo vo, RecordSetTrans rst) throws Exception {
		String sql = "UPDATE GREEDC_EB_PRO_ENTRY SET GOUX=? WHERE SEQ_NO=?";
		return rst.executeUpdate(sql, vo.getGoux(), vo.getSeqNo());
	}

	/**
	 * 获取费用清单驳回数量。
	 * @param requestId
	 * @return
	 */
	public int getRejectCount(int requestId) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT COUNT(1) ROWCOUNT FROM GREEDC_EB_PRO_ENTRY T1 "
				+ "INNER JOIN GREEDC_PRO_EXP_BUDGET T2 ON T2.SEQ_NO=T1.MAIN_SEQ_NO "
				+ "WHERE GOUX IN (0, 2) AND T2.REQUEST_ID=?";
		if (rs.executeQuery(sql, requestId) && rs.next()) {
			return rs.getInt("ROWCOUNT");
		}
		return -1;
	}
}
