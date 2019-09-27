package com.example.campingtripplanner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

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
