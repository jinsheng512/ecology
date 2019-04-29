package greedc.services;

import ecustom.dao.HrmResourceDao;
import ecustom.dao.WorkflowSelectItemDao;
import ecustom.entities.HrmResource;
import ecustom.util.CustomUtil;
import greedc.dao.Form40Dao;
import greedc.dao.PersonIndexDao;
import greedc.dao.PersonIndexDt1Dao;
import greedc.dao.PersonIndexDt2Dao;
import greedc.entities.PersonIndex;
import greedc.entities.PersonIndexDt1;
import greedc.entities.PersonIndexDt2;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.file.Prop;
import weaver.hrm.User;
import weaver.workflow.webservices.*;

/**
 * 个人绩效考核业务处理类。
 * @author William
 */
public class PersonIndexService {

	private static Logger log = Logger.getLogger(PersonIndexService.class);

	public PersonIndex getNew(User user) {
		PersonIndex index = new PersonIndex();
		index.setBeikhr(user.getUID());
		index.setBeikhrName(user.getLastname());
		
		HrmResource bumfzr = getBumfzr(user.getUserDepartment());
		index.setBumfzr(bumfzr.getId());
		index.setBumfzrName(bumfzr.getLastName());
		return index;
	}

	/**
	 * 获取部门负责人（从评分人矩阵中获取）。
	 * @param deptId
	 * @return
	 */
	public HrmResource getBumfzr(int deptId) {
		MatrixService matrixService = new MatrixService();
		int pingfr = matrixService.getPingfrByDept(deptId);
		HrmResourceDao dao = new HrmResourceDao();
		return dao.getById(pingfr);
	}

	/**
	 * 新建个人绩效评分。
	 * @throws Exception 
	 */
	public void createPersonIndex(User user, int year, int season) throws Exception {
		PersonIndexDao dao = new PersonIndexDao();
		boolean exist = dao.exists(user.getUID(), year, season);
		if (!exist) {
			// 获取与用户相关的行为能力指标
			List<PersonIndexDt2> baList = new PersonIndexDt2Service().getList(user);
			RecordSetTrans rst = new RecordSetTrans();
			boolean committed = false;
			try {
				rst.setAutoCommit(committed);
				PersonIndex index = newPersonIndex(user, year, season);
				savePersonIndex(index, rst);
				savePersonIndexDt2(baList, index.getId(), rst);
				rst.commit();
				committed = true;
			} finally {
				if (!committed) {
					rst.rollback();
				}
			}
			
		}
	}
	
	public PersonIndex newPersonIndex(User user, int year, int season) {
		PersonIndex index = new PersonIndex();
		// 被考核人
		index.setBeikhr(user.getUID());
		index.setBeikhrName(user.getLastname());
		// 考核人
		HrmResource bumfzr = getBumfzr(user.getUserDepartment());
		if (bumfzr == null) {
			throw new RuntimeException("获取不到部门负责人");
		}
		index.setBumfzr(bumfzr.getId());
		index.setBumfzrName(bumfzr.getLastName());
		index.setYear(year);
		index.setSeason(season);	// 考核季度
		index.setStatus(0);	// 单据状态-暂存
		index.setChengj(0.0);
		index.setYwzbzhj(0.0);
		index.setXwnlzhj(0.0);
		index.setYwzbshj(0.0);
		index.setXwnlshj(0.0);
		return index;
	}
	
	public void savePersonIndex(PersonIndex index, RecordSetTrans rst) throws Exception {
		PersonIndexDao dao = new PersonIndexDao();
		dao.save(index, rst);
	}
	
	public void savePersonIndexDt2(List<PersonIndexDt2> baList, int mainId, RecordSetTrans rst) throws Exception {
		PersonIndexDt2Dao dao = new PersonIndexDt2Dao();
		int order = 1;
		for (PersonIndexDt2 entry : baList) {
			entry.setOrder(order++);
			entry.setMainId(mainId);
		}
		dao.save(baList, rst);
	}
	
	public boolean exists(int id, int year, int season) {
		PersonIndexDao dao = new PersonIndexDao();
		return dao.exists(id, year, season);
	}

	public PersonIndex getPersonIndex(int userId, int year, int season) {
		PersonIndexDao dao = new PersonIndexDao();
		PersonIndex index = dao.getPersonIndex(userId, year, season);
		if (index != null) {
			HrmResourceDao resDao = new HrmResourceDao();
			index.setBeikhrName(resDao.getById(index.getBeikhr()).getLastName());
			index.setBumfzrName(resDao.getById(index.getBumfzr()).getLastName());
		}
		return index;
	}

