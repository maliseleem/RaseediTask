package com.example.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(primaryKeys = ("picture"))
public class AdsEntity implements Parcelable {

    @SerializedName("picture")
    @Expose
    @NonNull
    private String picture;
    @Expose
    private boolean solo;
    @Expose
    private String url;
    @Expose
    private String title;
    @Expose
    private String action;
    @Expose
    private boolean sucess;
    @Expose
    private int order;


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isSolo() {
        return solo;
    }

    public void setSolo(boolean solo) {
        this.solo = solo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    protected AdsEntity(Parcel in) {
        this.picture = in.readString();
        this.url = in.readString();
        this.title = in.readString();
        this.action = in.readString();
        this.order = in.readInt();
    }

    public static final Creator<AdsEntity> CREATOR = new Creator<AdsEntity>() {
        @Override
        public AdsEntity createFromParcel(Parcel in) {
            return new AdsEntity(in);
        }

        @Override
        public AdsEntity[] newArray(int size) {
            return new AdsEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public AdsEntity() {
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.picture);
        dest.writeValue(this.url);
        dest.writeValue(this.title);
        dest.writeValue(this.solo);
        dest.writeValue(this.order);
        dest.writeValue(this.action);
    }
}
