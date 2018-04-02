package com.tadu.updchapter;


import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tadu.bean.ChapterList;

public class HttpParser {

	public static  ChapterList parseChapterList(String json){
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
		return chapterList;
	}
}
