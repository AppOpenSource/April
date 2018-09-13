package com.abt.price.di;

import com.abt.price.ui.fragment.GankFragment;

import dagger.Component;

/**
 * @描述： @MainComponent
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Component(modules = MainModule.class)  // 作为桥梁，沟通调用者和依赖对象库
public interface MainComponent {

    //定义注入的方法
    void inject(GankFragment fragment);

}
