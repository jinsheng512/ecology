package greedc.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecustom.ecology8.commons.RecordUtil;
import ecustom.ecology8.workflow.entities.WorkflowBillField;
import ecustom.servlets.BaseServlet;

/**
 * 表单字段请求处理类。
 * @author William
 */
public class WFFieldServlet extends BaseServlet {

	public Map<String, String> getFieldMap() {
		Integer formId = getParameterIntegerCK("formId");
		List<WorkflowBillField> fields = RecordUtil.search(WorkflowBillField.class, "BILLID=?", formId);
		Map<String, String> fieldMap = new HashMap<String, String>();
		for (WorkflowBillField field : fields) {
			String dtName = "";
			if (field.getDetailTable() != null && !field.getDetailTable().trim().isEmpty()) {
				dtName = field.getDetailTable().substring(field.getDetailTable().lastIndexOf("_") + 1).toLowerCase();
			}
			fieldMap.put(dtName + field.getFieldName().toLowerCase(), "field" + field.getId());
		}
		return fieldMap;
	}
}
