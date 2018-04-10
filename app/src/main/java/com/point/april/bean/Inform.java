package com.point.april.bean;

import java.io.Serializable;
import java.util.List;

public class Inform implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String user_pic;
	private String user_name;
	private int user_id;
	private int inform_id;
	private String theme;
	private String detail;
	private int msg_type;
	private List<String> pics;
	private int count; // 点赞数
	private String up_ornot;
	private long date;
	
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getInform_id() {
		return inform_id;
	}
	public void setInform_id(int inform_id) {
		this.inform_id = inform_id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUp_ornot() {
		return up_ornot;
	}
	public void setUp_ornot(String up_ornot) {
		this.up_ornot = up_ornot;
	}
	
}
