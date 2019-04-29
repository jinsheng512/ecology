package greedc.eas.services;

import greedc.eas.dao.ExpenseBudgetDao;
import greedc.eas.vo.ExpenseBudgetVo;

import java.util.List;

import weaver.conn.RecordSetTrans;

/**
 * 费用预算清单条目数据服务类。<br/>
 * 目前可用于：项目付款计划会签表。
 * @author 曹水涛
 */
public class ExpenseBudgetService {

	/**
	 * 根据RequestId查找费用预算清单条目结果集。
	 * @param requestId
	 * @return
	 */
	public List<ExpenseBudgetVo> findByRequestId(int requestId) {
		List<ExpenseBudgetVo> vos = new ExpenseBudgetDao().findByRequestId(requestId);
		return vos;
	}

	public boolean save(List<ExpenseBudgetVo> vos) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			ExpenseBudgetDao dao = new ExpenseBudgetDao();
			for (ExpenseBudgetVo vo : vos) {
				vo.setSeqNo(dao.getNextSeqNo());
				dao.save(vo, rst);
			}
			rst.commit();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			rst.rollback();
			return false;
		}
	}
}
