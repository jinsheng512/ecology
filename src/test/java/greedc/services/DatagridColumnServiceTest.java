package greedc.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import greedc.entities.DatagridColumn;

public class DatagridColumnServiceTest {

	@Test
	public void getForm44EntriesColumns() {
		DatagridColumnService service = new DatagridColumnService();
		List<DatagridColumn> list = service.getForm44EntriesColumns(424);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		Assert.assertEquals(list.get(0).getTitle(), "Ö°Ô±ÐÕÃû");
	}
}
