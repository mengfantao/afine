package com.yufan.library.api;

/**
 * Created by wangyu on 2018/5/28.
 */

public abstract class YFSingleHttpCallback extends BaseHttpCallBack {
    @Override
    public void onSuccess(ApiBean mApiBean) {

    }

    @Override
    public void onResponse(ApiBean mApiBean) {
        if (ApiBean.checkOK(mApiBean.getCode())) {
            onRequestSuccess(mApiBean.data);
        } else {
            onRequestFailure(mApiBean.message);
        }
    }
    protected abstract void onRequestSuccess(String data);
    protected abstract void onRequestFailure(String message);

    @Override
    public void onError(int id, Exception e) {
        onRequestFailure(e.getMessage());
    }
}
