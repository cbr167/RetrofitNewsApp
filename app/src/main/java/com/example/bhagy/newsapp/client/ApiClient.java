package com.example.bhagy.newsapp.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bhagy on 12/23/2018.
 */

public class ApiClient {

    public  static final String BASE_URL = "https://hacker-news.firebaseio.com/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }


        return retrofit;
    }
}
