package com.example.campingtripplanner;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {
    @Query("SELECT * FROM trip")
    List<Trip> getAll();

    @Query("SELECT * FROM trip WHERE tid IN (:tripIds)")
    List<Trip> loadAllByIds(int[] tripIds);

    @Query("SELECT * FROM trip WHERE name LIKE :name LIMIT 1")
    Trip findByName(String name);

    @Query("SELECT * FROM trip WHERE tid=:tid LIMIT 1")
    Trip findByTid(int tid);

    @Insert
    void insertAll(Trip... trips);

    @Delete
    void delete(Trip trip);

    @Query("delete from trip")
    void deleteAll();

    @Query("delete from trip where tid=:tid")
    void deleteByTid(int tid);
}