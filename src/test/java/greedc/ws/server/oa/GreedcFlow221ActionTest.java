package greedc.ws.server.oa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import greedc.eas.vo.ExpenseBudgetVo;

public class GreedcFlow221ActionTest {

	@Test
	public void doAfter() {
		GreedcFlow221Action action = new GreedcFlow221Action();
		List<ExpenseBudgetVo> details = new ArrayList<ExpenseBudgetVo>();
		ExpenseBudgetVo vo = new ExpenseBudgetVo();
		vo.setDetailJson("{\"xiangmm\":\"ÏîÄ¿Ãû³Æ\",\"entry\":[{\"bianmxh\":\"±àÂëĞòºÅ\"}]}");
		details.add(vo);
		action.setDetails(details);
		action.doAfter("1");
	}
}
