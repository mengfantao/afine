package com.yufan.library.manager;

import android.text.TextUtils;


/**
 * Created by mengfantao on 18/4/3.
 */

public class UserManager {
    private String token;
    private String qiNiuToken;
    private String latitude;
    private String longitude;

    private static UserManager instance=new UserManager();
    private UserManager(){

    }
    public static UserManager getInstance(){
        return instance;
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferencesClient.getInstance().saveValue("token",token);
    }


    public String getToken() {
        if(TextUtils.isEmpty(token)){
            token=SharedPreferencesClient.getInstance().getString("token","");
        }
        return token;
    }





    public String getQiNiuToken() {
        return qiNiuToken;
    }

    public void setQiNiuToken(String qiNiuToken) {
        this.qiNiuToken = qiNiuToken;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
