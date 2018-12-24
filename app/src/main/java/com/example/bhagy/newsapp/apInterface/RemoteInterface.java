package com.example.bhagy.newsapp.apInterface;

import com.example.bhagy.newsapp.MainActivity;
import com.example.bhagy.newsapp.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bhagy on 12/23/2018.
 */

public interface RemoteInterface {

    //Method for an api call
    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getResponse();

    @GET("/v0/item/{id}.json?print=pretty")
    Call<Response> getNewsResponse(@Path("id") int newsId);

}
