package com.example.campingtripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class SelectTripActivity extends AppCompatActivity implements TripListAdapter.TripListAdapterOnClickHandler {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TripListAdapter.TripListAdapterOnClickHandler recyclerAdapterOnClickHandler;

    private Context context;

    private Trip trip1;
    private List<Trip> trips;
    public AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapterOnClickHandler = this;
        context = this;
/*        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, getString(R.string.database_name)).allowMainThreadQueries().build();*/
        db = AppDatabase.getInstance(getApplicationContext());
        SelectTripViewModelFactory selectTripViewModelFactory = new SelectTripViewModelFactory(db);
        SelectTripViewModel selectTripViewModel = ViewModelProviders.of(this, selectTripViewModelFactory).get(SelectTripViewModel.class);
        selectTripViewModel.getTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                setRecyclerView(trips);
            }
        });
        /*trips = db.tripDao().getAll();*/


    }

    public void setRecyclerView(List<Trip> trips){
        String[] myDataset2 = new String[trips.size()];
        int[] myDataset3 = new int[trips.size()];
        String[] myDataset4 = new String[trips.size()];
        for (int i=0; i<trips.size(); i++){
            myDataset2[i] = trips.get(i).name;
            myDataset3[i] = trips.get(i).tid;
            myDataset4[i] = trips.get(i).arrival + " - " + trips.get(i).departure;
        }
        mAdapter = new TripListAdapter(myDataset2, myDataset3, myDataset4, recyclerAdapterOnClickHandler);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(String name, int tid, int adapterPosition) {
        Intent intent = new Intent(this, ViewTripActivity.class);
        intent.putExtra(getString(R.string.tid), tid);
        startActivity(intent);
    }
}
