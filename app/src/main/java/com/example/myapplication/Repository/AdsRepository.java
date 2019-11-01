package com.example.myapplication.Repository;

import com.example.myapplication.Model.AdsEntity;
import com.example.myapplication.api.AdsApiService;
import com.example.myapplication.api.Resource;
import com.example.myapplication.api.RxNetworkBoundResource;
import com.example.myapplication.db.AdsDoa;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

@Singleton
public class AdsRepository {
    private AdsDoa adsDoa;
    private AdsApiService adsApiService;

    public AdsRepository(AdsDoa adsDoa, AdsApiService adsApiService) {
        this.adsDoa = adsDoa;
        this.adsApiService = adsApiService;
    }

    public Observable<Resource<List<AdsEntity>>> getData() {
        return new RxNetworkBoundResource<List<AdsEntity>, List<AdsEntity>>() {

            @Override
            protected void saveCallResult(List<AdsEntity> item) {
                adsDoa.insertData(item);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @Override
            protected Flowable<List<AdsEntity>> loadFromDb() {
                List<AdsEntity> adsEntities = adsDoa.getAscendingOrderAds();
                if (adsEntities == null || adsEntities.isEmpty()) {
                    return Flowable.empty();
                }
                return Flowable.just(adsEntities);
            }

            @Override
            protected Observable<List<AdsEntity>> createCall() {
                return adsApiService.getAds()
                        .flatMap(new Function<List<AdsEntity>, ObservableSource<List<AdsEntity>>>() {
                            @Override
                            public ObservableSource<List<AdsEntity>> apply(List<AdsEntity> adsEntities) throws Exception {
                                return Observable.just(adsEntities);
                            }
                        });
            }
        }.asObservable();

    }

}
