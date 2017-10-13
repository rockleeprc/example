package k17.bean;

import java.util.List;

public class BookListJson {
	private String code;
	private List<BookInfo> content;
	private String hasNext;
	private String msg;

	public List<BookInfo> getContent() {
		return content;
	}

	public void setContent(List<BookInfo> content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHasNext() {
		return hasNext;
	}

	public void setHasNext(String hasNext) {
		this.hasNext = hasNext;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BookListJson [code=" + code + ", content=" + content + ", hasNext=" + hasNext + ", msg=" + msg + "]";
	}

}
