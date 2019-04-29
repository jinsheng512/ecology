package ecustom.servlets;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class RequestWrapper extends HttpServletRequestWrapper {

	private byte[] requestBody;

	public RequestWrapper(HttpServletRequest request) {
		super(request);  
          
        try {  
            requestBody = IOUtils.toByteArray(request.getInputStream());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(requestBody == null){  
			requestBody = new byte[0];  
        }  
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);    
        return new ServletInputStream() {    
            @Override    
            public int read() throws IOException {    
                return bais.read();    
            }    
        }; 
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}
}