package com.abt.price.di;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @描述： @GithubApiService
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
public interface GithubApiService {
    @GET("/users/{user}/repos")
    Observable<ArrayList<Repo>> getRepoData(@Path("user") String user);
}