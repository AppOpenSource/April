package com.abt.price.bean.zhihu;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * @描述： @SimpleZhihuBean
 * @作者： @黄卫旗
 * @创建时间： @08/06/2018
 */
public class SimpleZhihuBean {
    public ObservableInt id = new ObservableInt();
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> thumbnail = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableBoolean done = new ObservableBoolean();

    //public ObservableField<String> name = new ObservableField<>();
    //public ObservableBoolean isGood = new ObservableBoolean(); //是否点赞
}
