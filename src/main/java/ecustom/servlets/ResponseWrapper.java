package ecustom.servlets;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {

	private CustomServletOutputStream outputStream;
	
	public ResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
		outputStream = new CustomServletOutputStream(response.getOutputStream());
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return outputStream;
	}

	public byte[] getByteArrayStream() {
		return outputStream.getByteArrayStream().toByteArray();
	}
}
