package com.mmf.rest.transport.http;

import android.os.Build;
import android.util.Base64;
import com.mmf.rest.exceptions.UnexpectedResponseCodeException;
import com.mmf.rest.transport.RestResponse;
import com.mmf.util.Logger;
import org.apache.http.auth.InvalidCredentialsException;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpDataProvider implements DataProvider {
	public static final String AUTH_HEADER = "Authorization";
	public static final int TIMEOUT = 10 * 1000;
	private URL connectionURL;

	public HttpDataProvider(String url) throws MalformedURLException {
		this.connectionURL = new URL(url);
	}

	@Override
	public void get(String login, String password, OutputStream outputWriter) throws IOException, UnexpectedResponseCodeException, InvalidCredentialsException {
		HttpURLConnection connection = null;
		try {
			connection = openConnection(login, password, -1, "get");
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
				throw new InvalidCredentialsException("You unauthorized to use service");
			}
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new UnexpectedResponseCodeException(responseCode);
			}
			readData(connection, outputWriter);
		} finally {
			closeConnection(connection);
		}
	}

//	public RestResponse get(String login, String password) throws IOException, InvalidCredentialsException {
//		HttpURLConnection connection = null;
//		try {
//			connection = openConnection(login, password, -1, "get");
//			int responseCode = connection.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
//				throw new InvalidCredentialsException("You unauthorized to use service");
//			}
//			return new RestResponse(responseCode, connection.getResponseMessage());
//		} finally {
//			closeConnection(connection);
//		}
//	}

//	public RestResponse put(String login, String password, String requestData) throws IOException, InvalidCredentialsException {
//		HttpURLConnection connection = null;
//		try {
//			byte[] dataBytes = requestData.getBytes("utf-8");
//			connection = openConnection(login, password, dataBytes.length, "put");
//			writeData(connection, dataBytes);
//			int responseCode = connection.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
//				throw new InvalidCredentialsException("You unauthorized to use service");
//			}
//			return new RestResponse(responseCode, connection.getResponseMessage());
//		} finally {
//			closeConnection(connection);
//		}
//	}

//	public RestResponse delete(String login, String password) throws IOException, InvalidCredentialsException {
//		HttpURLConnection connection = null;
//		try {
//			connection = openConnection(login, password, -1, "delete");
//			int responseCode = connection.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
//				throw new InvalidCredentialsException("You unauthorized to use service");
//			}
//			return new RestResponse(responseCode, connection.getResponseMessage());
//		} finally {
//			closeConnection(connection);
//		}
//	}

	@Override
	public void post(String login, String password, String requestData, OutputStream outputWriter) throws IOException, UnexpectedResponseCodeException, InvalidCredentialsException {
		HttpURLConnection connection = null;
		try {
			byte[] dataBytes = requestData.getBytes("utf-8");
			connection = openConnection(login, password, dataBytes.length, "post");
			writeData(connection, dataBytes);
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
				throw new InvalidCredentialsException("You unauthorized to use service");
			}
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new UnexpectedResponseCodeException(responseCode);
			}
			readData(connection, outputWriter);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public void doLogin(String login, String password) throws IOException, UnexpectedResponseCodeException, InvalidCredentialsException {
		HttpURLConnection connection = null;
		try {
			connection = openConnection(login, password, -1, "post");
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
				throw new InvalidCredentialsException("You unauthorized to use service");
			}
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new UnexpectedResponseCodeException(responseCode);
			}
		} finally {
			closeConnection(connection);
		}
	}

	public static String getAuthPropertyValue(String login, String password) {
		StringBuilder buf = new StringBuilder(login);
		buf.append(":");
		buf.append(password);
		byte[] bytes = null;
		bytes = buf.toString().getBytes();
		return ("Basic " + Base64.encodeToString(bytes, Base64.DEFAULT).trim());
	}

	private HttpURLConnection openConnection(String login, String password,	int contentLength, String requestMethod) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) connectionURL.openConnection();
		connection.setUseCaches(false);
		connection.setDefaultUseCaches(false);
		connection.setConnectTimeout(TIMEOUT);
		connection.setDoInput(true);
		// connection.setAllowUserInteraction(false);

		if ("post".equalsIgnoreCase(requestMethod)) {
			connection.setRequestMethod("POST");
			if (contentLength != -1) {
				connection.setRequestProperty("Content-Length", Integer.toString(contentLength));
				connection.setFixedLengthStreamingMode(contentLength);
			}
			connection.setDoOutput(true);
		} else if ("put".equalsIgnoreCase(requestMethod)) {
			connection.setRequestMethod("PUT");
			if (contentLength != -1) {
				connection.setRequestProperty("Content-Length",	Integer.toString(contentLength));
				connection.setFixedLengthStreamingMode(contentLength);
			}
			connection.setDoOutput(true);
		} else if ("delete".equalsIgnoreCase(requestMethod)) {
			connection.setRequestMethod("DELETE");
		} else if ("get".equalsIgnoreCase(requestMethod)) {
			connection.setRequestMethod("GET");
		}
		connection.setRequestProperty("User-Agent", Build.MODEL);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Host", connectionURL.getHost());
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept-Encoding", "utf-8");
		connection.setRequestProperty(AUTH_HEADER, getAuthPropertyValue(login, password));

		return connection;
	}

	private void closeConnection(HttpURLConnection connection) {
		if (connection != null) {
			connection.disconnect();
		}
	}

	private void readData(HttpURLConnection httpConnection, OutputStream outputWriter) throws IOException {
		if (outputWriter != null) {
			byte[] readBuffer = new byte[1024];
			int count;
			InputStream reader = null;
			try {
				reader = httpConnection.getInputStream();
				while ((count = reader.read(readBuffer)) > 0) {
					outputWriter.write(readBuffer, 0, count);
				}
			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		}
	}

	private void writeData(HttpURLConnection connection, byte[] data) throws IOException {
		OutputStream output = null;
		try {
			output = connection.getOutputStream();
			output.write(data);
			output.flush();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

}
