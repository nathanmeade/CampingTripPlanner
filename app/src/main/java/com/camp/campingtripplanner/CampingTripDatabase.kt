package com.camp.campingtripplanner

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CampingTrip::class], version = 1)
abstract class CampingTripDatabase : RoomDatabase(){
    companion object {
        private const val DB_NAME = "trip_db"

        @Volatile
        private var INSTANCE : CampingTripDatabase? = null

        fun getInstance(context: Context): CampingTripDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CampingTripDatabase::class.java, DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }

}
