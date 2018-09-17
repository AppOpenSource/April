package com.abt.price.di.module;

import android.app.Activity;
import android.app.Application;

import com.abt.price.R;
import com.abt.price.api.GankApi;
import com.abt.price.model.gank.GankModelImpl;
import com.abt.price.ui.IGankView;
import com.abt.price.ui.adapter.GankAdapter;
import com.abt.price.ui.fragment.GankFragment;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @描述： @GankApiModule
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Module
public class GankApiModule {

    private IGankView mIGankView;

    public GankApiModule(IGankView iGankView) {
        mIGankView = iGankView;
    }

    @Provides
    public IGankView provideIGankView() {
        return this.mIGankView;
    }

    @Provides
    public GankAdapter provideGankAdapter(Activity context) {
        return new GankAdapter(context);
    }

    @Provides
    public GankFragment provideGankFragment() {
        return new GankFragment();
    }

    @Provides
    GankModelImpl provideGankModelImpl() {
        return new GankModelImpl();
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        //okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    @Provides
    public Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(application.getString(R.string.api_gank))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    protected GankApi provideGitHubService(Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }

}

