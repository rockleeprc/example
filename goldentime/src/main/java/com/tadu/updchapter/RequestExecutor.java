package com.tadu.updchapter;

import java.util.HashMap;
import java.util.Map;

import com.tadu.bean.ChapterList;
import com.tadu.constant.URLConsts;
import com.tadu.util.HttpUtils;
import com.tadu.util.KeyUtils;

public class RequestExecutor {

	private RequestExecutor() {
	}

	public static RequestExecutor getInstance() {
		return new RequestExecutor();
	}

	public ChapterList chapterlist(int pageNo, int pageSize, String cBID) {
		ChapterList chapterList = reqChapterList(pageNo, pageSize, cBID);
		
		String returnCode = chapterList.getReturnCode();
		if(!"0".equals(returnCode)){
			return chapterList;
		}
		
		chapterList = reqChapterList(chapterList.getResult().getTotalCount(), pageSize, cBID);
		return chapterList;

	}

	private ChapterList reqChapterList(int pageNo, int pageSize, String cBID) {
		Map<String, String> params = createChapterListParams(pageNo, pageSize, cBID);
		String resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
		ChapterList chapterList = HttpParser.parseChapterList(resultJson);
		return chapterList;
	}

	private Map<String, String> createChapterListParams(int pageNo, int pageSize, String cBID) {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_CHAPTER_LIST);
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		params.put("CBID", cBID);
		params.put("pageNo", String.valueOf(pageNo));
		params.put("pageSize", String.valueOf(pageSize));
		return params;
	}
}
