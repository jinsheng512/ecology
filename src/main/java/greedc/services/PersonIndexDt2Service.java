package greedc.services;

import java.util.ArrayList;
import java.util.List;

import greedc.dao.PersonIndexDt2Dao;
import greedc.dao.UFBAIndexDao;
import greedc.dao.UFIndexAssignDao;
import greedc.entities.PersonIndexDt2;
import greedc.entities.UFBAIndex;
import greedc.entities.UFIndexAssign;
import weaver.conn.RecordSetTrans;
import weaver.hrm.User;

public class PersonIndexDt2Service {

	/**
	 * 根据用户获取行为能力列表。<br/>
	 * 默认取部门级行为能力分配数据，如果找不到则取公司级行为能力分配数据。
	 * @param user
	 * @return
	 */
	public List<PersonIndexDt2> getList(User user) {
		UFIndexAssignDao assDao = new UFIndexAssignDao();
		UFIndexAssign assign = new UFIndexAssign();
		if (assDao.hasDeptAssign(user.getUserDepartment())) {
			assign = assDao.getDeptAssign(user.getUserDepartment());
		} else {
			assign = assDao.getCompanyAssign(user.getUserSubCompany1());
		}

		List<PersonIndexDt2> vos = new ArrayList<PersonIndexDt2>();
		if (assign != null) {
			vos.add(getPersonIndexDt2(assign.getIndex1()));
			vos.add(getPersonIndexDt2(assign.getIndex2()));
			vos.add(getPersonIndexDt2(assign.getIndex3()));
			vos.add(getPersonIndexDt2(assign.getIndex4()));
			vos.add(getPersonIndexDt2(assign.getIndex5()));
			vos.add(getPersonIndexDt2(assign.getIndex6()));
		}
		return vos;
	}
	
	/**
	 * 获取行为能力列表。
	 * @param mainId
	 * @return
	 */
	public List<PersonIndexDt2> getList(int mainId, User user) throws Exception {
		PersonIndexDt2Dao dao = new PersonIndexDt2Dao();
		List<PersonIndexDt2> list = dao.getList(mainId);
		if (list == null || list.isEmpty()) {
			List<PersonIndexDt2> baList = getList(user);
			int order = 1;
			for (PersonIndexDt2 entry : baList) {
				entry.setOrder(order++);
				entry.setMainId(mainId);
			}
			RecordSetTrans rst = new RecordSetTrans();
			boolean committed = false;
			try {
				rst.setAutoCommit(false);
				dao.save(baList, rst);
				committed = true;
			} finally {
				if (!committed) {
					rst.rollback();
				}
			}
		}
		list = dao.getList(mainId);
		return list;
	}

	/**
	 * 根据指标ID获取行为能力数据行。
	 * @param id
	 * @return
	 */
	public PersonIndexDt2 getPersonIndexDt2(int id) {
		UFBAIndexDao dao = new UFBAIndexDao();
		UFBAIndex index = dao.getById(id);
		
		PersonIndexDt2 vo = new PersonIndexDt2();
		if (index != null) {
			vo.setXingwnl(index.getName());
			vo.setShuom(getScoreDesc(index));
		}
		return vo;
	}
	
	/**
	 * 根据指标获取拼接5分说明。
	 * @param index
	 * @return
	 */
	public String getScoreDesc(UFBAIndex index) {
		String desc = "%s（5分）；%s（4分）；%s（3分）；%s（2分）；%s（1分）。";
		desc = String.format(desc, index.getScore5(),
				index.getScore4(), index.getScore3(), index.getScore2(),
				index.getScore1());
		return desc;
	}
}
