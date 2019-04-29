package ecustom.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class CustomServletOutputStream extends ServletOutputStream {
	
	private ServletOutputStream outputStream;
	private ByteArrayOutputStream byteStream;

	public CustomServletOutputStream(ServletOutputStream outputStream) {
		this.outputStream = outputStream;
		byteStream = new ByteArrayOutputStream();
	}

	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
		byteStream.write(b);
	}
	
	public ByteArrayOutputStream getByteArrayStream() {
		return byteStream;
	}
}
