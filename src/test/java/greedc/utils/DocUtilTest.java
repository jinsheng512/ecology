package greedc.utils;

import org.junit.Test;

import weaver.general.GCONST;

public class DocUtilTest {
	
	@Test
	public void generateRandomDir() {
		GCONST.setRootPath("WebContent/");
		GCONST.setServerName("ecology");
		String baseDirPath = "D:/WEAVER/ecology/filesystem";
		String dirPath = DocUtil.generateRandomDir(baseDirPath);
		System.out.println(dirPath);
	}

}