	/**
	 * 保存自评分
	 * @param index
	 * @param imList
	 * @param baList
	 * @param deleteIds 
	 * @throws Exception 
	 */
	public void saveSelfMark(PersonIndex index, List<PersonIndexDt1> imList,
			List<PersonIndexDt2> baList, List<Integer> deleteIds) throws Exception {
		RecordSetTrans rst = new RecordSetTrans();
		boolean committed = false;
		try {
			rst.setAutoCommit(false);
			PersonIndexDao indexDao = new PersonIndexDao();
			indexDao.updateSelfMark(index, rst);
			saveIMSelfMark(index, imList, deleteIds, rst);
			updateBASelfMark(baList, rst);
			rst.commit();
			committed = true;
		} finally {
			if (!committed) {
				rst.rollback();
			}
		}
	}
	
	public void saveIMSelfMark(PersonIndex index, List<PersonIndexDt1> imList,
			List<Integer> deleteIds, RecordSetTrans rst) throws Exception {
		PersonIndexDt1Dao imDao = new PersonIndexDt1Dao();
		for (PersonIndexDt1 im : imList) {
			if (im.getId() < 0) {
				im.setMainId(index.getId());
				imDao.saveSelfMark(im, rst);
			} else {
				imDao.updateSelfMark(im, rst);
			}
		}
		imDao.delete(deleteIds, rst);
	}
	
	public void updateBASelfMark(List<PersonIndexDt2> baList, RecordSetTrans rst) throws Exception {
		PersonIndexDt2Dao baDao = new PersonIndexDt2Dao();
		baDao.updateSelfMark(baList, rst);
	}

	public void updateStatus(int id, int status) throws SQLException {
		PersonIndexDao dao = new PersonIndexDao();
		dao.setStatus(id, status);
	}

	public void saveManagerMark(PersonIndex index, List<PersonIndexDt1> imList,
			List<PersonIndexDt2> baList) throws Exception {
		RecordSetTrans rst = new RecordSetTrans();
		PersonIndexDao indexDao = new PersonIndexDao();
		UFIndexRoleDt1Service roleService = new UFIndexRoleDt1Service();
		boolean committed = false;
		try {
			rst.setAutoCommit(false);
			index.setYoulpd(roleService.calYoulpd(index.getChengj()));
			indexDao.updateManagerMark(index, rst);
			updateIMManagerMark(imList, rst);
			updateBAManagerMark(baList, rst);
			rst.commit();
			committed = true;
		} finally {
			if (!committed) {
				rst.rollback();
			}
		}
	}

	private void updateBAManagerMark(List<PersonIndexDt2> baList,
			RecordSetTrans rst) throws Exception {
		PersonIndexDt2Dao baDao = new PersonIndexDt2Dao();
		for (PersonIndexDt2 ba : baList) {
			baDao.updateManagerMark(ba, rst);
		}
	}

	private void updateIMManagerMark(List<PersonIndexDt1> imList,
			RecordSetTrans rst) throws Exception {
		PersonIndexDt1Dao imDao = new PersonIndexDt1Dao();
		for (PersonIndexDt1 im : imList) {
			imDao.updateManagerMark(im, rst);
		}
	}

	public List<PersonIndex> listBumfzrAppr(int uid, int year, int season) {
		PersonIndexDao dao = new PersonIndexDao();
		List<PersonIndex> list = dao.listBumfzrAppr(uid, year, season);
		return list;
	}

	public List<PersonIndex> listDeptAppr(int deptId, int year, int season) {
		log.error("进来1" + deptId + year + season);
		PersonIndexDao dao = new PersonIndexDao();
		List<PersonIndex> list = dao.listDeptAppr(deptId, year, season);
		log.error("进来2" + list.size());
		return list;
	}

	/**
	 * 提交个人绩效。<br/>
	 * 如果部门负责人还没有创建绩效流程，则自动创建绩效流程。 
	 * @param id
	 * @param userId
	 * @param year
	 * @param season
	 * @throws SQLException
	 */
	public void submintSelfMark(int id, int userId, int year, int season) throws SQLException {
		updateStatus(id, 1);
		Form40Dao dao = new Form40Dao();
		HrmResourceDao resDao = new HrmResourceDao();
		HrmResource res = resDao.getById(userId);
		
		// 如果部门评分流程不存在，则新建季度评分流程
		if (!dao.checkExists(res.getDepartmentId(), year, season)) {
			createFlow(res.getDepartmentId(), year, season);
		}
	}
	
