package com.tadu.bean;

public class InputData {
	private String taduId;
	private String otherId;

	public InputData(String taduId, String otherId) {
		super();
		this.taduId = taduId;
		this.otherId = otherId;
	}

	public String getTaduId() {
		return taduId;
	}

	public void setTaduId(String taduId) {
		this.taduId = taduId;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	@Override
	public String toString() {
		return "InputData [taduId=" + taduId + ", otherId=" + otherId + "]";
	}

}
