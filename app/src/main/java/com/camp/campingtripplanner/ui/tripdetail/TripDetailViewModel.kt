package com.camp.campingtripplanner.ui.tripdetail

import androidx.lifecycle.ViewModel
import com.camp.campingtripplanner.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    @Inject lateinit var apiKey: String
    var _weather : MutableStateFlow<WeatherData> = MutableStateFlow(WeatherData(4.0))
    val weather : StateFlow<WeatherData>
        get() = _weather

    fun getCurrentWeather() {
        val myScope = CoroutineScope(Dispatchers.IO)
        myScope.launch {
            _weather.value = weatherRepository.getCurrentWeather(34.901910,-82.358923, apiKey)
        }
    }
}
