package com.tadu.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	private static final int HTTP_STATUS_200 = 200;

	/**
	 * http GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * http GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, String> params) {
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
		
		if (param != null) {
			url += param.toString();
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
//			System.out.println(url + param.toString());
			try {
				FileHelper.writeLine("D:\\yw_chapter_timeout.txt", url + param.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String doPost(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			RequestConfig reqConfig = RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(600000)
					.setConnectionRequestTimeout(600000).build();
			httpPost.setConfig(reqConfig);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}