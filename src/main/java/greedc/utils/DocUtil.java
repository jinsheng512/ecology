package greedc.utils;

import java.io.File;
import java.util.UUID;

import weaver.general.MD5;

import weaver.file.FileUpload;

public class DocUtil {

	/**
	 * 生成附件存放的随机目录。
	 * @param baseDirPath	基目录，如果为空默认为“${contextPath}/ecology/filesystem”
	 * @return
	 */
	public static String generateRandomDir(String baseDirPath) {
		String fileDir = FileUpload.getCreateDir(baseDirPath);
		File dirFile = new File(fileDir);
		if (dirFile.exists() == false) {
			dirFile.mkdirs(); 
		}
		return fileDir;
	}
	
	public static String generateRandomFileName() {
		MD5 md5 = new MD5();
		String fileName = md5.getMD5ofStr(UUID.randomUUID().toString());
		return fileName;
	}
}
