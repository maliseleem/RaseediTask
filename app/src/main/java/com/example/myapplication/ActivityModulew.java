package com.example.myapplication;


import com.example.myapplication.View.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModulew {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}
