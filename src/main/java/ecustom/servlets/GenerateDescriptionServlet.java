package ecustom.servlets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ecustom.easyui.vo.PropertyGridVo;
import ecustom.exception.DataNotFoundException;
import ecustom.exception.ParameterNotFoundException;
import ecustom.services.GenerateDescriptionService;
import ecustom.util.CustomUtil;
import ecustom.vo.GenerateDescriptionBaseVo;
import ecustom.vo.GenerateDescriptionFieldVo;

public class GenerateDescriptionServlet extends BaseServlet {

	public Object getFields() {
		int workflowId = CustomUtil.getInt(request().getParameter("workflowId"), 0);
		if (workflowId == 0) {
			throw new ParameterNotFoundException("请提供请求参数 - workflowId");
		}
		GenerateDescriptionService service = new GenerateDescriptionService();
		List<GenerateDescriptionFieldVo> list = service.getFields(workflowId);
		Collections.sort(list);
		return list;
	}

	public Object getBase() {
		int workflowId = CustomUtil.getInt(request().getParameter("workflowId"), 0);
		if (workflowId == 0) {
			throw new ParameterNotFoundException("请提供请求参数 - workflowId");
		}
		GenerateDescriptionService service = new GenerateDescriptionService();
		GenerateDescriptionBaseVo vo = service.getBase(workflowId);
		if (vo == null) {
			throw new DataNotFoundException("workflowId[" + workflowId + "] - 不存在");
		}
		List<PropertyGridVo> list = new ArrayList<PropertyGridVo>();
		list.add(new PropertyGridVo("流程名称 - ID", vo.getWorkflowName() + " - " + vo.getWorkflowId()));
		list.add(new PropertyGridVo("表单名称 - ID", vo.getFormName() + " - " + vo.getFormId()));
		list.add(new PropertyGridVo("流程类型名称", vo.getWorkflowTypeName()));
		return list;
	}
}
