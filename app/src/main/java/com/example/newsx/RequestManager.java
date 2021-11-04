package com.example.newsx;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.newsx.Listeners.OnFetchDataListener;
import com.example.newsx.Models.APIStatus;
import com.example.newsx.Models.NewsApiResponse;
import com.example.newsx.Models.NewsHeadline;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getNewsHeadlines(OnFetchDataListener<NewsApiResponse> listener, String category, String query){

        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);

        Call<NewsApiResponse> call = callNewsApi.callHeadlines("us", category, query, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country")String country,
                @Query("category")String category,
                @Query("q")String query,
                @Query("apiKey")String apiKey
        );
    }
}
