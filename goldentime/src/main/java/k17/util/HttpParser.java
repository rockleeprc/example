package k17.util;

import java.util.HashMap;
import java.util.Map;

import k17.bean.BookInfo;
import k17.bean.BookListJson;
import net.sf.json.JSONObject;

public class HttpParser {

	public static BookListJson parseBookList(String str) {
		System.out.println("json="+str);
		JSONObject jsonObject = JSONObject.fromObject(str);
		Map<String,Class<?>> classMap = new HashMap<String,Class<?>>();
		classMap.put("content",BookInfo.class);
		BookListJson dto = (BookListJson) JSONObject.toBean(jsonObject, BookListJson.class,classMap);
		return dto;
	}
}
