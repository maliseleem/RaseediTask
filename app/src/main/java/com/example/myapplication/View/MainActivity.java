package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.Status;
import com.example.myapplication.viewmodel.AdsListViewModel;
import com.example.myapplication.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;
    private AdsListViewModel adsListViewModel;
    RecyclerView recyclerView;
    AdsAdapter adsAdapter;
    TextView load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = findViewById(R.id.load);
        recyclerView = findViewById(R.id.adsrecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        initialiseViewModel();
    }
    private void initialiseViewModel() {
        adsListViewModel = ViewModelProviders.of(this, viewModelFactory).get(AdsListViewModel.class);
        adsListViewModel.getLiveData().observe(this, resource -> {
            if(resource.status == Status.LOADING) {
                displayLoader();
            } else if(!resource.data.isEmpty()) {
                load.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                AdsAdapter adsAdapter =  new AdsAdapter(resource.data);
                recyclerView.setAdapter(adsAdapter);
            } else {
                load.setText("error");
                load.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });

        adsListViewModel.loadads();
    }

    private void displayLoader() {
        recyclerView.setVisibility(View.GONE);
        load.setVisibility(View.VISIBLE);
        load.setText("loading");

    }
}
