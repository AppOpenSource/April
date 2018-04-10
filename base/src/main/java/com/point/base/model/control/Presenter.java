package com.point.base.model.control;

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
