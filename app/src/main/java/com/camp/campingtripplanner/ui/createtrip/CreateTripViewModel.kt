package com.camp.campingtripplanner.ui.createtrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camp.campingtripplanner.CampingTrip
import com.camp.campingtripplanner.CampingTripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(private val tripRepository: CampingTripRepository) :
    ViewModel() {
    fun createTrip(onTripCreated: (Int) -> Unit) {
        val campingTrip = CampingTrip(
            5,
            "aosdhf",
            "aodgh",
            "aodfgh",
            "aodgh",
            "",
            "",
            "",
            ""
        )
        viewModelScope.launch(Dispatchers.IO) {
            tripRepository.insertCampingTrip(campingTrip)
        }
        onTripCreated(5)
    }

}
