package com.yufan.library.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


import com.yufan.library.base.BaseApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by mengfantao on 17/4/6.
 * sharedpreferences全局工具
 */

public class SharedPreferencesClient {
    public final static String DATA_APP = "_data_app";
    public final static String DATA_USER = "_data_user";
    public final static String DATA_SETTING = "_data_setting";
    public final static String DATA_COOKIES = "_data_cookies";



    private String DATA = DATA_APP;
    private static SharedPreferencesClient appClient;
    private static SharedPreferencesClient userClient;
    private static SharedPreferencesClient settingClient;
    private static SharedPreferencesClient client;


    public void destroy(){
        appClient=null;
        userClient=null;
        settingClient=null;
        client=null;
    }
    public static SharedPreferencesClient getInstance() {
        return getInstance(DATA_APP);
    }

    public static SharedPreferencesClient getInstance(String data_) {
        switch (data_) {

            case DATA_APP:
                if (appClient == null) {
                    appClient = new SharedPreferencesClient(DATA_APP);
                }

                return appClient;
            case DATA_USER:
                if (userClient == null) {
                 String userid=   SharedPreferencesClient.getInstance().getString("userid","");
                    userClient = new SharedPreferencesClient(DATA_USER + userid);
                }
                return userClient;
            case DATA_SETTING:
                if (settingClient == null) {

                    settingClient = new SharedPreferencesClient(DATA_SETTING);
                }
                return settingClient;
            case DATA_COOKIES:
                if (appClient == null) {
                    appClient = new SharedPreferencesClient(DATA_COOKIES);
                }
                return appClient;
            default:
                client = new SharedPreferencesClient(data_);

                return client;
        }

    }

    private SharedPreferencesClient(String data) {
        DATA = data;
    }

    private SharedPreferences mSharedPreferences;


    public void saveValue(String key, int value) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putInt(key, value);
            editor.commit();
        }


    }


    public void saveValue(String key, List value){
        getSharedPreferences();

        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i=0;i<value.size();i++){
                if(i==0){
                    stringBuffer.append(value.get(0));
                }else{
                    stringBuffer.append(","+value.get(i));
                }
            }
            editor.putString(key, stringBuffer.toString());
            editor.commit();
        }

    }

    public void saveValue(String key, boolean value) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, String value) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, HashSet value) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putStringSet(key, value);
            editor.commit();
        }
    }

    public void saveValue(String key, long value) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putLong(key, value);
            editor.commit();
        }


    }

    public HashSet getStringSet(String key){
        getSharedPreferences();
        HashSet<String> list=new HashSet<>();
        if (mSharedPreferences != null) {
            list = (HashSet) mSharedPreferences.getStringSet(key, new HashSet<String>());
        }
        return list;

    }

    public List getList(String key){
        getSharedPreferences();
        ArrayList list=new ArrayList();
        if (mSharedPreferences != null) {
            String string = mSharedPreferences.getString(key, "");
            if(!TextUtils.isEmpty(string)){
                String[] split=string.split(",");
                for (int i=0;i<split.length;i++){
                    list.add(split[i]);
                }
            }
        }
        return list;

    }
    public String getString(String key, String def) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(key, def);
        }
        return def;
    }

    public boolean getBoolean(String key, Boolean def) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            return mSharedPreferences.getBoolean(key, def);
        }
        return def;
    }

    public int getInt(String key, int def) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            return mSharedPreferences.getInt(key, def);
        }
        return def;
    }

    public long getLong(String key, long def) {
        getSharedPreferences();
        if (mSharedPreferences != null) {
            return mSharedPreferences.getLong(key, def);
        }
        return def;


    }

    private SharedPreferences getSharedPreferences() {
        mSharedPreferences = BaseApplication.getInstance().getSharedPreferences(DATA, Context.MODE_PRIVATE);
        return mSharedPreferences;
    }

}
