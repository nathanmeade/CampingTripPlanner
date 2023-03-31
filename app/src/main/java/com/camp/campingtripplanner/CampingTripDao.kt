package com.camp.campingtripplanner

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CampingTripDao {

    @Query("SELECT * FROM camping_trips")
    fun getAllCampingTrips() : Flow<List<CampingTrip>>

    @Query("SELECT * FROM camping_trips WHERE id = :id")
    fun getCampingTripById(id: Long) : Flow<CampingTrip?>

    @Insert
    fun insertCampingTrip(campingTrip: CampingTrip)

    @Update
    fun updateCampingTrip(campingTrip: CampingTrip)

    @Delete
    fun deleteCampingTrip(campingTrip: CampingTrip)
}
