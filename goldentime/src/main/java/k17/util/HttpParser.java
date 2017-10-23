package k17.util;

import java.util.HashMap;
import java.util.Map;

import k17.bean.BookChapter;
import k17.bean.BookInfo;
import k17.bean.BookListJson;
import k17.bean.BookVolumnChapterInfo;
import k17.bean.ChapterContentJson;
import k17.bean.VolumnAndChapterListJson;
import net.sf.json.JSONObject;

public class HttpParser {

	public static BookListJson parseBookList(String json) {
		System.out.println("json="+json);
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map<String,Class<?>> classMap = new HashMap<String,Class<?>>();
		classMap.put("content",BookInfo.class);
		BookListJson dto = (BookListJson) JSONObject.toBean(jsonObject, BookListJson.class,classMap);
		return dto;
	}
	
	public static VolumnAndChapterListJson parseVolumnAndChapterList(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);
		Map<String,Class<?>> classMap = new HashMap<String,Class<?>>();
		classMap.put("content",BookVolumnChapterInfo.class);
		classMap.put("chapterList",BookChapter.class);
		VolumnAndChapterListJson dto = (VolumnAndChapterListJson) JSONObject.toBean(jsonObject, VolumnAndChapterListJson.class,classMap);
		return dto;
	}

	public static ChapterContentJson parseChapterContent(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		ChapterContentJson dto = (ChapterContentJson) JSONObject.toBean(jsonObject, ChapterContentJson.class);
		return dto;
	}
}
