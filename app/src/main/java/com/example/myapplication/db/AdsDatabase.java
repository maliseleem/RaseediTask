package com.example.myapplication.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.Model.AdsEntity;

@Database(entities = {AdsEntity.class}, version = 1, exportSchema = false)
public abstract class AdsDatabase extends RoomDatabase {
    public abstract AdsDoa adsDoa();
}
