package com.camp.campingtripplanner

import android.util.Log
import com.camp.campingtripplanner.ui.tripdetail.WeatherData

class WeatherApiRepository(private val api: WeatherApiApi) : WeatherRepository {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherData {
        var weatherData = WeatherData()
        val response = api.getCurrentWeather(apiKey, "$latitude,$longitude")
        if (response.isSuccessful) {
            weatherData.temp = response.body()?.current?.temp_f ?: 0.0
        } else {
            Log.d("nathanTest", "response.errorBody(): ${response.errorBody()}")
            Log.d("nathanTest", "response.errorBody(): ${response.raw()}")
            weatherData.temp = 3.4
        }
        return weatherData
    }
}
