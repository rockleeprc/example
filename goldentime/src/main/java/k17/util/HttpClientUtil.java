package k17.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static void main(String[] args) {
		String url = "http://openapi.tadu.com/360Book/BookList";
		Map<String,String> map = new HashMap<String,String>();
		map.put("begin_time", "2017100911");
		map.put("end_time", "2017100915");
		String result = doPost(url,new HashMap<String, String>(),"utf-8");
		System.out.println(result);
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
