package com.tadu.bean;

import java.util.List;

public class ChapterList extends ReturnCode {
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public static class Result {
		private List<ResultData> resultData;
		private int totalCount;

		public List<ResultData> getResultData() {
			return resultData;
		}

		public void setResultData(List<ResultData> resultData) {
			this.resultData = resultData;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public static class ResultData {
			private String cCID;// 章号
			private String cBID;// 书号
			private String cVID;// 卷号
			private String chaptersort;// 序号
			private String chaptertitle;// 章节名
			private int chaptertype;
			private String content_md5;// 内容 MD5
			private int originalwords;// 计费字数
			private int status;
			private String updatetime;
			/*
			 * 章节是否为收费章节 Vip 为收费，非 vip 不收费
			 */
			private int vipflag;// VIP 类型:-1:非 vip 1:vip
			private int amount;// 价格

			@Override
			public String toString() {
				return "ResultData [cCID=" + cCID + ", cBID=" + cBID + ", cVID=" + cVID + ", chaptersort=" + chaptersort
						+ ", chaptertitle=" + chaptertitle + ", chaptertype=" + chaptertype + ", content_md5="
						+ content_md5 + ", originalwords=" + originalwords + ", status=" + status + ", updatetime="
						+ updatetime + ", vipflag=" + vipflag + ", amount=" + amount + "]";
			}

			public String getcCID() {
				return cCID;
			}

			public void setcCID(String cCID) {
				this.cCID = cCID;
			}

			public String getcBID() {
				return cBID;
			}

			public void setcBID(String cBID) {
				this.cBID = cBID;
			}

			public String getcVID() {
				return cVID;
			}

			public void setcVID(String cVID) {
				this.cVID = cVID;
			}

			public String getChaptersort() {
				return chaptersort;
			}

			public void setChaptersort(String chaptersort) {
				this.chaptersort = chaptersort;
			}

			public String getChaptertitle() {
				return chaptertitle;
			}

			public void setChaptertitle(String chaptertitle) {
				this.chaptertitle = chaptertitle;
			}

			public int getChaptertype() {
				return chaptertype;
			}

			public void setChaptertype(int chaptertype) {
				this.chaptertype = chaptertype;
			}

			public String getContent_md5() {
				return content_md5;
			}

			public void setContent_md5(String content_md5) {
				this.content_md5 = content_md5;
			}

			public int getOriginalwords() {
				return originalwords;
			}

			public void setOriginalwords(int originalwords) {
				this.originalwords = originalwords;
			}

			public int getStatus() {
				return status;
			}

			public void setStatus(int status) {
				this.status = status;
			}

			public String getUpdatetime() {
				return updatetime;
			}

			public void setUpdatetime(String updatetime) {
				this.updatetime = updatetime;
			}

			public int getVipflag() {
				return vipflag;
			}

			public void setVipflag(int vipflag) {
				this.vipflag = vipflag;
			}

			public int getAmount() {
				return amount;
			}

			public void setAmount(int amount) {
				this.amount = amount;
			}
		}
	}
}
