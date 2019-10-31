package com.example.myapplication.db;


import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.annotations.NonNull;

@Module
public class DbModule {
    /*
     * The method returns the AdsDatabase object
     * */
    @Provides
    @Singleton
    AdsDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AdsDatabase.class, "Ads.db")
                .allowMainThreadQueries().build();
    }
    @Provides
    @Singleton
    AdsDoa provideAdsDao(@NonNull AdsDatabase appDatabase) {
        return appDatabase.adsDoa();
    }
}
