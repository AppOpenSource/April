package com.abt.price.di;

import android.app.Application;

import com.abt.price.R;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @描述： @GithubApiModule
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Module
public class GithubApiModule {

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
                .baseUrl(application.getString(R.string.api_github))
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    protected GithubApiService provideGitHubService(Retrofit retrofit) {
        return retrofit.create(GithubApiService.class);
    }
}

