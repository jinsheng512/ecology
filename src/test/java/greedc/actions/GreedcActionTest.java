package greedc.actions;

import org.junit.Test;

import ecustom.test.ActionTest;
import weaver.general.GCONST;

public class GreedcActionTest {

	@Test
	public void execute() throws Exception {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
		ActionTest test = new ActionTest(2385, "greedc.actions.GenerateEASLeaveAction");
//		ActionTest test = new ActionTest(2385, "greedc.actions.GenerateEASResignAction");
//		ActionTest test = new ActionTest(594, "greedc.actions.UpdeteEASStatusAction");
//		test.setSrc("reject");
		test.setLastOperator(1321);
		String status = test.execute();
		System.out.println(status);
	}
}
