package greedc.ws.server.oa;

import java.sql.SQLException;

import weaver.conn.RecordSetTrans;
import weaver.workflow.webservices.WorkflowRequestTableField;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;
import ecustom.services.BigFieldService;
import ecustom.util.CustomUtil;
import ecustom.webservice.server.oa.CustomWorkflowRequestInfo;
import ecustom.webservice.server.oa.ICustomWorkflowServiceAction;
import greedc.dao.RequestBaseDao;
import greedc.entities.Form60;
import greedc.services.Form60Service;

/**
 * 生成“项目合同结算申请”流程Action。
 * @author William
 */
public class GreedcFlow261Action implements ICustomWorkflowServiceAction {

	private String biangxx;
	private String chengbxx;
	private String fuksqdxx;
	private String gongcfkxx;
	private String fukdxx;
	private String guanlhtxx;
	private String hetxx;
	private String koukxx;
	private String jianglxx;
	private String weiyxx;
	private HrmResource creator = null;
	private String dhFieldName = null;
	private String tableName = "FORMTABLE_MAIN_60";
	
	@Override
	public void doBefore(CustomWorkflowRequestInfo request) throws SQLException {
		int creatorId = Integer.parseInt(request.getCreatorId());
		this.creator = new HrmResourceDao().getById(creatorId);
		
		WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			if ("chengbxx".equalsIgnoreCase(field.getFieldName())) {
				this.chengbxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("fuksqdxx".equalsIgnoreCase(field.getFieldName())) {
				this.fuksqdxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("gongcfkxxb".equalsIgnoreCase(field.getFieldName())) {
				this.gongcfkxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("fukdxx".equalsIgnoreCase(field.getFieldName())) {
				this.fukdxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("guanlhtxx".equalsIgnoreCase(field.getFieldName())) {
				this.guanlhtxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("hetxx".equalsIgnoreCase(field.getFieldName())) {
				this.hetxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("biangxx".equalsIgnoreCase(field.getFieldName())) {
				this.biangxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("koukxx".equalsIgnoreCase(field.getFieldName())) {
				this.koukxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("jianlxx".equalsIgnoreCase(field.getFieldName())) {
				this.jianglxx = field.getFieldValue();
				field.setFieldValue("");
			} else if ("weiydxx".equalsIgnoreCase(field.getFieldName())) {
				this.weiyxx = field.getFieldValue();
				field.setFieldValue("");
			} /*else if ("fuj".equalsIgnoreCase(field.getFieldName())) {
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
			}*/ else if ("BIAODBH".equalsIgnoreCase(field.getFieldName())) {
				this.dhFieldName = field.getFieldValue();
			}
		}
	}

	@Override
	public void doAfter(String requestId) {
		Form60 form = new Form60();
		form.setRequestId(CustomUtil.getInt(requestId));
		
		BigFieldService bfService = new BigFieldService();
		form.setGongcfkxx(0);
		form.setBiangxx(0);
		form.setChengbxx(0);
		form.setFuksqxx(0);
		form.setFukxx(0);
		form.setGuanlxx(0);
		form.setHetxx(0);
		form.setKoukxx(0);
		form.setJianglxx(0);
		form.setWeiyxx(0);
		
		if (CustomUtil.isNotBlank(this.gongcfkxx)) {
			form.setGongcfkxx(bfService.save(this.gongcfkxx));
		}
		if (CustomUtil.isNotBlank(this.biangxx)) {
			form.setBiangxx(bfService.save(this.biangxx));
		}
		if (CustomUtil.isNotBlank(this.chengbxx)) {
			form.setChengbxx(bfService.save(this.chengbxx));
		}
		if (CustomUtil.isNotBlank(this.fuksqdxx)) {
			form.setFuksqxx(bfService.save(this.fuksqdxx));
		}
		if (CustomUtil.isNotBlank(this.fukdxx)) {
			form.setFukxx(bfService.save(this.fukdxx));
		}
		if (CustomUtil.isNotBlank(this.guanlhtxx)) {
			form.setGuanlxx(bfService.save(this.guanlhtxx));
		}
		if (CustomUtil.isNotBlank(this.koukxx)) {
			form.setKoukxx(bfService.save(this.koukxx));
		}
		if (CustomUtil.isNotBlank(this.jianglxx)) {
			form.setJianglxx(bfService.save(this.jianglxx));
		}
		if (CustomUtil.isNotBlank(this.weiyxx)) {
			form.setWeiyxx(bfService.save(this.weiyxx));
		}
		if (CustomUtil.isNotBlank(this.hetxx)) {
			form.setHetxx(bfService.save(this.hetxx));
		}
		
		Form60Service service = new Form60Service();
		service.updateForm(form);
		
		RequestBaseDao rbDao = new RequestBaseDao();
		RecordSetTrans rst = new RecordSetTrans();
		try {
			rst.setAutoCommit(false);

			String sql = "UPDATE FORMTABLE_MAIN_60 SET GONGH=? WHERE REQUESTID=?";
			rst.executeUpdate(sql, this.creator.getWorkCode(), requestId);
			
			rbDao.deleteByBillCode(this.tableName, "BIAODBH", this.dhFieldName, CustomUtil.getInt(requestId), rst);
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
		}
	}
}
