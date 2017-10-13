package k17;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.tadu.util.HttpUtils;

import k17.keyt.Sign;
import k17.keyt.SignUtil;
import k17.util.HttpRequester;
import k17.util.HttpRespons;

public class K17Test {
	private static final String URI_PREFIX = "http://cup.17k.com/union/book/";
	private static final String URI_BOOK_LIST_JSON = "bookList.json";

	private static final String PARENT_ID = "1021";
	private static final String SECRET_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA09lLyECaAfz64RM0J/E505rQfxbGTTT9Z/30Tuxbdqc7OwMwX1ZHe5tfxuj/VuaE5UAT3j7xZeijY/HHLijSDwIDAQABAkBRaE8WxLxpxy0hEKAaOThfeD5ml/nb8WDvdUdMjMcY8LdrQVjzNs1wIG3vLA5mRkd336TryBWGpeeyx4mfCWkBAiEA7i5izfmFTiiwzfqqsOroMG6Ds3gG557rjTt/SH+pbLsCIQDjspiI5dGeLGQ/BEDu7T8/VskYZRxgJ1HXIHxKPmfkvQIhAL9DKn6Cl4SK8meFmhoVmLyDkmjEwq6ulDLGi1CZi2DPAiEAyszwJMYknC/HnYTpXKS8d2qRs4Oi8VU0BFpvuSS6HjUCIGljzPDLvBCsOW30vx6PI0IFwuP3TpfBLfowlTlr+1v9";

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_ZERO_POINT = "yyyy-MM-dd 00:00:00";
	
	@Test
	public void t2() {
		for (int i = 0; i < 10000; i++) {
			String result = HttpUtils.doGet(
					"http://openapi.tadu.com/book/volume/chapter/info/?identity=hzys&bookId=380561&volumeId=1&chapterId=27648329");
			System.out.println(i);
			if(result.contains("A00005")){
				System.out.println(result);
				break;
			}
		}
	}
	
	@Test
	public void t3() throws IOException{
		HttpRequester sender = new HttpRequester();
		
		HttpRespons sendGet;
		try {
			sendGet = sender.sendGet("http://www.baidu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 章节有更新的书籍列表
	 * 
	 * @throws IOException
	 */
	@Test
	public void testbookList() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ZERO_POINT);
		String currentDate = sdf.format(new Date());
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", PARENT_ID);
		param.put("lastUpdateDate", "2017-10-09 00:01:01");
		param.put("page", "1");
//		System.out.println(currentDate);
		String param_str = SignUtil.getParameterString(param);
//		System.out.println("parms=" + param_str);
//		System.out.println("签名结果：" + Sign.sign(param_str, SECRET_KEY));
		String sign = Sign.sign(param_str, SECRET_KEY);
		param.put("sign", sign);

		HttpRequester sender = new HttpRequester();
		String url = URI_PREFIX + URI_BOOK_LIST_JSON;
		System.out.println("request is " + url);
		try {
			sender.sendPost(url, param);
			sender.sendGet(url, param);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ZERO_POINT);
		String currentDate = sdf.format(new Date());
		System.out.println(currentDate);
	}
}
