package com.camp.campingtripplanner.ui.tripdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camp.campingtripplanner.CampingTrip
import com.camp.campingtripplanner.CampingTripRepository
import com.camp.campingtripplanner.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val campingTripRepository: CampingTripRepository
    ): ViewModel() {
    @Inject lateinit var apiKey: String
    var _weather : MutableStateFlow<WeatherData> = MutableStateFlow(WeatherData(4.0))
    val weather : StateFlow<WeatherData>
        get() = _weather

    var trip = CampingTrip(
        98,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    fun getCurrentWeather() {
        val myScope = CoroutineScope(Dispatchers.IO)
        myScope.launch {
            _weather.value = weatherRepository.getCurrentWeather(34.901910,-82.358923, apiKey)
        }
    }

    var campingTrips = listOf<CampingTrip>()

    fun getTrip() {
//        return tripRepository.getAllCampingTrips()
//            .flowOn(Dispatchers.IO)
//            .collect {
//                campingTrips = it
//            }
        viewModelScope.launch {
            campingTripRepository.getCampingTripById(5).collect {
                it?.let {
                    trip = it
                }
            }
        }
    }
}
