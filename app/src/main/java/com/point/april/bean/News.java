package com.point.april.bean;

import java.util.List;

public class News {
	private int id;
	private int status;
	private int type;
	private int problem_type;
	private int warranty_type;
	private long date;
	private String description;
	private int count;
	private List<Dialogue> dialogues;
	private List<String> pics;
	
	private String phone;
	private String address;
	private int user_id;
	private String user_name;
	private String tag;
	private int is_read = 0;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getProblem_type() {
		return problem_type;
	}
	public void setProblem_type(int problem_type) {
		this.problem_type = problem_type;
	}
	public int getWarranty_type() {
		return warranty_type;
	}
	public void setWarranty_type(int warranty_type) {
		this.warranty_type = warranty_type;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	public List<Dialogue> getDialogues() {
		return dialogues;
	}
	public void setDialogues(List<Dialogue> dialogues) {
		this.dialogues = dialogues;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getIsRead() {
		return is_read;
	}
	public void setIsRead(int is_read) {
		this.is_read = is_read;
	}
}
