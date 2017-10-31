package com.tadu.updchapter;

import org.apache.commons.lang3.StringUtils;

public class InputYWData {
	private String taduId;
	private String bookName;
	private String taduPartId;
	private String ChapterName;
	private String taduCreateTime;
	private String taduCBID;
	private String taduCCID;
	//阅文接口字段
	private String returnCode;
	private	String cCID;
	private String updateDate;
	private String chapterTitle;
	private String status;
	
	
	

	public String getTaduCBID() {
		return taduCBID;
	}

	public void setTaduCBID(String taduCBID) {
		this.taduCBID = taduCBID;
	}

	public String getTaduCCID() {
		return taduCCID;
	}

	public void setTaduCCID(String taduCCID) {
		this.taduCCID = taduCCID;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getcCID() {
		return cCID;
	}

	public void setcCID(String cCID) {
		this.cCID = cCID;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaduId() {
		return taduId;
	}

	public void setTaduId(String taduId) {
		this.taduId = taduId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getTaduPartId() {
		return taduPartId;
	}

	public void setTaduPartId(String taduPartId) {
		this.taduPartId = taduPartId;
	}

	public String getChapterName() {
		return ChapterName;
	}

	public void setChapterName(String chapterName) {
		ChapterName = chapterName;
	}

	public String getTaduCreateTime() {
		return taduCreateTime;
	}

	public void setTaduCreateTime(String taduCreateTime) {
		this.taduCreateTime = taduCreateTime;
	}

	public String getcBID() {
		return taduCBID;
	}

	public void setcBID(String cBID) {
		this.taduCBID = cBID;
	}

	@Override
	public String toString() {
		return "returnCode=" + returnCode + ",taduId=" + taduId + ", bookName=" + bookName + ", taduPartId=" + taduPartId
				+ ", ChapterName=" + ChapterName + ", taduCreateTime=" + taduCreateTime + ", taduCBID=" + taduCBID
				+ ", taduCCID=" + taduCCID + ", cCID=" + cCID + ", updateDate="
				+ updateDate + ", chapterTitle=" + chapterTitle + ", status=" + (StringUtils.equals(taduCCID, cCID)==true?"Y":"X");
	}



}
