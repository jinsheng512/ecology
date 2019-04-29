package greedc.eas.services;

import greedc.eas.dao.EBProjectEntryDao;
import greedc.eas.vo.EBProjectEntryVo;

import java.util.List;

import weaver.conn.RecordSetTrans;

/**
 * 项目付款计划会签单-费用清单。
 * @author William
 */
public class EBProjectEntryService {

	public List<EBProjectEntryVo> findByMainSeqNo(int seqNo, int goux) {
		EBProjectEntryDao dao = new EBProjectEntryDao();
		List<EBProjectEntryVo> list = dao.findByMainSeqNo(seqNo, goux);
		return list;
	}

	public boolean save(List<EBProjectEntryVo> vos) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			EBProjectEntryDao dao = new EBProjectEntryDao();
			for (EBProjectEntryVo vo : vos) {
				vo.setSeqNo(dao.getNextSeqNo());
				dao.save(vo, rst);
			}
			rst.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			rst.rollback();
			return false;
		}
	}

	public boolean updateGoux(List<EBProjectEntryVo> vos) {
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			EBProjectEntryDao dao = new EBProjectEntryDao();
			for (EBProjectEntryVo vo : vos) {
				dao.updateGoux(vo, rst);
			}
			rst.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			rst.rollback();
			return false;
		}
	}

	/**
	 * 获取费用清单驳回数量。
	 * @param requestId
	 * @return
	 */
	public int getRejectCount(int requestId) {
		EBProjectEntryDao dao = new EBProjectEntryDao();
		return dao.getRejectCount(requestId);
	}
}
