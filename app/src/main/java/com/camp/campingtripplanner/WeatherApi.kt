package com.camp.campingtripplanner

interface WeatherApi {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) : Any

    suspend fun getCurrentWeather(
        apiKey: String,
        latAndLong: String
    ) : Any
}
