package k17.bean;

public class ChapterContent {
	private String chapterContent;
	private String isRecommend;
	private String isVIP;

	public String getChapterContent() {
		return chapterContent;
	}

	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(String isVIP) {
		this.isVIP = isVIP;
	}

	@Override
	public String toString() {
		return "ChapterContent [chapterContent=" + chapterContent + ", isRecommend=" + isRecommend + ", isVIP=" + isVIP
				+ "]";
	}

}
