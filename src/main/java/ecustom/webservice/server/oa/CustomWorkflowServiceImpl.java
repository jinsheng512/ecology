package ecustom.webservice.server.oa;

import java.util.Date;
import java.util.List;

import com.greenpineyu.fel.parser.FelParser.integerLiteral_return;

import weaver.file.Prop;
import weaver.general.BaseBean;
import weaver.workflow.webservices.WorkflowDetailTableInfo;
import weaver.workflow.webservices.WorkflowMainTableInfo;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowRequestTableField;
import weaver.workflow.webservices.WorkflowRequestTableRecord;
import weaver.workflow.webservices.WorkflowServiceImpl;
import ecustom.Log;
import ecustom.dao.WorkflowBaseDao;
import ecustom.entities.EcstWorkflowBase;
import ecustom.entities.HrmResource;
import ecustom.services.HrmResourceService;
import ecustom.util.CustomUtil;

/**
 * 自定义流程服务WebService实现接口。
 * @author William
 */
public class CustomWorkflowServiceImpl extends WorkflowServiceImpl implements IWorkflowService {

	private Log log = new Log(CustomWorkflowServiceImpl.class);

	public final static String REPLACE_CODE = "code:";
	
	private HrmResourceService resService = new HrmResourceService();
	private FieldConvert fieldConvert = null;

	private HrmResource creator = null;	// 创建人
	
    private static BaseBean baseBean=new BaseBean();

