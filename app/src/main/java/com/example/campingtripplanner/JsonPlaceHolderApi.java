package com.example.campingtripplanner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("data/2.5/weather")
    Call<Weather> getWeather(@Query("lat") String latitude, @Query("lon") String longitude, @Query("appid") String appid);
}
