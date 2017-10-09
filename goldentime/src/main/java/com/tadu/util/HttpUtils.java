package com.tadu.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	private static final int HTTP_STATUS_200 = 200;

	/**
	 * http GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url) {
		return httpGet(url, null);
	}

	/**
	 * http GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String httpGet(String url, Map<String, String> params) {
		StringBuilder param = null;
		if (params != null) {
			param = new StringBuilder();
			param.append("?");
			Set<Map.Entry<String, String>> entries = params.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				String value = (entry.getValue() != null) ? (String.valueOf(entry.getValue())) : "";
				param.append(entry.getKey() + "=" + value + "&");
			}
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
//		System.out.println(url + param.toString());
		if(param!=null){
			url+=param.toString();
		}
		HttpGet httpGet = new HttpGet(url);
		RequestConfig reqConfig = RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(600000)
				.setConnectionRequestTimeout(600000).build();

		httpGet.setConfig(reqConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			StatusLine status = response.getStatusLine();
			if (HTTP_STATUS_200 == status.getStatusCode()) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
//			System.err.println(param.toString());
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}