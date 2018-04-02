package k17.bean;

import java.util.List;

public class VolumnAndChapterListJson {
	private String code;
	private List<BookVolumnChapterInfo> content;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<BookVolumnChapterInfo> getContent() {
		return content;
	}

	public void setContent(List<BookVolumnChapterInfo> content) {
		this.content = content;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "VolumnAndChapterListJson [code=" + code + ", msg=" + msg + "]";
	}

}
