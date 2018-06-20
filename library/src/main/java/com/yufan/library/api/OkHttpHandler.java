package com.yufan.library.api;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 */
public abstract class OkHttpHandler extends StringCallback implements IHttpCallBack {   //GamecatBaseHttpHandler{


    public OkHttpHandler() {
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onFailure(id,  e);
    }

    public void onResponse(String response, int id, retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> mResponse, Headers headers) {
        onResponse(response, id);
    }

    @Override
    public void onResponse(String response, int id) {
        ApiBean mApiBean = null;
        try {
            //mjson = JsonUtil.ConvertStream2Json(new ByteArrayInputStream(responseBody));
//            if (BuildConfig.DEBUG) {
//                TLog.i("response: " + response);
//            }

            mApiBean = JSON.parseObject(response, ApiBean.class);

            Log.i("app","response: " + response);
            if (ApiBean.TOKEN_LOSE==mApiBean.code) {
              //登陆
                return;
            }
            onResponse(mApiBean);
        } catch (Exception ex) {
            Log.e("GameCatHttpHandler", ex+"");
            onFailure(id,  new Exception("接口格式异常"));
        }
    }

    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        return response.body().string();
    }


}
