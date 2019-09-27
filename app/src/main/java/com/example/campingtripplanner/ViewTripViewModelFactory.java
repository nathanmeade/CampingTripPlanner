package com.example.campingtripplanner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewTripViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    final private AppDatabase mDb;
    final private int mTripId;

    public ViewTripViewModelFactory(AppDatabase appDatabase, int tripId){
        mDb = appDatabase;
        mTripId = tripId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewTripViewModel(mDb, mTripId);
    }
}
