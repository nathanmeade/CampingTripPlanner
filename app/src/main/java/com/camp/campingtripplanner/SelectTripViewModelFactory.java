package com.camp.campingtripplanner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SelectTripViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    final private AppDatabase mAppDatabase;

    public SelectTripViewModelFactory(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SelectTripViewModel(mAppDatabase);
    }
}
