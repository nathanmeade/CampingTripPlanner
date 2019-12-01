package com.camp.campingtripplanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ViewTripViewModel extends ViewModel {
    private LiveData<Trip> trip;

    public ViewTripViewModel(AppDatabase appDatabase, int tripId){
        trip = appDatabase.tripDao().findByTid(tripId);
    }

    public LiveData<Trip> getTrip(){
        return trip;
    }
}
