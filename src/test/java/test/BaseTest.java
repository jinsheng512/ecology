package test;

import org.junit.Before;
import weaver.general.GCONST;

public class BaseTest {

	@Before
	public void before() {
		GCONST.setServerName("ecology");
		GCONST.setRootPath("WebContent/");
	}
}
