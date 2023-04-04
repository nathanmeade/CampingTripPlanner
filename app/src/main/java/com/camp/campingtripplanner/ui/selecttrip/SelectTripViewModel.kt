package com.camp.campingtripplanner.ui.selecttrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camp.campingtripplanner.CampingTrip
import com.camp.campingtripplanner.CampingTripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SelectTripViewModel @Inject constructor(
    private val tripRepository: CampingTripRepository
) : ViewModel() {

    var campingTrips = listOf<CampingTrip>()

    init {
        getTrips()
    }

    fun getTrips() {
//        return tripRepository.getAllCampingTrips()
//            .flowOn(Dispatchers.IO)
//            .collect {
//                campingTrips = it
//            }
        viewModelScope.launch {
            tripRepository.getAllCampingTrips().collect {
                campingTrips = it
            }
        }
    }
}
