package greedc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64FileUtil {
	
	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);;
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);

	}

	public static int base64CodeToZip(String base64Code, String fileNameInZip, String zipTargetPath) throws IOException {
		File zipTargetFile = new File(zipTargetPath);
		FileOutputStream os = new FileOutputStream(zipTargetFile);
		ZipOutputStream zos = new ZipOutputStream(os);
		ZipEntry entry = new ZipEntry(fileNameInZip);
		zos.putNextEntry(entry);
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		int fileSize = buffer.length;
		zos.write(buffer);
		zos.close();
		os.close();
		return fileSize;
	}
}
