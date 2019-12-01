package com.camp.campingtripplanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SelectTripViewModel extends ViewModel {
    private LiveData<List<Trip>> trips;

    public SelectTripViewModel(AppDatabase appDatabase){
        trips = appDatabase.tripDao().getAll();
    }

    public LiveData<List<Trip>> getTrips(){
        return trips;
    }
}
