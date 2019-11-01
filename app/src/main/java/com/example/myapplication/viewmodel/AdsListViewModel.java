package com.example.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.AdsEntity;
import com.example.myapplication.Repository.AdsRepository;
import com.example.myapplication.api.AdsApiService;
import com.example.myapplication.api.Resource;
import com.example.myapplication.db.AdsDoa;

import java.util.List;

import javax.inject.Inject;

public class AdsListViewModel extends ViewModel {

    private AdsRepository movieRepository;

    /* We are using LiveData to update the UI with the data changes.
     */
    private MutableLiveData<Resource<List<AdsEntity>>> adsLiveData = new MutableLiveData<>();


    /*
     * We are injecting the AdsDao class
     * and the AdsApiService class to the ViewModel.
     * */

    @Inject
    public AdsListViewModel(AdsDoa adsDao, AdsApiService adsApiService) {
        /* You can see we are initialising the AdsRepository class here */
        movieRepository = new AdsRepository(adsDao, adsApiService);
    }


    /*
     * Method called by UI to fetch ads list
     * */
    public void loadads() {
        movieRepository.getData()
                .subscribe(resource -> getLiveData().postValue(resource));
    }


    /*
     * LiveData observed by the UI
     * */
    public MutableLiveData<Resource<List<AdsEntity>>> getLiveData() {
        return adsLiveData;
    }
}