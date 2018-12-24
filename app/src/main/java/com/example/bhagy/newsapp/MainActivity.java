package com.example.bhagy.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bhagy.newsapp.apInterface.RemoteInterface;
import com.example.bhagy.newsapp.client.ApiClient;
import com.example.bhagy.newsapp.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private List<Response> responses;
    RemoteInterface remoteInterface;
    GetItems getItems;
    int noOfItem;
    private static int newsId;

    public static int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        remoteInterface = ApiClient.getApiClient().create(RemoteInterface.class);
        Call<List<Integer>> newsCall = remoteInterface.getResponse();


       newsCall.enqueue(new Callback<List<Integer>>() {
           @Override
           public void onResponse(Call<List<Integer>> call, retrofit2.Response<List<Integer>> response) {
               Toast.makeText(MainActivity.this, (response.body()).toString()
                        , Toast.LENGTH_SHORT).show();
                noOfItem = 20;
               if(response.body().size() < 20){
                   noOfItem = response.body().size();
               }
               for(int i = 0;i <=noOfItem;i++){

                   Call<Response> newsCallItems = remoteInterface.getNewsResponse(response.body().get(i));
                   newsCallItems.enqueue(new Callback<Response>() {
                       @Override
                       public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                           Log.d("newsItemss", response.body().getTitle() +  "  " + response.body().getUrl());
                       }

                       @Override
                       public void onFailure(Call<Response> call, Throwable t) {
                           Log.d("newsItemss", "fail");
                       }
                   });
               }
           }

           @Override
           public void onFailure(Call<List<Integer>> call, Throwable t) {

           }
       });
    }
}
