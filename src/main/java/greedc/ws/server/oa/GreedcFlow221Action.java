package greedc.ws.server.oa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.general.BaseBean;
import weaver.workflow.webservices.WorkflowDetailTableInfo;
import weaver.workflow.webservices.WorkflowRequestTableField;
import weaver.workflow.webservices.WorkflowRequestTableRecord;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import ecustom.Log;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;
import ecustom.util.CustomUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.dao.RequestBaseDao;
import greedc.eas.services.EBProjectEntryService;
import greedc.eas.services.ExpenseBudgetService;
import greedc.eas.vo.EBProjectEntryVo;
import greedc.eas.vo.ExpenseBudgetVo;

/**
 * 生成项目付款计划会签单流程Action。
 * @author William
 */
public class GreedcFlow221Action implements ICustomWorkflowServiceAction {
	
	private static final Log log = new Log(GreedcFlow221Action.class);
	private List<ExpenseBudgetVo> details = null;
	private HrmResource creator = null;
	private String dhFieldName = null;
	private String tableName = "FORMTABLE_MAIN_50";
	private static BaseBean baseBean=new BaseBean();

	@Override
	public void doAfter(String requestId) {
		baseBean.writeLog("tagtag run action7 :"+ requestId);
		if (this.details != null && !details.isEmpty()) {
			Gson gson = new Gson();
			for (ExpenseBudgetVo vo : details) {
				baseBean.writeLog("tagtag run action8 :"+ vo.toString());
				Map<String, Object> detailMap = gson.fromJson(vo.getDetailJson(), Map.class);
				vo.setProjectName((String)detailMap.get("xiangmm"));
				vo.setStatisticalDept((String)detailMap.get("huizbm"));
				vo.setYear((String)detailMap.get("year"));
				vo.setMonth((String)detailMap.get("month"));
				vo.setRequestId(CustomUtil.getInt(requestId));
			}
			ExpenseBudgetService service = new ExpenseBudgetService();
			if (!service.save(this.details)) {
				log.error("List<ExpenseBudgetVo> details 保存失败！");
				return ;
			}
			
			List<EBProjectEntryVo> entryList = new ArrayList<EBProjectEntryVo>();
			for (ExpenseBudgetVo vo : details) {
				Map<String, Object> detailMap = gson.fromJson(vo.getDetailJson(), Map.class);
				List<LinkedTreeMap> entries = (List<LinkedTreeMap>)detailMap.get("entry");
				for (LinkedTreeMap dtRow : entries) {
					EBProjectEntryVo entry = new EBProjectEntryVo();
					entry.setMainSeqNo(vo.getSeqNo());
					entry.setBianmxh(CustomUtil.getString(dtRow.get("danh")));
					entry.setGoux(getGouxInt("" + dtRow.get("goux")));
					entry.setGongcxm((String)dtRow.get("gongcxm"));
					entry.setHetbm(CustomUtil.getString(dtRow.get("hetbm")));
					entry.setHetmc(CustomUtil.getString(dtRow.get("hetmc")));
					entry.setHetje(CustomUtil.getDouble("" + dtRow.get("hetje"), null));
					entry.setTiaozhje(CustomUtil.getDouble("" + dtRow.get("tiaozhje"), null));
					entry.setXingxjdms(CustomUtil.getString(dtRow.get("xingxjdms")));
					entry.setYuecljje(CustomUtil.getDouble("" + dtRow.get("yuecljje"), null));
					entry.setYuecljbl(CustomUtil.getString(dtRow.get("yuecljbl")));
					entry.setBenywcjd(CustomUtil.getString(dtRow.get("benywcjd")));
					entry.setBenyjdfk(CustomUtil.getString(dtRow.get("benyjdfk")));
					entry.setJe(CustomUtil.getDouble("" + dtRow.get("je"), null));
					entry.setYifkbl(CustomUtil.getString(dtRow.get("yifkbl")));
					entry.setBenyjhfke(CustomUtil.getDouble("" + dtRow.get("benyjhfke"), null));
					entry.setLeijbl(CustomUtil.getString(dtRow.get("leijbl")));
					entry.setKuanxlb(CustomUtil.getString(dtRow.get("kuanxlb")));
					entry.setBeiz(CustomUtil.getString(dtRow.get("beiz")));
					entry.setShenpryj(CustomUtil.getString(dtRow.get("shenpryj")));
					entry.setShoukdwqc(CustomUtil.getString(dtRow.get("shoukdwqc")));
					entry.setShijskdw(CustomUtil.getString(dtRow.get("shijskdw")));
					entryList.add(entry);
				}
			}
			EBProjectEntryService peService = new EBProjectEntryService();
			if (!peService.save(entryList)) {
				log.error("List<EBProjectEntryVo> entryList 保存失败！");
			}
		}
		
		RecordSet rs = new RecordSet();
		String sql = "UPDATE FORMTABLE_MAIN_50 SET GONGH=? WHERE REQUESTID=?";
		rs.executeUpdate(sql, this.creator.getWorkCode(), requestId);
		
		RequestBaseDao rbDao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);
			rbDao.deleteByBillCode(this.tableName, "DANH", this.dhFieldName, CustomUtil.getInt(requestId), rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
		}
	}

	@Override
	public void doBefore(CustomWorkflowRequestInfo request) throws SQLException {
		baseBean.writeLog("tagtag run action1 :"+ request);
		int creatorId = Integer.parseInt(request.getCreatorId());
		this.creator = new HrmResourceDao().getById(creatorId);
		
		WorkflowRequestTableField[] mainFields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : mainFields) {
			baseBean.writeLog("tagtag run action2 :"+ field);
			/*if ("fuj".equalsIgnoreCase(field.getFieldName())) {
				EASFileService service = new EASFileService();
				List<EASFileVo> vos = GsonUtil.toObjectList(field.getFieldValue(), EASFileVo.class);
				if (vos.isEmpty()) {
					field.setFieldValue("");
				} else {
					try {
						field.setFieldValue("");
						field.setFieldValue(service.saveBase64Files(vos, creatorId, workflowId));
					} catch (IOException e) {
						throw new RuntimeException("附件处理失败");
					}
				}
			} else*/ if ("DANH".equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldName = field.getFieldValue();
				baseBean.writeLog("tagtag run action3 :"+ dhFieldName);
			}
		}
		
		WorkflowDetailTableInfo detailTable = request.getWorkflowDetailTableInfos()[0];
		WorkflowRequestTableRecord[] records = detailTable.getWorkflowRequestTableRecords();
		ExpenseBudgetVo vo = null;
		details = new ArrayList<ExpenseBudgetVo>();
		for (WorkflowRequestTableRecord record : records) {
			baseBean.writeLog("tagtag run action4 :"+ records);
			WorkflowRequestTableField[] fields = record.getWorkflowRequestTableFields();
			vo = new ExpenseBudgetVo();
			if (fields.length > 0) {
				for (WorkflowRequestTableField field : fields) {
					baseBean.writeLog("tagtag run action5 :"+ field.getFieldName());
					if ("benyyfhj".equalsIgnoreCase(field.getFieldName())) {
						vo.setAccPayable(CustomUtil.getDouble(field.getFieldValue(), null));
					} else if ("hetsl".equalsIgnoreCase(field.getFieldName())) {
						vo.setConNum(CustomUtil.getInt(field.getFieldValue(), null));
					} else if ("fukdw".equalsIgnoreCase(field.getFieldName())) {
						vo.setPayCo(field.getFieldValue());
					} else if ("shangyfkhj".equalsIgnoreCase(field.getFieldName())) {
						vo.setPayTotal(CustomUtil.getDouble(field.getFieldValue(), null));
					} else if ("xiangmmc".equalsIgnoreCase(field.getFieldName())) {
						vo.setProjectName(field.getFieldValue());
					} else if ("benyfkbl".equalsIgnoreCase(field.getFieldName())) {
						vo.setProportion(field.getFieldValue());
					} else if ("fukmxb".equalsIgnoreCase(field.getFieldName())) {
						vo.setDetailJson(field.getFieldValue());
					} else if ("projectId".equalsIgnoreCase(field.getFieldName())) {
						vo.setProjectId(field.getFieldValue());
					}
				}
				details.add(vo);
			}
		}
		baseBean.writeLog("tagtag run action6 :"+ details);
		request.setWorkflowDetailTableInfos(null);	// 清空明细表
	}
	
	public void setDetails(List<ExpenseBudgetVo> details) {
		this.details = details;
	}

	private int getGouxInt(String goux) {
		int i = 0;
		if ("复核".equals(goux)) {
			i = 1;
		} else if ("修改".equals(goux)) {
			i = 2;
		} else if ("执行".equals(goux)) {
			i = 3;
		}
		return i;
	}
}
