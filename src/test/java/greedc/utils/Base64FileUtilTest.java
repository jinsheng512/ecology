package greedc.utils;

import org.junit.Test;

public class Base64FileUtilTest {

	@Test
	public void base64CodeToZip() throws Exception {
		String baseDir = "E:/项目资料/ZH-珠海/ZH-格力地产/01集成/集成-项目管理流程/";
		String fileNameInZip = "" + System.currentTimeMillis();
		String zipTargetPath = baseDir + fileNameInZip + ".zip";
		String formFilePath = "E:/项目资料/ZH-珠海/ZH-格力地产/01集成/集成-项目管理流程/项目类单据缺少字段.doc";
		String base64Code = Base64FileUtil.encodeBase64File(formFilePath);
		
		Base64FileUtil.base64CodeToZip(base64Code, fileNameInZip, zipTargetPath);
		System.out.println(zipTargetPath);
	}
}
