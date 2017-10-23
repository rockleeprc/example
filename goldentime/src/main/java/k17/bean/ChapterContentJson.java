package k17.bean;

public class ChapterContentJson {
	private String code;
	private String msg;
	private ChapterContent content;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ChapterContent getContent() {
		return content;
	}
	public void setContent(ChapterContent content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ChapterContentJson [code=" + code + ", msg=" + msg + ", content=" + content + "]";
	}
	
	
}
