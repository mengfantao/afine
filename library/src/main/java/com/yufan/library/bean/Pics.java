package com.yufan.library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import static com.yufan.library.bean.DataSource.CLOUD_DOMIN;

/**
 * Created by wangyu on 2018/4/8.
 */

public class Pics implements Parcelable{

    private String id;

    private String user;

    private String furUrl;

    private String thumFurUrl;

    private String thumUrl;

    private String red;

    private String fire;

    private String url;

    private String photoStatus;

    public Pics() {

    }


    protected Pics(Parcel in) {
        id = in.readString();
        user = in.readString();
        furUrl = in.readString();
        thumFurUrl = in.readString();
        thumUrl = in.readString();
        red = in.readString();
        fire = in.readString();
        url = in.readString();
        photoStatus = in.readString();
    }

    public static final Creator<Pics> CREATOR = new Creator<Pics>() {
        @Override
        public Pics createFromParcel(Parcel in) {
            return new Pics(in);
        }

        @Override
        public Pics[] newArray(int size) {
            return new Pics[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFurUrl() {
        return furUrl;
    }

    public void setFurUrl(String furUrl) {
        this.furUrl = furUrl;
    }

    public String getThumFurUrl() {
        return thumFurUrl;
    }

    public void setThumFurUrl(String thumFurUrl) {
        this.thumFurUrl = thumFurUrl;
    }

    public String getThumUrl() {
        return thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    public String getUrl() {
        return CLOUD_DOMIN + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoStatus() {
        return photoStatus;
    }

    public void setPhotoStatus(String photoStatus) {
        this.photoStatus = photoStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(user);
        dest.writeString(furUrl);
        dest.writeString(thumFurUrl);
        dest.writeString(thumUrl);
        dest.writeString(red);
        dest.writeString(fire);
        dest.writeString(url);
        dest.writeString(photoStatus);
    }
}
