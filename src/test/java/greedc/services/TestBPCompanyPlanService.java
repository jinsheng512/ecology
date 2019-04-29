package greedc.services;

import org.junit.Test;

import test.BaseTest;

public class TestBPCompanyPlanService extends BaseTest {

	@Test
	public void cancelAudit() throws Exception {
		BPCompanyPlanService service = new BPCompanyPlanService();
		service.cancelAudit(122);
		System.out.println("反审核成功！");
	}
}
