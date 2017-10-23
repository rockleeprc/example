package k17;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import k17.bean.BookChapter;
import k17.bean.BookInfo;
import k17.bean.BookListJson;
import k17.bean.BookVolumnChapterInfo;
import k17.bean.ChapterContentJson;
import k17.bean.VolumnAndChapterListJson;
import k17.keyt.Sign;
import k17.keyt.SignUtil;
import k17.util.HttpHelper;
import k17.util.HttpParser;

public class K17Test {
	private static final String URL_PREFIX = "http://cup.17k.com/union/book/";
	private static final String URL_BOOK_LIST_JSON = "bookList.json";
	private static final String URL_VOLUME_LIST_JSON = "volumeList.json";
	private static final String URL_VOLUMNAND_CHAPTER_LIST_JSON="volumnAndChapterList.json";
	private static final String URL_CHAPTER_CONTENT_JSON="chapterContent.json";

	private static final String PARTNER_ID = "1021";
	private static final String SECRET_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA09lLyECaAfz64RM0J/E505rQfxbGTTT9Z/30Tuxbdqc7OwMwX1ZHe5tfxuj/VuaE5UAT3j7xZeijY/HHLijSDwIDAQABAkBRaE8WxLxpxy0hEKAaOThfeD5ml/nb8WDvdUdMjMcY8LdrQVjzNs1wIG3vLA5mRkd336TryBWGpeeyx4mfCWkBAiEA7i5izfmFTiiwzfqqsOroMG6Ds3gG557rjTt/SH+pbLsCIQDjspiI5dGeLGQ/BEDu7T8/VskYZRxgJ1HXIHxKPmfkvQIhAL9DKn6Cl4SK8meFmhoVmLyDkmjEwq6ulDLGi1CZi2DPAiEAyszwJMYknC/HnYTpXKS8d2qRs4Oi8VU0BFpvuSS6HjUCIGljzPDLvBCsOW30vx6PI0IFwuP3TpfBLfowlTlr+1v9";

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_ZERO_POINT = "yyyy-MM-dd 00:01:01";

	@Test
	public void t0() {
		String str="拉开之后，柳刀瞬间傻眼了。<br><br>　　柜子的背部不见了，露出一";
		System.out.println(str.replaceAll("<br><br>", "\n"));
	}
	
	@Test
	public void testChapterContent(){
		 String chapterId="28178827";
		 String chapterId1="28178830";
		 final  String url = URL_PREFIX+ URL_CHAPTER_CONTENT_JSON;
		 Map<String, String> params = paramOfChapterContent(chapterId);
		 try {
			String resultPost = HttpHelper.doPost(url, params);
			System.out.println(resultPost);
			ChapterContentJson chapterContentJson = HttpParser.parseChapterContent(resultPost);
			printChapterContent(chapterContentJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printChapterContent(ChapterContentJson chapterContentJson) {
		System.out.println(chapterContentJson);
		
	}

	private Map<String, String> paramOfChapterContent(String chapterId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", PARTNER_ID);
		param.put("chapterId", chapterId);
		String sign = createSign(param);
		param.put("sign", sign);
		return param;
		
	}

	@Test
	public void testVolumnAndChapterList() {
		String bookId = "1721756";
		Map<String, String> param = paramOfVolumeList(PARTNER_ID, bookId);
		final String url = URL_PREFIX+URL_VOLUMNAND_CHAPTER_LIST_JSON;
		try {
			String postResult = HttpHelper.doPost(url,param);
			System.out.println("VolumnAndChapterList-->"+postResult);
			VolumnAndChapterListJson dto = HttpParser.parseVolumnAndChapterList(postResult);
//			System.out.println(postResult);
			printVolumnAndChapterListJson(dto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printVolumnAndChapterListJson(VolumnAndChapterListJson dto) {
		System.out.println(dto);
		List<BookVolumnChapterInfo> content = dto.getContent();
		for (BookVolumnChapterInfo bvci : content) {
			System.out.println(bvci);
			List<BookChapter> chapterList = bvci.getChapterList();
			for (BookChapter chapter : chapterList) {
				System.out.println(chapter);
			}
		}
	}

	@Test
	public void testVolumeList() {
		String bookId = "1721756";
		Map<String, String> param = paramOfVolumeList(PARTNER_ID, bookId);
		final String url =  URL_PREFIX + URL_VOLUME_LIST_JSON;
		try {
			String postResult = HttpHelper.doPost(url, param);
			System.out.println(postResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Map<String,String> paramOfVolumeList(String partnerId,String bookId){
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", partnerId);
		param.put("bookId", bookId);
		String sign = createSign(param);
		param.put("sign", sign);
		return param;
	}
	

	/**
	 * 章节有更新的书籍列表
	 * 
	 * @throws IOException
	 */
	@Test
	public void testBookList() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_ZERO_POINT);
		String currentDate = sdf.format(new Date());
		Map<String, String> param = paramOfBookList(PARTNER_ID, "2011-08-17 17:50:35", "1");

		String url = URL_PREFIX + URL_BOOK_LIST_JSON;
		System.out.println("request is " + url);
		try {
			String postResult = HttpHelper.doPost(url, param);
			String getResult = HttpHelper.doGet(url, param);
			System.out.println(postResult);
//			System.out.println(getResult);
			BookListJson bookListJson = HttpParser.parseBookList(postResult);
			System.out.println(bookListJson);
			printBookList(bookListJson);
			boolean hasNextPage = "1".equals(bookListJson.getHasNext());
			while (hasNextPage) {
				param = paramOfBookList(PARTNER_ID, "2017-10-09 00:01:01", "2");
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

	private void printBookList(BookListJson bookListJson) {
		List<BookInfo> content = bookListJson.getContent();
		for (BookInfo bi : content) {
			System.out.println("bookInfo = " + bi);
		}
	}

	private Map<String, String> paramOfBookList(String partnerId, String date, String pageNum) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", partnerId);
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
