package com.camp.campingtripplanner

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "camping_trips")
data class CampingTrip(
    @PrimaryKey val id: Int,
    val name: String,
    val location: String,
    val arrival: String,
    val departure: String,
    val tent: String,
    val bag: String,
    val eggs: String,
    val bacon: String
)
