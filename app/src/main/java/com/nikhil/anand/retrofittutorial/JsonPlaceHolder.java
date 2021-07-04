package com.nikhil.anand.retrofittutorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
* Api Key : 0a5d17752d9f4ee29370cf0337956531
 */
public interface JsonPlaceHolder {

    @GET("continents")
    Call<List<Post>> getPosts();

    @GET("everything")
    Call<News> getNews(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );
}
