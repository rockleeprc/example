package goldentime.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tadu.bean.BookInfo;
import com.tadu.constant.URLConsts;
import com.tadu.util.FileHelper;
import com.tadu.util.HttpUtils;
import com.tadu.util.KeyUtils;

public class T {

	private static final String INPUT_FILE_PATH = "E:" + File.separator + "yw_book.txt";
	private static final String OUTPUT_FILE_PATH = "E:" + File.separator + "yw_time.txt";
	private static final String OUTPUT_FAIL_PATH = "E:" + File.separator + "yw_time_fail.txt";

	@Test
	public void t5(){
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_BOOK_INFO);
		params.put("CBID", "22438572000473902");
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		String str = HttpUtils.httpGet(URLConsts.QQ_URL, params);
		System.out.println(str);
	}
	
	@Test
	public void t4() {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_BOOK_INFO);
		params.put("CBID", "22438572000473902");
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		StringBuilder paramsStr = new StringBuilder();
		paramsStr.append("?");
		Set<Map.Entry<String, String>> entries = params.entrySet();
		for (Map.Entry<String, String> entry : entries) {
			String value = (entry.getValue() != null) ? (String.valueOf(entry.getValue())) : "";
			paramsStr.append(entry.getKey() + "=" + value + "&");
		}
		
		// 创建httpget
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpget = new HttpGet(URLConsts.QQ_URL + paramsStr.toString());
		httpget.addHeader("content-type", "application/x-www-form-urlencoded");
		RequestConfig reqConfig = RequestConfig.custom()
					.setSocketTimeout(600000)
					.setConnectTimeout(600000)
					.setConnectionRequestTimeout(600000)
					.build();
		httpget.setConfig(reqConfig);
		try {

			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				
				// 打印响应状态
				System.out.println(response.getStatusLine());
				
				if (entity != null) {
					// 打印响应内容长度
					System.out.println("Response content length: " + entity.getContentLength());
					// 打印响应内容
					System.out.println("Response content: " + EntityUtils.toString(entity));
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void t3() throws IOException {
		File file = new File("E:" + File.separator + "time.txt");
		FileHelper.writeLine(file, "A");
		FileHelper.writeLine(file, "B");
		FileHelper.writeLine(file, "C");
	}
	@Test
	public  void t2() throws IOException {
		File file = new File("E:" + File.separator + "yw_book.txt");
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				System.out.println(line);
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
		// InputStream in = new FileInputStream(file);
		// try {
		// System.out.println(IOUtils.toString(in, "UTF-8"));
		// } finally {
		// IOUtils.closeQuietly(in);
		// }
	}

	@Test
	public void t1() throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_BOOK_INFO);
		params.put("CBID", "22438572000473902");
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
//		String resultJson = HttpUtils.get(URIUtils.URL, params, 10 * 1000, 10 * 1000, "UTF-8");
//		System.out.println(resultJson);
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		BookInfo info = mapper.readValue(resultJson, BookInfo.class);
//		System.out.println(info);

	}
}
