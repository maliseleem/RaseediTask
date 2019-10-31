package com.example.myapplication.api;

import com.example.myapplication.Model.AdsEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface AdsApiService  {
    //api for get ads list
    @GET("get_ad/?solo=false")
    Observable<List<AdsEntity>> getAds();
}
