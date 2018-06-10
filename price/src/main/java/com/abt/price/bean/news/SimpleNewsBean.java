package com.abt.price.bean.news;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * @描述： @SimpleNewsBean
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SimpleNewsBean {
    public ObservableInt color = new ObservableInt();
    public ObservableField<String> thumbnail = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableInt id = new ObservableInt();
    public ObservableField<String> name = new ObservableField<>();
    public ObservableBoolean isGood = new ObservableBoolean(); //是否点赞
}
