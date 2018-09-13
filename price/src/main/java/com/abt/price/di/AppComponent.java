package com.abt.price.di;

import com.abt.price.ui.fragment.GankFragment;

import dagger.Component;

/**
 * @描述： @AppComponent
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Component(modules = { AppModule.class, GithubApiModule.class})
public interface AppComponent {
    // inject what
    void inject(GankFragment gankFragment);
}
