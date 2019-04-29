package greedc.services;

import org.junit.Assert;
import org.junit.Test;

import greedc.entities.Form59;
import weaver.general.GCONST;

public class Form59ServiceTest {

	@Test
	public void updateForm() {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
		Form59Service service = new Form59Service();
		Form59 form = new Form59();
		form.setRequestId(296);
		form.setGongcfkxx(1151);
		Assert.assertEquals(service.updateForm(form), true);
	}
}
