package com.point.april.presenter;

public interface INewsRecordPresenter {
	void getLatestNews();

	void getMoreNews();

	void delRecord(int recordId, int type);
}
