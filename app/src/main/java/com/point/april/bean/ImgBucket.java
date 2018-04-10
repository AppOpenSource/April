package com.point.april.bean;

import java.util.List;

public class ImgBucket {
	
	public int count = 0;
	public String bucketName;
	public List<ImgItem> imageList;
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getBucketName() {
		return bucketName;
	}
	
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public List<ImgItem> getImageList() {
		return imageList;
	}
	
	public void setImageList(List<ImgItem> imageList) {
		this.imageList = imageList;
	}

}
