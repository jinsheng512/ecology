package greedc.services;

import org.junit.Assert;
import org.junit.Test;

import ecustom.services.BigFieldService;

public class BigFieldServiceTest {

	@Test
	public void save() {
		BigFieldService service = new BigFieldService();
		Integer seqNo = service.save("[{\"name\":\"张三\"},{\"name\":\"李四\"}]");
		System.out.println("seqNo: " + seqNo);
		Assert.assertEquals(seqNo != null && seqNo > 0, true);
	}
	
	@Test
	public void getValue() {
		BigFieldService service = new BigFieldService();
		String value = service.getValue(5);
		System.out.println("value: " + value);
		Assert.assertEquals(value != null && value.length() > 0, true);
	}
}
