package com.yufan.library.api;

import android.util.Log;
import android.widget.Toast;

import com.yufan.library.base.BaseVu;
import com.yufan.library.manager.DialogManager;


/**
 * Created by mengfantao on 18/2/27.
 */

public  abstract class BaseHttpCallBack implements IHttpCallBack {



    public abstract void onSuccess(ApiBean mApiBean);

    public abstract void onError(int id, Exception e);

    public  abstract void onFinish();

    private BaseVu vu;

    public BaseHttpCallBack(BaseVu vu) {
        this.vu = vu;
    }

    public BaseHttpCallBack() {
    }

    @Override
    public final void onResponse(ApiBean mApiBean) {
        if(ApiBean.checkOK(mApiBean.getCode())){
            if(vu!=null){
                vu.setStateGone();
            }
            onSuccess(mApiBean);
        }else {
            DialogManager.getInstance().toast(mApiBean.message);
        }
    }

    @Override
    public final void onFailure(int id, Exception e) {
        DialogManager.getInstance().toast(e.getMessage());
        if(vu!=null){
            vu.setStateError();
        }
        onError( id,  e);
    }
}
