package greedc.servlets;

import java.io.File;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;

public class FileUploadServletTest {

	@Test
	public void upload() throws HttpException, IOException {
		
		File file = new File("E:\\最新.xlsx");
		String result = null;
		String url = "http://112.91.150.58:8080/greedc/servlets/FileUpload-upload.json";
		PostMethod method = new PostMethod(url);
		HttpClient client = new HttpClient();
		Part[] parts = new Part[]{
				new StringPart("workflowId", "163"),
				new StringPart("fileName", "最新", "UTF-8"),
				new StringPart("fileType", "xlsx"),
				new StringPart("empCode", "002486"),
				new FilePart("file", file)  
		};  
		MultipartRequestEntity entity = new MultipartRequestEntity(parts, method.getParams());  
		method.setRequestEntity(entity);

		int status = client.executeMethod(method);
		if(status==HttpStatus.SC_OK) {
			result = method.getResponseBodyAsString();   
		}else{
			result = null;
		}
		System.out.println("Result: " + result);
		method.releaseConnection();
	}
}
