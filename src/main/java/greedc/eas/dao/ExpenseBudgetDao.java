package greedc.eas.dao;

import ecustom.util.CustomUtil;
import greedc.eas.vo.ExpenseBudgetVo;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;

/**
 * 费用预算清单条目数据访问类。<br/>
 * 目前可用于：项目付款计划会签表。
 * @author 曹水涛
 */
public class ExpenseBudgetDao {
	
	/**
	 * 根据RequestId查找费用预算清单条目结果集。
	 * @param requestId
	 * @return
	 */
	public List<ExpenseBudgetVo> findByRequestId(int requestId) {
		RecordSet rs = new RecordSet();
		String sql = "SELECT SEQ_NO, REQUEST_ID, PROJECT_NAME, PAY_CO, ACC_PAYABLE,"
				+ " PROPORTION, PAY_TOTAL, CON_NUM, STATISTICAL_DEPT, YEAR_,"
				+ " MONTH_ FROM GREEDC_PRO_EXP_BUDGET WHERE REQUEST_ID=?";
		List<ExpenseBudgetVo> vos = new ArrayList<ExpenseBudgetVo>();
		if (rs.executeQuery(sql, requestId)) {
			while (rs.next()) {
				ExpenseBudgetVo vo = new ExpenseBudgetVo();
				vo.setSeqNo(rs.getInt("SEQ_NO"));
				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setProjectName(rs.getString("PROJECT_NAME"));
				vo.setPayCo(rs.getString("PAY_CO"));
				vo.setAccPayable(CustomUtil.isBlank(rs.getString("ACC_PAYABLE")) ? null : rs.getDouble("ACC_PAYABLE"));
				vo.setProportion(rs.getString("PROPORTION"));
				vo.setPayTotal(CustomUtil.isBlank(rs.getString("PAY_TOTAL")) ? null : rs.getDouble("PAY_TOTAL"));
				vo.setConNum(CustomUtil.isBlank(rs.getString("CON_NUM")) ? null : rs.getInt("CON_NUM"));
				vo.setStatisticalDept(rs.getString("STATISTICAL_DEPT"));
				vo.setYear(rs.getString("YEAR_"));
				vo.setMonth(rs.getString("MONTH_"));
				vos.add(vo);
			}
		}
		return vos;
	}
	
	public Integer getNextSeqNo() throws Exception {
		RecordSet rs = new RecordSet();
		if (rs.execute("SELECT GREEDC_PRO_EXP_BUDGET_SEQ.NEXTVAL FROM DUAL") && rs.next()) {
			return rs.getInt(1);
		}
		throw new Exception("生成序列号失败！");
	}
	
	public boolean save(ExpenseBudgetVo vo, RecordSetTrans rst) throws Exception {
		String sql = "INSERT INTO GREEDC_PRO_EXP_BUDGET(SEQ_NO, REQUEST_ID, PROJECT_ID, PROJECT_NAME, PAY_CO,"
				+ " ACC_PAYABLE, PROPORTION, PAY_TOTAL, CON_NUM, STATISTICAL_DEPT, YEAR_, MONTH_)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return rst.executeUpdate(sql, vo.getSeqNo(), vo.getRequestId(), vo.getProjectId(), vo.getProjectName(),
				vo.getPayCo(), vo.getAccPayable(), vo.getProportion(), vo.getPayTotal(), vo.getConNum(),
				vo.getStatisticalDept(), vo.getYear(), vo.getMonth());
	}
}
