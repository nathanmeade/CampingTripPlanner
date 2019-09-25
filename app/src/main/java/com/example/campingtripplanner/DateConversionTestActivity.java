package com.example.campingtripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateConversionTestActivity extends AppCompatActivity {

    private AppDatabase db;
    private Trip trip;
    private boolean approachingTrip;
    private boolean tripsSaved;
    private long diffInMillies;
    private long diffInMillies2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_conversion_test);
        diffInMillies = 0;
        diffInMillies2 = 0;
        approachingTrip = false;
        tripsSaved = false;
        Date date = Calendar.getInstance().getTime();
        Date d = new Date();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        List<Trip> trips = db.tripDao().getAll();
        for (Trip trip : trips){
            /*trip.arrival*/
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                d = sdf.parse(trip.arrival);
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            if (d.after(date)){
                Log.d("nathanTest", "after " + trip.arrival + " " + d.compareTo(date));
                tripsSaved = true;
                /*approachingTrip = true;*/
                if (diffInMillies2 == 0){
                    diffInMillies2 = Math.abs(d.getTime() - date.getTime());
                }
                else if ((Math.abs(d.getTime() - date.getTime())) < diffInMillies2){
                    diffInMillies2 = Math.abs(d.getTime() - date.getTime());
                }
            }
            else {
                Log.d("nathanTest", "before" + trip.arrival);
                tripsSaved = true;
                if (diffInMillies == 0){
                    diffInMillies = Math.abs(date.getTime() - d.getTime());
                }
                else if ((Math.abs(date.getTime() - d.getTime())) < diffInMillies){
                    diffInMillies = Math.abs(date.getTime() - d.getTime());
                }
            }
        }
        if (!tripsSaved){
            //no trips saved displayed
            Log.d("nathanTest", "no trips saved");
        }
        else if (!approachingTrip){
            //"it's been " (positive difference in days between closest camping trip) " days since your last camping trip!
            //^shouldn't this actually be for departure?
            Log.d("nathanTest", "no approaching trips" + (diffInMillies / (1000*60*60*24)));
        }
        else {
            //"You have " (days until closest camping trip) " until your next camping trip!
            /*Log.d("nathanTest", "approaching trip" + diffInMillies2);*/
            Log.d("nathanTest", "approaching trip" + (diffInMillies2 / (1000*60*60*24)));
        }
    }
}
