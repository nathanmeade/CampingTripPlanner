package com.example.campingtripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewTripActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        Intent intent = getIntent();
        int tid = intent.getIntExtra("tid", 0);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        Trip trip = db.tripDao().findByTid(tid);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView dateRangeTextView = findViewById(R.id.dateRangeTextView);
        TextView tentTextView = findViewById(R.id.tentTextView);
        TextView bagTextView = findViewById(R.id.sleepingBagTextView);
        TextView eggsTextView = findViewById(R.id.eggsTextView);
        TextView baconTextView = findViewById(R.id.baconTextView);

        nameTextView.setText(trip.name);
    }
}
