package k17;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import k17.bean.BookListJson;
import k17.keyt.Sign;
import k17.keyt.SignUtil;
import k17.util.HttpHelper;
import k17.util.HttpParser;

public class K17Test {
	private static final String URI_PREFIX = "http://cup.17k.com/union/book/";
	private static final String URI_BOOK_LIST_JSON = "bookList.json";

	private static final String PARENT_ID = "1021";
	private static final String SECRET_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA09lLyECaAfz64RM0J/E505rQfxbGTTT9Z/30Tuxbdqc7OwMwX1ZHe5tfxuj/VuaE5UAT3j7xZeijY/HHLijSDwIDAQABAkBRaE8WxLxpxy0hEKAaOThfeD5ml/nb8WDvdUdMjMcY8LdrQVjzNs1wIG3vLA5mRkd336TryBWGpeeyx4mfCWkBAiEA7i5izfmFTiiwzfqqsOroMG6Ds3gG557rjTt/SH+pbLsCIQDjspiI5dGeLGQ/BEDu7T8/VskYZRxgJ1HXIHxKPmfkvQIhAL9DKn6Cl4SK8meFmhoVmLyDkmjEwq6ulDLGi1CZi2DPAiEAyszwJMYknC/HnYTpXKS8d2qRs4Oi8VU0BFpvuSS6HjUCIGljzPDLvBCsOW30vx6PI0IFwuP3TpfBLfowlTlr+1v9";

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_ZERO_POINT = "yyyy-MM-dd 00:01:01";

	@Test
	public void t0()  {
		String result=null;
		try {
			result = HttpHelper.doGet("http://topenapi.tadu.com/api/getBookDetail?key=343a4083eb3d78f99e0ac8f7bf3e4015&copyrightid=330&cpid=100098");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
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
		Map<String, String> param = paramOfBookList(PARENT_ID, "2017-10-09 00:01:01", "1");

		String url = URI_PREFIX + URI_BOOK_LIST_JSON;
		System.out.println("request is " + url);
		try {
			String postResult = HttpHelper.doPost(url, param);
			String getResult = HttpHelper.doGet(url, param);
			System.out.println(postResult);
			System.out.println(getResult);
			BookListJson bookListJson = HttpParser.parseBookList(postResult);
			System.out.println(bookListJson);
			boolean hasNextPage = "1".equals(bookListJson.getHasNext());
			while (hasNextPage) {
				param = paramOfBookList(PARENT_ID, "2017-10-09 00:01:01", "2");
				postResult = HttpHelper.doPost(url, param);
				System.out.println(postResult);
				bookListJson = HttpParser.parseBookList(postResult);
				hasNextPage = "1".equals(bookListJson.getHasNext());
				System.out.println(hasNextPage);
			}
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> paramOfBookList(String parentId, String date, String pageNum) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", parentId);
		param.put("lastUpdateDate", date);
		param.put("page", pageNum);
		String sign = createSign(param);
		param.put("sign", sign);
		return param;
	}

	private String createSign(Map<String, String> param) {
		String param_str = SignUtil.getParameterString(param);
		String sign = Sign.sign(param_str, SECRET_KEY);
		return sign;
	}

	@Test
	public void testDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ZERO_POINT);
		String currentDate = sdf.format(new Date());
		System.out.println(currentDate);
	}
}
