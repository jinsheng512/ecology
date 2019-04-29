package greedc.actions;

import org.junit.Test;

import test.BaseTest;
import ecustom.test.ActionTest;

public class TestBP09PlanFinishAction extends BaseTest {

	@Test
	public void execute() throws Exception {
		ActionTest test = new ActionTest(5478, "greedc.actions.BP09PlanFinishAction");
		test.setSrc("reject");
		test.setLastOperator(685);
		String status = test.execute();
		System.out.println(status);
	}
}
