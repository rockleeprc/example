package com.tadu.bean;


public class BookContent extends ReturnCode {
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public static class Result {
		private Contnt content;

		public Contnt getContent() {
			return content;
		}

		public void setContent(Contnt content) {
			this.content = content;
		}

		public static class Contnt {
			private String cBID;
			private String cCID;
			private String content;

			public String getcBID() {
				return cBID;
			}

			public void setcBID(String cBID) {
				this.cBID = cBID;
			}

			public String getcCID() {
				return cCID;
			}

			public void setcCID(String cCID) {
				this.cCID = cCID;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String content) {
				this.content = content;
			}

			@Override
			public String toString() {
				return "Contnt [cBID=" + cBID + ", cCID=" + cCID + ", content=" + content + "]";
			}
		}
	}
}
