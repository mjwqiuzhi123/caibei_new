package com.borrow.supermarket.response.result;

public class GetsaveOrderDTOResponse {
	private String identifier;
	private String lendUrl;
	private int dayTotalApply;

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLendUrl() {
		return this.lendUrl;
	}

	public void setLendUrl(String lendUrl) {
		this.lendUrl = lendUrl;
	}

	public GetsaveOrderDTOResponse(String identifier, String lendUrl) {
		this.identifier = identifier;
		this.lendUrl = lendUrl;
	}

	public GetsaveOrderDTOResponse() {
	}

	public int getDayTotalApply() {
		return dayTotalApply;
	}

	public void setDayTotalApply(int dayTotalApply) {
		this.dayTotalApply = dayTotalApply;
	}
}