package com.camp.campingtripplanner

import kotlinx.coroutines.flow.Flow

class RoomCampingTripRepository(private val dao: CampingTripDao) : CampingTripRepository {
    override fun getAllCampingTrips() : Flow<List<CampingTrip>> {
        return dao.getAllCampingTrips()
    }

    override fun getCampingTripById(id: Long) : Flow<CampingTrip?> {
        return dao.getCampingTripById(id)
    }

    override suspend fun insertCampingTrip(campingTrip: CampingTrip) {
        dao.insertCampingTrip(campingTrip)
    }

    fun updateCampingTrip(campingTrip: CampingTrip) {
        dao.updateCampingTrip(campingTrip)
    }

    fun deleteCampingTrip(campingTrip: CampingTrip) {
        dao.deleteCampingTrip(campingTrip)
    }
}
