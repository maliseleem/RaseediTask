package com.example.myapplication.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.Model.AdsEntity;

import java.util.List;

@Dao
public interface AdsDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertData(List<AdsEntity> ads);

    @Query("SELECT * FROM `AdsEntity` ORDER BY `order` ASC ")
    List<AdsEntity> getAscendingOrderAds();
}
