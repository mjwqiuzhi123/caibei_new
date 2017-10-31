package com.borrow.supermarket.request.dto;

import java.util.List;

public class HomeMessDisDTO<T> {
	private String pattern;
	private String title;
	private List<T> productList;
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the productList
	 */
	public List<T> getProductList() {
		return productList;
	}
	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<T> productList) {
		this.productList = productList;
	}
	
	
}