	/**
	 * 创建季度评分流程。
	 * @param deptId
	 * @param year
	 * @param season
	 */
	public void createFlow(int deptId, int year, int season) {
		// 获取部门负责人
		HrmResource bumfzr = getBumfzr(deptId);
		if (bumfzr == null || bumfzr.getId() <= 0) {
			log.error("找不到部门负责人，部门ID：" + deptId);
			return ;
		}

		// 获取年份的选项值
		WorkflowSelectItemDao selectDao = new WorkflowSelectItemDao();
		int yearValue = selectDao.getSelectValue(Form40Dao.YEAR_ID, year + "");

		WorkflowServiceImpl service = new WorkflowServiceImpl();
		WorkflowRequestInfo request = new WorkflowRequestInfo();

		// ********* 不能使用此方法，生成流程后不能停留到创建节点 **********
		request.setCreateTime(CustomUtil.dateFormat(new Date(), "yyyy-MM-dd"));
		request.setCreatorId(bumfzr.getId() + "");
		request.setRemark("系统自动创建");
		request.setRequestLevel("0");
		request.setRequestName("部门评分流程-" + bumfzr.getLastName() + "-" + request.getCreateTime());

		WorkflowBaseInfo baseInfo = new WorkflowBaseInfo();
		baseInfo.setWorkflowId("144");
		request.setWorkflowBaseInfo(baseInfo);

		WorkflowMainTableInfo mainTable = new WorkflowMainTableInfo();
		WorkflowRequestTableRecord record = new WorkflowRequestTableRecord();
		WorkflowRequestTableField[] fields = new WorkflowRequestTableField[] {
				getFields("stay", "1"),	// 是否停留，0-否，1-是
				getFields("bummc", deptId + ""),
				getFields("gongsm", bumfzr.getSubCompanyId() + ""),
				getFields("year", yearValue + ""),
				getFields("kaojd", season + "")
		};
		record.setWorkflowRequestTableFields(fields);
		WorkflowRequestTableRecord[] records = new WorkflowRequestTableRecord[]{record};
		mainTable.setRequestRecords(records);
		request.setWorkflowMainTableInfo(mainTable);
		
		String requestId = service.doCreateWorkflowRequest(request, bumfzr.getId());
		log.info("创建评分流程成功，RequestId = " + requestId);
	}

	/**
	 * 构建字段。
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	private WorkflowRequestTableField getFields(String fieldName, String fieldValue) {
		WorkflowRequestTableField field = new WorkflowRequestTableField();
		field.setEdit(true);
		field.setView(true);
		field.setFieldName(fieldName);
		field.setFieldValue(fieldValue);
		return field;
	}

	/**
	 * 部门负责人驳回。
	 * @param id
	 * @param year
	 * @param season
	 * @param userId	被考核人ID
	 * @throws Exception 
	 */
	public void reject(int id, int year, int season, int userId) throws Exception {
		PersonIndexDao dao = new PersonIndexDao();
		dao.setStatus(id, 0);	// 更新个人绩效状态为：草稿状态
		dao.setClearUF_PERSONINDEX_DT1(id);
		dao.setClearUF_PERSONINDEX_DT2(id);
		
		log.error("id" + id);
		
		RecordSet rs = new RecordSet();
		StringBuilder sql = new StringBuilder(" SELECT T2.ID FROM FORMTABLE_MAIN_40 T1");
		sql.append(" INNER JOIN FORMTABLE_MAIN_40_DT1 T2 ON T2.MAINID=T1.ID");
		sql.append(" INNER JOIN WORKFLOW_SELECTITEM T3 ON FIELDID=9708 AND T3.SELECTVALUE=T1.YEAR");
		sql.append(" WHERE T1.KAOJD=? AND T2.XINGM=? AND T3.SELECTNAME=?");
		int mxId = 0;
		if (rs.executeQuery(sql.toString(), season, userId, "" + year) && rs.next()) {
			mxId = rs.getInt(1);
		}
		if (mxId <= 0) {
			return;
//			throw new Exception(String.format("查找将评分表单明细表记录失败！id=%s, year=%s, season=%s, userId=%s",
//					id, year, season, userId));
		}
		
		sql = new StringBuilder("UPDATE FORMTABLE_MAIN_40_DT1 SET MAINID=-1 WHERE ID=?");
		if (!rs.executeUpdate(sql.toString(), mxId)) {
			throw new Exception("将评分表单明细表记录设置为无效，执行失败！");
		}
		
		sql = new StringBuilder("UPDATE FORMTABLE_MAIN_40_DT1 SET MAINID=-1 WHERE ID=?");
		if (!rs.executeUpdate(sql.toString(), mxId)) {
			throw new Exception("将评分表单明细表记录设置为无效，执行失败！");
		}
	}
}