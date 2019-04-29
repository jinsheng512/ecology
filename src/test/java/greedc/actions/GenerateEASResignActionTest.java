package greedc.actions;

import org.junit.Test;

import ecustom.test.ActionTest;
import weaver.general.GCONST;

public class GenerateEASResignActionTest {
	
	@Test
	public void execute() throws Exception {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
		ActionTest test = new ActionTest(2533, "greedc.actions.GenerateEASResignAction");
		test.setSrc("submit");
		test.setLastOperator(504);
		String status = test.execute();
		System.out.println(status);
	}
	
}
