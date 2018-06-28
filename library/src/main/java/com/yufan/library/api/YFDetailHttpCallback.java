package com.yufan.library.api;

import com.yufan.library.manager.DialogManager;
import com.yufan.library.widget.RootLayout;

/**
 * Created by wangyu on 2018/5/28.
 */

public abstract class YFDetailHttpCallback extends BaseHttpCallBack {


    private RootLayout rootLayout;

    public YFDetailHttpCallback(RootLayout rootLayout) {

        this.rootLayout = rootLayout;
    }

    public YFDetailHttpCallback() {

    }

    @Override
    public void onResponse(ApiBean mApiBean) {
        if (ApiBean.checkOK(mApiBean.getCode())) {

            onRequestSuccess(mApiBean.data);
            rootLayout.setState(RootLayout.STATE_SUCCESS);
        } else {
            onRequestFailure(mApiBean.message);
        }
    }

    protected void onRequestFailure(String message){
        DialogManager.getInstance().toast(message);
        rootLayout.setState(RootLayout.STATE_ERROR);
    }


    protected abstract void onRequestSuccess(String data);

    @Override
    public void onError(int id, Exception e) {
        super.onError(id, e);
        onRequestFailure(e.getMessage());
    }


    @Override
    public void onSuccess(ApiBean mApiBean) {

    }
}
