package com.camp.campingtripplanner

import com.camp.campingtripplanner.ui.tripdetail.WeatherData
import retrofit2.Response

class OpenWeatherRepository(private val api: OpenWeatherApi) : WeatherRepository {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherData {
        val weatherData = WeatherData()
        val response = api.getCurrentWeather(latitude, longitude, apiKey)
        if (response.isSuccessful) {
            weatherData.temp = response.body()?.main?.temp ?: 1.0
        }
        return weatherData
    }
}
