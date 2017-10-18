package k17.bean;

public class BookInfo {

	private String authorPenname;
	private String bookId;
	private String bookName;
	private String bookStatus;
	private String categoryId;
	private String categoryName;
	private String channelName;
	private String coverImageUrl;
	private String introduction;
	private String lastUpdateChapterDate;
	private String status;
	private String wordCount;

	public String getAuthorPenname() {
		return authorPenname;
	}

	public void setAuthorPenname(String authorPenname) {
		this.authorPenname = authorPenname;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLastUpdateChapterDate() {
		return lastUpdateChapterDate;
	}

	public void setLastUpdateChapterDate(String lastUpdateChapterDate) {
		this.lastUpdateChapterDate = lastUpdateChapterDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	@Override
	public String toString() {
		return "BookInfo [authorPenname=" + authorPenname + ", bookId=" + bookId + ", bookName=" + bookName
				+ ", bookStatus=" + bookStatus + ", categoryId=" + categoryId + ", categoryName=" + categoryName
				+ ", channelName=" + channelName + ", coverImageUrl=" + coverImageUrl + ", introduction=" + introduction
				+ ", lastUpdateChapterDate=" + lastUpdateChapterDate + ", status=" + status + ", wordCount=" + wordCount
				+ "]";
	}
	
	

}