	@Override
	public String doCreateWorkflowRequest(CustomWorkflowRequestInfo request, int arg1) {
		log.debug("doCreateWorkflowRequest()");
		String actionClassName = Prop.getPropValue("ecustom", "wf.ws.flow" + request.getWorkflowBaseInfo().getWorkflowId() + ".action");
		ICustomWorkflowServiceAction action = null;
		String requestId = null;

		
		// 转换创建人ID
		String creatorCode = request.getCreatorId();
		this.creator = resService.getByWorkCode(creatorCode);
		log.debug("createId=" + creator.getId());

		// 转换申请人Id
		String applicantCode = request.getApplicantCode();
		HrmResource applicant = null;
		if (creatorCode.equals(applicantCode) ||CustomUtil.isBlank(request.getApplicantCode())) {
			applicant = this.creator;
		} else if (CustomUtil.isNotBlank(request.getApplicantCode())) {
			applicantCode = request.getApplicantCode();
			applicant = resService.getByWorkCode(applicantCode);
			this.creator = applicant;
		}
		request.setCreatorId(this.creator.getId().toString());
		fieldConvert = new FieldConvert(this.creator);

		CustomUtil.checkNull(this.creator, "创建人工号找不到 " + creatorCode);

		try {
			if (CustomUtil.isNotBlank(actionClassName)) {
				action = (ICustomWorkflowServiceAction)Class.forName(actionClassName).newInstance();
				action.doBefore(request);	// 生成流程前执行操作
			}
			requestId = super.doCreateWorkflowRequest(convertRequestInfo(request), this.creator.getId());
			log.debug("requestId: " + requestId);
			checkRequestId(requestId);
			if (action != null) {
				action.doAfter(requestId);	// 生成流程后执行操作
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return requestId;
	}

	/**
	 * 将请求参数组织信息中的编码转换成ID。
	 * @param request
	 * @return
	 */
	private WorkflowRequestInfo convertRequestInfo(CustomWorkflowRequestInfo request) {

		baseBean.writeLog("tagtag run action0 :"+ request.toString()+request );
		baseBean.writeLog("tagtag run action1 :"+",MainTableInfo="+request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields()[0].getFieldName());
		baseBean.writeLog("tagtag run action1 :"+",长度"+request.getWorkflowMainTableInfo().getRequestRecords().length);
		baseBean.writeLog("tagtag run action2 :"+ request.getWorkflowBaseInfo().getWorkflowId());
		
		baseBean.writeLog("*************************打印明细表明细************************");
		//打印明细表明细
//		for(int i=0;i<request.getWorkflowDetailTableInfos().length;i++){
			
//			for(int j=0;j<request.getWorkflowDetailTableInfos()[i].getWorkflowRequestTableRecords().length;j++){
//				
//				for(int k=0;k<request.getWorkflowDetailTableInfos()[i].getWorkflowRequestTableRecords()[j].getWorkflowRequestTableFields().length;k++){
//					
//					baseBean.writeLog("tagtag run 明细表name :"+request.getWorkflowDetailTableInfos()[i].getWorkflowRequestTableRecords()[j].getWorkflowRequestTableFields()[k].getFieldName());
//					baseBean.writeLog("tagtag run 明细表value :"+request.getWorkflowDetailTableInfos()[i].getWorkflowRequestTableRecords()[j].getWorkflowRequestTableFields()[k].getFieldValue());
//					baseBean.writeLog("tagtag run 明细表id :"+request.getWorkflowDetailTableInfos()[i].getWorkflowRequestTableRecords()[j].getWorkflowRequestTableFields()[k].getFieldId());
//				}
//			}
//		}
		baseBean.writeLog("*************************打印主表明细************************");
		//打印主表明细
	for(int i=0;i<request.getWorkflowMainTableInfo().getRequestRecords().length;i++){
			
			for(int j=0;j<request.getWorkflowMainTableInfo().getRequestRecords()[i].getWorkflowRequestTableFields().length;j++){
				
				
					
					baseBean.writeLog("tagtag run 主表name :"+request.getWorkflowMainTableInfo().getRequestRecords()[i].getWorkflowRequestTableFields()[j].getFieldName());
					baseBean.writeLog("tagtag run 主表value :"+request.getWorkflowMainTableInfo().getRequestRecords()[i].getWorkflowRequestTableFields()[j].getFieldValue());
					baseBean.writeLog("tagtag run 主表id :"+request.getWorkflowMainTableInfo().getRequestRecords()[i].getWorkflowRequestTableFields()[j].getFieldId());
				
			}
		}
		
		
		CustomUtil.checkNull(request, "WorkflowRequestInfo 输入参数不能为空");
		CustomUtil.checkStringEmpty(request.getCreatorId(), "CreatorId 创建人不能为空");


		CustomUtil.checkNull(request.getWorkflowMainTableInfo(), "WorkflowMainTableInfo 主表数据不能为空");
		CustomUtil.checkNull(request.getWorkflowBaseInfo(), "WorkflowBaseInfo 流程基本信息不能为空");
		CustomUtil.checkStringEmpty(request.getWorkflowBaseInfo().getWorkflowId(), "WorkflowId 流程ID不能为空");
		CustomUtil.checkStringEmpty(request.getRequestLevel(), "RequestLevel 请求级别不能为空");
		
		// 如果没有设置创建日期，则提供默认日期，yyyy-MM-dd
		if (CustomUtil.isBlank(request.getCreateTime())) {
			request.setCreateTime(CustomUtil.dateFormat(new Date(), "yyyy-MM-dd"));
		}

		// 转换主表中的组织相关ID
		convertMainTableInfo(request.getWorkflowMainTableInfo());

		// 转换明细表组织相关ID
		convertDetailTableInfos(request.getWorkflowDetailTableInfos());
		
		// 判断WorkflowId是否需要转换，如果需要则做相应转换
		String flowId = request.getWorkflowBaseInfo().getWorkflowId();
		int iFlowId = 0;
		if (flowId.startsWith(REPLACE_CODE)) {
			String flowCode = flowId.substring(REPLACE_CODE.length());
			WorkflowBaseDao dao = new WorkflowBaseDao();
			EcstWorkflowBase v = dao.getECSTFlowBaseByCode(flowCode);
			if (v != null) {
				iFlowId = v.getId();
			}
			request.getWorkflowBaseInfo().setWorkflowId("" + iFlowId);
		} else {
			iFlowId = CustomUtil.getInteger(flowId);
		}
		
		// 如果没有设置标题，则提供默认标题
		if (CustomUtil.isBlank(request.getRequestName())) {
			WorkflowBaseDao dao = new WorkflowBaseDao();
			EcstWorkflowBase base = dao.getECSTFlowBaseById(iFlowId);
			request.setRequestName(getDefaultRequestName(request, base));
		}

		log.debug("convertRequestInfo() done.");
		return request;
	}

	private String getDefaultRequestName(CustomWorkflowRequestInfo request, EcstWorkflowBase v) {
		List<String> ds = CustomUtil.getDeclares(v.getRequestName());
		String requestName = v.getRequestName();
		if (!ds.isEmpty()) {
			if (ds.contains("WORKFLOW_NAME")) {
				requestName = requestName.replace("${WORKFLOW_NAME}", v.getWorkflowName());
			}
			if (ds.contains("CREATOR_NAME")) {
				requestName = requestName.replace("${CREATOR_NAME}", this.creator.getLastName());
			}
			if (ds.contains("CREATE_DATE")) {
				requestName = requestName.replace("${CREATE_DATE}", request.getCreateTime());
			}
			WorkflowRequestTableField[] fields = request.getWorkflowMainTableInfo().getRequestRecords()[0].getWorkflowRequestTableFields();
			for (WorkflowRequestTableField field : fields) {
				if (ds.contains(field.getFieldName())) {
					requestName = requestName.replace("${" + field.getFieldName() + "}", field.getFieldValue());
				}
			}
		} else if (CustomUtil.isBlank(v.getRequestName())) {
			requestName = String.format("%s-%s-%s", v.getWorkflowName(), this.creator.getLastName(), request.getCreateTime());
		}
		return requestName;
	}
	
	/**
	 * 转换主表中的组织相关ID
	 * @param mainTableInfo
	 */
	private void convertMainTableInfo(WorkflowMainTableInfo mainTableInfo) {
		WorkflowRequestTableRecord[] records = mainTableInfo.getRequestRecords();
		CustomUtil.checkArrayEmpty(records, "WorkflowRequestTableRecord 主表数据不能为空");
		for (WorkflowRequestTableRecord record : records) {
			convertRecord(record);
		}
	}

	/**
	 * 转换明细表中的组织相关ID
	 * @param mainTableInfo
	 */
	private void convertDetailTableInfos(WorkflowDetailTableInfo[] detailTableInfos) {
		if (detailTableInfos != null) {
			for (WorkflowDetailTableInfo detailTableInfo : detailTableInfos) {
				convertDetailTableInfo(detailTableInfo);
			}
		}
	}

	/**
	 * 转换明细表中的组织相关ID
	 * @param mainTableInfo
	 */
	private void convertDetailTableInfo(WorkflowDetailTableInfo detailTableInfo) {
		WorkflowRequestTableRecord[] records = detailTableInfo.getWorkflowRequestTableRecords();
		if (records == null) {
			return ;
		}
		for (WorkflowRequestTableRecord record : records) {
			convertRecord(record);
		}
	}

	/**
	 * 转换记录中组织结构相关ID。
	 * @param records
	 */
	private void convertRecord(WorkflowRequestTableRecord record) {
		WorkflowRequestTableField[] fields = record.getWorkflowRequestTableFields();
		for (WorkflowRequestTableField field : fields) {
			fieldConvert.convertField(field);
			field.setView(true);
			field.setEdit(true);
		}
	}

	private void checkRequestId(String requestId) {
		int id = CustomUtil.getInteger(requestId);
		if (id == -1) {
			throw new RuntimeException("创建流程失败");
		} else if (id == -2) {
			throw new RuntimeException("用户没有流程创建权限");
		} else if (id == -3) {
			throw new RuntimeException("创建流程基本信息失败");
		} else if (id == -4) {
			throw new RuntimeException("保存表单主表信息失败");
		} else if (id == -5) {
			throw new RuntimeException("更新紧急程度失败");
		} else if (id == -6) {
			throw new RuntimeException("流程操作者失败");
		} else if (id == -7) {
			throw new RuntimeException("流转至下一节点失败");
		} else if (id == -8) {
			throw new RuntimeException("节点附加操作失败");
		}
	}
}
