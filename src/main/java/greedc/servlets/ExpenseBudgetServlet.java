package greedc.servlets;

import java.util.List;

import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.eas.services.ExpenseBudgetService;
import greedc.eas.vo.ExpenseBudgetVo;

/**
 * 费用预算清单条目数据请求处理类。<br/>
 * 目前可用于：项目付款计划会签表。
 * @author 曹水涛
 */
public class ExpenseBudgetServlet extends BaseServlet {

	/**
	 * 根据RequestId查找费用预算清单条目结果集。
	 * @param requestId
	 * @return
	 */
	public Response findByRequestId() {
		int requestId = CustomUtil.getInt(getParameter("requestId", true));
		ExpenseBudgetService service = new ExpenseBudgetService();
		List<ExpenseBudgetVo> vos = service.findByRequestId(requestId);
		return new Response(true, "OK", vos);
	}
}
