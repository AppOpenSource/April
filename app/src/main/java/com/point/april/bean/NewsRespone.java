package com.point.april.bean;

import java.util.List;

public class NewsRespone {
	
	private String success_ornot;
	private List<News> warranties;
	
	public String getSuccess_ornot() {
		return success_ornot;
	}
	public void setSuccess_ornot(String success_ornot) {
		this.success_ornot = success_ornot;
	}
	public List<News> getNews() {
		return warranties;
	}
	public void setNews(List<News> warranties) {
		this.warranties = warranties;
	}
}
