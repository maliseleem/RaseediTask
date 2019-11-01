package com.example.myapplication.View;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.AdsEntity;
import com.example.myapplication.R;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.MyViewHolder> {


    Picasso picasso;
    private List<AdsEntity> adsEntities;
    private PublishSubject<AdsEntity> mViewClickSubject = PublishSubject.create();

    public Observable<AdsEntity> getViewClickedObservable() {
        return mViewClickSubject.asObservable();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            imageView = view.findViewById(R.id.image);
        }
    }


    public AdsAdapter(List<AdsEntity> adsEntities, Picasso picasso) {
        this.adsEntities = adsEntities;
        this.picasso = picasso;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ads_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AdsEntity adsEntity = adsEntities.get(position);
        holder.title.setText(adsEntity.getTitle());
        RxView.clicks(holder.itemView)
                .map(aVoid -> adsEntity)
                .subscribe(mViewClickSubject);
        picasso.load(adsEntity.getPicture()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return adsEntities.size();
    }
}