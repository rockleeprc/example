package k17.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;

/**
 * Java HTTP请求对象 发送GET/POST请求工具类
 */
public class HttpRequester {

	private String defaultContentEncoding;

	public HttpRequester() {
		this.defaultContentEncoding = Charset.defaultCharset().name();
		System.out.println(defaultContentEncoding);
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString) throws IOException {
		return this.send(urlString, "GET", null, null);
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params) throws IOException {
		return this.send(urlString, "GET", params, null);
	}

	/**
	 * 发送GET请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @param propertys
	 *            请求属性
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendGet(String urlString, Map<String, String> params, Map<String, String> propertys)
			throws IOException {
		return this.send(urlString, "GET", params, propertys);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString) throws IOException {
		return this.send(urlString, "POST", null, null);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params) throws IOException {
		return this.send(urlString, "POST", params, null);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param urlString
	 *            URL地址
	 * @param params
	 *            参数集合
	 * @param propertys
	 *            请求属性
	 * @return 响应对象
	 * @throws IOException
	 */
	public HttpRespons sendPost(String urlString, Map<String, String> params, Map<String, String> propertys)
			throws IOException {
		return this.send(urlString, "POST", params, propertys);
	}

	/**
	 * 发送HTTP请求
	 * 
	 * @param url
	 *            地址
	 * @param method
	 *            get/post
	 * @param parameters
	 *            添加由键值对指定的请求参数
	 * @param propertys
	 *            添加由键值对指定的一般请求属性
	 * @return 响映对象
	 * @throws IOException
	 */
	private HttpRespons send(String url, String method, Map<String, String> parameters,
			Map<String, String> propertys) throws IOException {

		if (method.equalsIgnoreCase("GET") && parameters != null) {
			String param = createGetParam(parameters);
			url += param;
			// System.out.println("get url=" + urlString);
		}

		HttpURLConnection urlConnection = createDefaultConnectionAndOpen(url,parameters);
		urlConnection.setRequestMethod(method);

		if (propertys != null) {
			setRequestProperty(urlConnection,propertys);
		}
		
		if (method.equalsIgnoreCase("POST") && parameters != null) {
			String param = createPostParam(parameters);
			urlConnection.getOutputStream().write(param.toString().getBytes());
		}
		urlConnection.getOutputStream().flush();
		urlConnection.getOutputStream().close();
		this.parseResponse(urlConnection);
		return null;
	}

	private String createPostParam(Map<String, String> parameters) {
		StringBuilder params = new StringBuilder();
		for (String key : parameters.keySet()) {
			params.append("&");
			params.append(key).append("=").append(parameters.get(key));
		}
		return params.toString();
	}

	private void setRequestProperty(HttpURLConnection urlConnection, Map<String, String> propertys) {
		for (String key : propertys.keySet()) {
			urlConnection.setRequestProperty(key, propertys.get(key));
		}
	}

	private HttpURLConnection createDefaultConnectionAndOpen(String uri, Map<String, String> parameters) throws IOException {
		URL url = new URL(uri);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(30000);
		urlConnection.setReadTimeout(30000);
		return urlConnection;
	}

	private String createGetParam(Map<String, String> parameters) {
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (String key : parameters.keySet()) {
			if (i == 0) {
				params.append("?");
			} else {
				params.append("&");
			}
			params.append(key).append("=").append(URLEncoder.encode(parameters.get(key)));
			i++;
		}
		return params.toString();
	}

	private HttpRespons parseResponse(HttpURLConnection urlConnection) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		try {
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// httpResponser.contentCollection.add(line);
				// System.out.println(line);
				content.append(line).append("\r\n");
				// line = bufferedReader.readLine();
			}
			System.out.println(content.toString());

			String ecod = urlConnection.getContentEncoding();
			if (ecod == null)
				ecod = this.defaultContentEncoding;
			/*
			 * httpResponser.defaultPort =
			 * urlConnection.getURL().getDefaultPort(); httpResponser.file =
			 * urlConnection.getURL().getFile(); httpResponser.host =
			 * urlConnection.getURL().getHost(); httpResponser.path =
			 * urlConnection.getURL().getPath(); httpResponser.port =
			 * urlConnection.getURL().getPort(); httpResponser.protocol =
			 * urlConnection.getURL().getProtocol(); httpResponser.query =
			 * urlConnection.getURL().getQuery(); httpResponser.ref =
			 * urlConnection.getURL().getRef(); httpResponser.userInfo =
			 * urlConnection.getURL().getUserInfo(); httpResponser.content = new
			 * String(content.toString().getBytes(), ecod);
			 * httpResponser.contentEncoding = ecod; httpResponser.code =
			 * urlConnection.getResponseCode(); httpResponser.message =
			 * urlConnection.getResponseMessage(); httpResponser.contentType =
			 * urlConnection.getContentType(); httpResponser.method =
			 * urlConnection.getRequestMethod(); httpResponser.connectTimeout =
			 * urlConnection.getConnectTimeout(); httpResponser.readTimeout =
			 * urlConnection.getReadTimeout();
			 * System.out.println(urlConnection.getResponseCode()); return
			 * httpResponser;
			 */
		} catch (IOException e) {
			throw e;
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		return null;
	}

	/**
	 * 得到响应对象
	 * 
	 * @param urlConnection
	 * @return 响应对象
	 * @throws IOException
	 */
	private HttpRespons parseResponse1(String urlString, HttpURLConnection urlConnection) throws IOException {
		HttpRespons httpResponser = new HttpRespons();
		InputStream in = urlConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		try {
			// httpResponser.contentCollection = new Vector<String>();
			StringBuilder content = new StringBuilder();
			// String line = bufferedReader.readLine();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// httpResponser.contentCollection.add(line);
				// System.out.println(line);
				content.append(line).append("\r\n");
				// line = bufferedReader.readLine();
			}
			System.out.println(content.toString());

			String ecod = urlConnection.getContentEncoding();
			if (ecod == null)
				ecod = this.defaultContentEncoding;

			httpResponser.urlString = urlString;
			httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();
			httpResponser.file = urlConnection.getURL().getFile();
			httpResponser.host = urlConnection.getURL().getHost();
			httpResponser.path = urlConnection.getURL().getPath();
			httpResponser.port = urlConnection.getURL().getPort();
			httpResponser.protocol = urlConnection.getURL().getProtocol();
			httpResponser.query = urlConnection.getURL().getQuery();
			httpResponser.ref = urlConnection.getURL().getRef();
			httpResponser.userInfo = urlConnection.getURL().getUserInfo();
			httpResponser.content = new String(content.toString().getBytes(), ecod);
			httpResponser.contentEncoding = ecod;
			httpResponser.code = urlConnection.getResponseCode();
			httpResponser.message = urlConnection.getResponseMessage();
			httpResponser.contentType = urlConnection.getContentType();
			httpResponser.method = urlConnection.getRequestMethod();
			httpResponser.connectTimeout = urlConnection.getConnectTimeout();
			httpResponser.readTimeout = urlConnection.getReadTimeout();
			System.out.println(urlConnection.getResponseCode());
			return httpResponser;
		} catch (IOException e) {
			throw e;
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}

	/**
	 * 默认的响应字符集
	 */
	public String getDefaultContentEncoding() {
		return this.defaultContentEncoding;
	}

	/**
	 * 设置默认的响应字符集
	 */
	public void setDefaultContentEncoding(String defaultContentEncoding) {
		this.defaultContentEncoding = defaultContentEncoding;
	}
}