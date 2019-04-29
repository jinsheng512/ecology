package test.greedc.services;

import java.util.List;

import org.junit.Test;

import greedc.entities.PersonIndex;
import greedc.services.PersonIndexService;
import test.BaseTest;

public class TestPersonIndexService extends BaseTest {

	@Test
	public void reject() throws Exception {
		PersonIndexService service = new PersonIndexService();
		service.reject(321, 2017, 0, 484);
	}
	
	@Test
	public void listDeptAppr() throws Exception {
		PersonIndexService service = new PersonIndexService();
		List<PersonIndex> list = service.listDeptAppr(61, 2017, 0);
		System.out.println(list);
	}
}
