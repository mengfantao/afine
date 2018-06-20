package com.yufan.library.api;

/**
 * Created by mengfantao on 18/2/27.
 */

public interface IHttpCallBack {
    void onResponse( ApiBean mApiBean);
    void onFailure(int id,Exception e);
}
