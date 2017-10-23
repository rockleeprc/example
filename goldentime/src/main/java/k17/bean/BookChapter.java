package k17.bean;

public class BookChapter {

	private String bookId;
	private String chapterId;
	private String chapterName;
	private String chapterindex;
	private String isVIP;
	private String status;
	private String updateDate;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterindex() {
		return chapterindex;
	}

	public void setChapterindex(String chapterindex) {
		this.chapterindex = chapterindex;
	}

	public String getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(String isVIP) {
		this.isVIP = isVIP;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "BookChapter [bookId=" + bookId + ", chapterId=" + chapterId + ", chapterName=" + chapterName
				+ ", chapterindex=" + chapterindex + ", isVIP=" + isVIP + ", status=" + status + ", updateDate="
				+ updateDate + "]";
	}

	
}
