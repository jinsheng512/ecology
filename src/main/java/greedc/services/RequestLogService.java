package greedc.services;

import ecustom.dao.WorkflowBaseDao;
import ecustom.entities.WorkflowBase;
import ecustom.util.CustomUtil;
import greedc.dao.FormTableMainDao;
import greedc.dao.RequestLogDao;
import greedc.vo.RequestLogVo;

import java.sql.SQLException;
import java.util.List;

import weaver.file.Prop;

public class RequestLogService {

	public List<RequestLogVo> listByBillCode(String requestId) throws ClassNotFoundException, SQLException {
		int reqId = CustomUtil.getInt(requestId);
		WorkflowBaseDao wbDao = new WorkflowBaseDao();
		WorkflowBase wb = wbDao.getByRequestId(reqId);

		String tableName = "FORMTABLE_MAIN_" + Math.abs(wb.getFormId());
		String fieldName = Prop.getPropValue("ecustom", "flow" + wb.getId() + ".form.eascode.field");
		FormTableMainDao mainDao = new FormTableMainDao();
		String fieldValue = mainDao.getBillCode(reqId, tableName, fieldName);
		
		RequestLogDao dao = new RequestLogDao();
		List<RequestLogVo> vos = dao.listByBillCode(fieldValue);
		processVOS(vos);
		return vos;
	}
	
	private void processVOS(List<RequestLogVo> vos) {
		for (RequestLogVo vo : vos) {
			if (vo.getOperator().intValue() == 1) {
				vo.setName("系统管理员");
				vo.setDeptName("");
			}
			
			if ("i".equals(vo.getOpTypeId())) {
				vo.setOpTypeName("流程干预");
			} else if ("0".equals(vo.getOpTypeId())) {
				vo.setOpTypeName("批准");
			} else if ("1".equals(vo.getOpTypeId())) {
				vo.setOpTypeName("已查看");
			} else if ("2".equals(vo.getOpTypeId())) {
				vo.setOpTypeName("提交");
			}else if ("3".equals(vo.getOpTypeId())) {
				vo.setOpTypeName("退回");
			}
			
			if (vo.getReNames() != null && vo.getReNames().endsWith(",")) {
				vo.setReNames(vo.getReNames().substring(0, vo.getReNames().length() - 1));
			}
			
			if (vo.getLogContent() == null) {
				vo.setLogContent("");
			}
		}
	}
}
