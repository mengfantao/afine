package com.youximao.anew.myapplication;

import android.util.Log;

import com.yufan.library.base.BaseApplication;

/**
 * Created by mengfantao on 18/2/2.
 */

public class App extends BaseApplication {
    private static App instance;
//    //TODO 申请第三方开发者账号
//    {
//        PlatformConfig.setWeixin("weixinid", "weixinsecret");
//        PlatformConfig.setQQZone("qqid", "qqsecret");
//        PlatformConfig.setSinaWeibo("xinlangid", "xinlangsecret", "xinlanghuidiao");
//    }
    public static App getApp(){
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        /**
         * 防止第三方服务多次执行．
         */
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
            Log.e("service", "enter the service process!");
            return;
        }


    }




}
