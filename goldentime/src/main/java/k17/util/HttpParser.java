package k17.util;

import k17.bean.BookListJson;
import net.sf.json.JSONObject;

public class HttpParser {

	public static BookListJson parseBookList(String str) {
		System.out.println("json="+str);
		JSONObject jsonObject = JSONObject.fromObject(str);
		BookListJson dto = (BookListJson) JSONObject.toBean(jsonObject, BookListJson.class);
		return dto;
	}
}
