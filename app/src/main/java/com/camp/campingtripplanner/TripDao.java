package com.camp.campingtripplanner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {
    @Query("SELECT * FROM trip")
    LiveData<List<Trip>> getAll();

    @Query("SELECT * FROM trip")
    List<Trip> widgetGetAll();

    @Query("SELECT * FROM trip WHERE tid IN (:tripIds)")
    LiveData<List<Trip>> loadAllByIds(int[] tripIds);

    @Query("SELECT * FROM trip WHERE name LIKE :name LIMIT 1")
    LiveData<Trip> findByName(String name);

    @Query("SELECT * FROM trip WHERE tid=:tid LIMIT 1")
    LiveData<Trip> findByTid(int tid);

    @Insert
    void insertAll(Trip... trips);

    @Delete
    void delete(Trip trip);

    @Query("delete from trip")
    void deleteAll();

    @Query("delete from trip where tid=:tid")
    void deleteByTid(int tid);
}