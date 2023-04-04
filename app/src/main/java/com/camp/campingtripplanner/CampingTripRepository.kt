package com.camp.campingtripplanner

import kotlinx.coroutines.flow.Flow

interface CampingTripRepository {
    suspend fun insertCampingTrip(campingTrip: CampingTrip)

    fun getAllCampingTrips() : Flow<List<CampingTrip>>
}
