package com.example.myapplication;


import android.app.Application;

import com.example.myapplication.api.ApiModule;
import com.example.myapplication.viewmodel.ViewModelModule;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import io.reactivex.annotations.NonNull;

@Module(includes = {AndroidInjectionModule.class, ApiModule.class, ViewModelModule.class})
public class PicassoModule {
    /*
     * The method returns the picasso object
     * */
    @Provides
    @Singleton
    Picasso picasso(Application app, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(app.getApplicationContext())
                .downloader(okHttp3Downloader)
                .loggingEnabled(true)
                .build();
    }
}
