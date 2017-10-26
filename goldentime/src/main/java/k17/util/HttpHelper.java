package k17.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 * k17接口http get/post请求辅助类
 */
public class HttpHelper {

	private static final String CHARSET_ENCODE;
	private static final String CHARSET_UTF8 = "utf-8";

	static {
		CHARSET_ENCODE = Charset.defaultCharset().name();
	}

	public static String doGet(String url) throws IOException {
		return doGet(url, null);
	}

	public static String doGet(String url, Map<String, String> params) throws IOException {
		return doGet(url, params, null);
	}

	public static String doGet(String url, Map<String, String> params, Map<String, String> properties)
			throws IOException {
		// return send(url, "GET", params, properties);
		return get(url, "GET", params, properties);
	}

	private static String get(String url, String method, Map<String, String> parameters, Map<String, String> properties)
			throws IOException {

		if (parameters != null) {
			String param = createGetParam(parameters);
			url += param;
			System.out.println("Get url=" + url);
		}

		HttpURLConnection urlConnection = createGetConnection(url);
		urlConnection.setRequestMethod(method);

		if (properties != null) {
			setRequestProperties(urlConnection, properties);
		}

		//urlConnection.getOutputStream().flush();
		//urlConnection.getOutputStream().close();
		return parseResponse(urlConnection);
	}

	public static String doPost(String url) throws IOException {
		return post(url, "POST", null, null);
	}

	public static String doPost(String url, Map<String, String> params) throws IOException {
		return post(url, "POST", params, null);
	}

	public static String doPost(String url, Map<String, String> params, Map<String, String> properties)
			throws IOException {
		// return send(url, "POST", params, properties);
		return post(url, "POST", params, properties);
	}

	private static String post(String url, String method, Map<String, String> parameters, Map<String, String> properties)
			throws IOException {

		HttpURLConnection urlConnection = createDefaultConnection(url);
		urlConnection.setRequestMethod(method);

		if (properties != null) {
			setRequestProperties(urlConnection, properties);
		}

		if (parameters != null) {
			String param = createPostParam(parameters);
			System.out.println(param);
			urlConnection.getOutputStream().write(param.toString().getBytes());
		}
		urlConnection.getOutputStream().flush();
		urlConnection.getOutputStream().close();
		return parseResponse(urlConnection);
	}

	@Deprecated
	private static String send(String url, String method, Map<String, String> parameters, Map<String, String> properties)
			throws IOException {

		if (method.equalsIgnoreCase("GET") && parameters != null) {
			String param = createGetParam(parameters);
			url += param;
			System.out.println("Get url=" + url);
		}

		HttpURLConnection urlConnection = createDefaultConnection(url);
		urlConnection.setRequestMethod(method);

		if (properties != null) {
			setRequestProperties(urlConnection, properties);
		}

		if (method.equalsIgnoreCase("POST") && parameters != null) {
			String param = createPostParam(parameters);
			System.out.println(param);
			urlConnection.getOutputStream().write(param.toString().getBytes());
		}
		urlConnection.getOutputStream().flush();
		urlConnection.getOutputStream().close();
		return parseResponse(urlConnection);
	}

	private static String createPostParam(Map<String, String> parameters) throws UnsupportedEncodingException {
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (String key : parameters.keySet()) {
			if (i != 0) {
				params.append("&");
			}
			params.append(key).append("=").append(URLEncoder.encode(parameters.get(key), CHARSET_UTF8));
			i++;
		}
		return params.toString();
	}

	private static void setRequestProperties(HttpURLConnection urlConnection, Map<String, String> properties) {
		for (String key : properties.keySet()) {
			urlConnection.setRequestProperty(key, properties.get(key));
		}
	}
	
	private static HttpURLConnection createGetConnection(String uri) throws IOException {
		URL url = new URL(uri);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//		urlConnection.setDoOutput(true);
//		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(30000);
		urlConnection.setReadTimeout(30000);
		return urlConnection;
	}

	private static HttpURLConnection createDefaultConnection(String uri) throws IOException {
		URL url = new URL(uri);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(30000);
		urlConnection.setReadTimeout(30000);
		return urlConnection;
	}

	private static String createGetParam(Map<String, String> parameters) throws UnsupportedEncodingException {
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (String key : parameters.keySet()) {
			if (i == 0) {
				params.append("?");
			} else {
				params.append("&");
			}
			params.append(key).append("=").append(URLEncoder.encode(parameters.get(key), CHARSET_UTF8));
			i++;
		}
		return params.toString();
	}

	private static String parseResponse(HttpURLConnection connection) throws IOException {
		StringBuilder content = new StringBuilder();
		if (HttpServletResponse.SC_OK == connection.getResponseCode()) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			try {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					content.append(line).append("\r\n");
				}
				System.out.println(line);
			} catch (IOException e) {
				throw e;
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (connection != null)
					connection.disconnect();
			}
		} else {
			// TODO 非200怎么处理？
			throw new RuntimeException("http请求失败：responseCode=" + connection.getResponseCode());
		}
		return content.toString();
	}

}