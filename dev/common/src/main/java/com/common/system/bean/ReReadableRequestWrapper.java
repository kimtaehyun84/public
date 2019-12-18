package com.common.system.bean;



import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ReReadableRequestWrapper extends HttpServletRequestWrapper {

	private final Charset encoding;
	private byte[] rawData;
	private final String body;

	public ReReadableRequestWrapper(HttpServletRequest request) throws IOException {

		super(request);
		String characterEncoding = request.getCharacterEncoding();
		if(StringUtils.isEmpty(characterEncoding)){
			characterEncoding = StandardCharsets.UTF_8.name();
		}
		this.encoding = Charset.forName(characterEncoding);

		StringBuilder stringBuilder = new StringBuilder();

		BufferedReader bufferedReader = null;

		try {

			InputStream inputStream = request.getInputStream();

			if (inputStream != null) {

				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}

			} else {
				stringBuilder.append("");
			}

		} catch (IOException ex) {

			throw ex;

		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

		ServletInputStream servletInputStream = new ServletInputStream() {

			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
		return servletInputStream;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public String getBody() {
		return this.body;
	}

}
