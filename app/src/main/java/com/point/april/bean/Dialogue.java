package com.point.april.bean;

public class Dialogue {

	private int role;
	private int user_id;
	private String user_name;
	private String comment_date;
	private String content;
	
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Dialogue [role=" + role + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", comment_date=" + comment_date
				+ ", content=" + content + "]";
	}
}
