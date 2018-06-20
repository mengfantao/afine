package com.yufan.library.util;

import android.app.Application;
import android.net.ConnectivityManager;

import com.yufan.library.base.BaseApplication;

/**
 * Created by mengfantao on 18/3/31.
 */

public class YFUtil {
    public static boolean hasInternet() {
        boolean flag;
        flag = ((ConnectivityManager) BaseApplication.getInstance().getSystemService(
                "connectivity")).getActiveNetworkInfo() != null;
        return flag;
    }

}
