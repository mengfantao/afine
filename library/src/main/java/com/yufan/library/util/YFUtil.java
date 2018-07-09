package com.yufan.library.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.yufan.library.base.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * Created by mengfantao on 18/3/31.
 */

public class YFUtil {


    public static int getVersionCode(String packageName) {
        int versionCode = 0;
        try {
            versionCode = BaseApplication.getInstance().getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    public static String getVersionName() {
        String name = "";
        try {
            name = BaseApplication
                    .getInstance()
                    .getPackageManager()
                    .getPackageInfo(BaseApplication.getInstance().getPackageName(),
                            0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }


    //获取ｃｐｕ信息
    public static String getCpuName(){

        try{
            FileReader fr= new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+",2);
            for(int i = 0; i < array.length; i++){
            }
            fr.close();
            return array[1];
        }catch (Exception e){

            e.printStackTrace();
        }
        return "";
    }


    public static  String getCpuProduct(){
        return   getprop("ro.product.cpu.abi", "");
    }
    @SuppressLint("WrongConstant")
    public static boolean hasInternet() {
        boolean flag;
        flag = ((ConnectivityManager) BaseApplication.getInstance().getSystemService(
                "connectivity")).getActiveNetworkInfo() != null;
        return flag;
    }
    /**
     * 获取系统指定属性值
     * @param key
     * @param defaultValue
     * @return 调用方法 getprop("ro.hardware","")
     */
    public static String getprop(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, "unknown"));
        } catch (Exception e) {
//            Log.d("OSUtil", "get property error, " + e.getMessage());
        }
//        Log.d("OSUtil", "get property, " + key + " = " + value);
        return value;
    }

    public static String getIMEI() {
        String reslut="";
        try {
            TelephonyManager tel = (TelephonyManager) BaseApplication.getInstance()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if(tel!=null){
                String deviceId = tel.getDeviceId();
                if(!TextUtils.isEmpty(deviceId)){
                    reslut= URLEncoder.encode(deviceId,"utf-8");
                }
            }



        } catch (Exception e) {

            e.printStackTrace();
        }
        return reslut;
    }
}
