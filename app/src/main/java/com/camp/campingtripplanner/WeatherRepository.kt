package com.camp.campingtripplanner

import com.camp.campingtripplanner.ui.tripdetail.WeatherData

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) : WeatherData
}
