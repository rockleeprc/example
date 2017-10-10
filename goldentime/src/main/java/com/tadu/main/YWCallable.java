package com.tadu.main;

import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tadu.bean.BookContent;
import com.tadu.bean.BookInfo;
import com.tadu.bean.ChapterList;
import com.tadu.bean.ChapterList.Result.ResultData;
import com.tadu.constant.URLConsts;
import com.tadu.util.HttpUtils;
import com.tadu.util.KeyUtils;

public class YWCallable implements Runnable {

	private static final int PAGE_SIZE = 50;

	private String id;

	public YWCallable(String id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		// requestBookAndChapter(id);
		requestBookAndChapterSliptPage(id);
	}

	public void requestBookAndChapterSliptPage(String id) {
		String bookInfoJson = requestBookInfo(id);
		System.out.println(bookInfoJson);
		BookInfo bookInfo = parseBookInfo(bookInfoJson);
		if (bookInfo != null) {
			String cbid = bookInfo.getResult().getBook().getcBID();
			String chapterJson = requestChapterlist(cbid, 1, 1);
			ChapterList chapterList = parseChapterList(chapterJson);
			int totalPage = splitPage(chapterList.getResult().getTotalCount(), PAGE_SIZE);
			for(int i=1;i<=totalPage;i++){
				chapterJson = requestChapterlist(cbid, i, PAGE_SIZE);
				chapterList = parseChapterList(chapterJson);
				System.out.println(chapterList.getResult().getResultData().size());
			}
		}
	}

	private int splitPage(int chapterCount, int pageSize) {
		int pageCount = 1;
		if (chapterCount < pageSize) {
			return pageCount;
		}
		int modular = chapterCount % pageSize;
		pageCount = modular != 0 ? chapterCount / pageSize + 1 : chapterCount / pageCount;
		return pageCount;
	}

	public void requestBookAndChapter(String id) {
		String bookInfoJson = requestBookInfo(id);
		BookInfo bookInfo = parseBookInfo(bookInfoJson);
		if (bookInfo != null) {
			String chapterListJson = requestChapterlist(bookInfo.getResult().getBook().getcBID());
			System.out.println(chapterListJson);
			ChapterList chapterList = parseChapterList(chapterListJson);
			System.out.println("章节数：" + chapterList.getResult().getTotalCount());
			List<String> contentJson = requestContent(chapterList);
			parseContent(contentJson);
		}
	}

	@Deprecated
	private void parseContent1(List<String> contentJson) {
		for (String json : contentJson) {

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			Map<Object, Object> map = null;
			try {
				map = mapper.readValue(json, Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Map.Entry<Object, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "," + entry.getValue());
			}

		}
	}

	private void parseContent(List<String> contentJson) {
		for (String json : contentJson) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			BookContent bookContent = null;
			try {
				bookContent = mapper.readValue(json, BookContent.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TODO 过滤处理
			if ("-1008".equals(bookContent.getReturnCode()))
				;
			if ("0".equals(bookContent.getReturnCode())) {
				System.out.println(bookContent.getResult().getContent().toString());
			}
		}
	}

	private ChapterList parseChapterList(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ChapterList chapterList = null;
		try {
			chapterList = mapper.readValue(json, ChapterList.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO 过滤处理
		if ("-1008".equals(chapterList.getReturnCode()))
			;
		if ("0".equals(chapterList.getReturnCode())) {
			List<ResultData> list = chapterList.getResult().getResultData();
			for (ResultData rd : list) {
				// System.out.println(rd.toString());
			}
			// System.out.println(chapterList.getResult().getTotalCount());
			return chapterList;
		}
		return null;
	}

	private BookInfo parseBookInfo(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		BookInfo bookInfo = null;
		try {
			bookInfo = mapper.readValue(json, BookInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO 过滤处理
		if ("-1008".equals(bookInfo.getReturnCode()))
			;
		if ("0".equals(bookInfo.getReturnCode())) {
			return bookInfo;
		}
		return null;
	}

	private List<String> requestContent(ChapterList chapterList) {
		List<ResultData> list = chapterList.getResult().getResultData();
		List<String> jsonList = new ArrayList<String>();
		for (ResultData rd : list) {
			Map<String, String> params = new HashMap<>();
			params.put("service", URLConsts.QQ_SERVICE);
			params.put("action", URLConsts.QQ_CONTENT);
			params.put("CBID", rd.getcBID());
			params.put("CCID", rd.getcCID());
			params.put("appKey", KeyUtils.qqAppKey());
			params.put("appToken", KeyUtils.qqAppToken());
			String resultJson = null;
			try {
				resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
			} catch (Exception e) {
				for (int i = 0; i < 5; i++) {
					resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
				}
			}
			jsonList.add(resultJson);
		}
		return jsonList;
	}

	private String requestChapterlist(String cbid, int pageNo, int pageSize) {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_CHAPTER_LIST);
		params.put("CBID", cbid);
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		params.put("pageNo", String.valueOf(pageNo));
		params.put("pageSize", String.valueOf(pageSize));
		String resultJson = null;
		try {
			resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
		} catch (Exception e) {
			for (int i = 0; i < 5; i++) {
				resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
			}
		}
		return resultJson;
	}

	private String requestChapterlist(String cbid) {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_CHAPTER_LIST);
		params.put("CBID", cbid);
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		params.put("pageNo", "1");
		params.put("pageSize", "50");
		String resultJson = null;
		try {
			resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
		} catch (Exception e) {
			for (int i = 0; i < 5; i++) {
				resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
			}
		}
		return resultJson;
	}

	private String requestBookInfo(String cbid) {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_BOOK_INFO);
		params.put("CBID", cbid);
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		String resultJson = null;
		try {
			resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
		} catch (Exception e) {
			for (int i = 0; i < 5; i++) {
				resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
			}
		}
		return resultJson;
	}

}
