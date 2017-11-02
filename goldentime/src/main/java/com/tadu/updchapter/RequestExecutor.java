package com.tadu.updchapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
		//retry request
		while(chapterList==null){
			chapterList = reqChapterList(pageNo, pageSize, cBID);
		}
		
		//-1008
		String returnCode = chapterList.getReturnCode();
		if (!"0".equals(returnCode)) {
			return chapterList;
		}

		int pageIdx = chapterList.getResult().getTotalCount();
		chapterList = reqChapterList(pageIdx, pageSize, cBID);
		
		//retry request
		while(chapterList==null){
			chapterList = reqChapterList(pageIdx, pageSize, cBID);
		}

		boolean isEmpty = chapterList.getResult().getResultData().isEmpty();
		while (isEmpty) {
			goToSleep();
			pageIdx--;
			chapterList = reqChapterList(pageIdx, pageSize, cBID);
			
			//retry request
			while(chapterList==null){
				chapterList = reqChapterList(pageIdx, pageSize, cBID);
			}
			
			isEmpty = chapterList.getResult().getResultData().isEmpty();
			if (pageIdx < 0) {
				isEmpty = false;
			}
		}

		return chapterList;

	}
	private void goToSleep(){
		Random r = new Random();
		try {
			TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private ChapterList reqChapterList(int pageNo, int pageSize, String cBID) {
		Map<String, String> params = createChapterListParams(pageNo, pageSize, cBID);
		String resultJson = null;
		try {
			resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
		} catch (Exception e) {
			resultJson = HttpUtils.doGet(URLConsts.QQ_URL, params);
		}
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
