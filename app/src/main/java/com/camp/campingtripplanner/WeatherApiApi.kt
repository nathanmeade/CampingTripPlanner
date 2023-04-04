package com.camp.campingtripplanner

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApiApi : WeatherApi {

    @GET("current.json")
    override suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") latitudeAndLongitude: String
    ): Response<WeatherApiResponse>
}
