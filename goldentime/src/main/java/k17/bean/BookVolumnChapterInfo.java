package k17.bean;

import java.util.List;

public class BookVolumnChapterInfo {
	private String bookId;
	private String volumeId;
	private String volumeName;
	private List<BookChapter> chapterList;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public List<BookChapter> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<BookChapter> chapterList) {
		this.chapterList = chapterList;
	}

	@Override
	public String toString() {
		return "BookVolumnChapterInfo [bookId=" + bookId + ", volumeId=" + volumeId + ", volumeName=" + volumeName
				+ "]";
	}

	
}
