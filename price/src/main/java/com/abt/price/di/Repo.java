package com.abt.price.di;

import android.util.Log;

import javax.inject.Inject;

/**
 * @描述： @Repo
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
public class Repo {

    public String name; // 库的名字
    public String description; // 描述
    public String language; // 语言
//  public String testNullField; // 试错

    @Inject  // 添加注解关键字
    public Repo() {
        Log.i("dagger", "person create!!!");
    }

}
