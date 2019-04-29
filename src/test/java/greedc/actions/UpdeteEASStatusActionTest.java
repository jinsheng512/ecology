package greedc.actions;

import org.junit.Assert;
import org.junit.Test;

import ecustom.test.ActionTest;
import weaver.general.GCONST;
import weaver.interfaces.workflow.action.Action;

/**
 * 测试。
 * @author Willaim
 *
 */
public class UpdeteEASStatusActionTest {

	@Test
	public void execute() throws Exception {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
		ActionTest test = new ActionTest(3643, "greedc.actions.UpdeteEASStatusAction");
		test.setLastOperator(929);
		test.setSrc("submit");
		String actionResult = test.execute();
		Assert.assertEquals(actionResult, Action.SUCCESS);
	}
}
