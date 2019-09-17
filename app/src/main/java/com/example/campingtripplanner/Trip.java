package com.example.campingtripplanner;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Trip {
    @PrimaryKey
    public int tid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "arrival")
    public String arrival;

    @ColumnInfo(name = "departure")
    public String departure;

    @ColumnInfo(name = "tent")
    public String tent;

    @ColumnInfo(name = "bag")
    public String bag;

    @ColumnInfo(name = "eggs")
    public String eggs;

    @ColumnInfo(name = "bacon")
    public String bacon;
}
