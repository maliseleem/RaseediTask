package com.example.myapplication;


import android.app.Application;

import com.example.myapplication.api.ApiModule;
import com.example.myapplication.db.DbModule;
import com.example.myapplication.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        ApiModule.class,
        DbModule.class,
        ViewModelModule.class,
        ActivityModulew.class,
        AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(task appController);

}
