package com.yufan.library.api;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yufan.library.manager.UserManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mengfantao on 18/2/27.
 */

public class ApiManager {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;
    private ApiManager(){
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor(){
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request authorised = chain.request().newBuilder()
                       // .headers(mHeaders)
                        .addHeader("paltform","android")
                        .addHeader("userId","userid")
                        .addHeader("Authorization",  Base64.encodeToString(("JWT " +UserManager.getInstance().getToken()).getBytes(), Base64.NO_WRAP))
                        .build();
                return chain.proceed(authorised);

            }
        };
        builder.addInterceptor(commonInterceptor);
        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://47.98.52.243:8001")
                .baseUrl("http://192.168.200.43:8001")
                .build();
    }
    private static class SingletonHolder{
        private static final ApiManager INSTANCE = new ApiManager();
    }
    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static ApiManager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }



    public  void callEnqueue(Call<ResponseBody> call, final BaseHttpCallBack handler) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    int code = response.code();
                    if (code == 200) {
                        String mjson = response.body().string();
                        ApiBean mApiBean = JSON.parseObject(mjson, ApiBean.class);
                        mApiBean.json=mjson;
                            Log.i("http","response: " + mApiBean.json);
                            if (TextUtils.equals(ApiBean.TOKEN_LOSE,mApiBean.code)) {
                                //token失效
                                return;
                            }

                        handler.onResponse(mApiBean);
                    } else {
                        handler.onError(code, new Exception(response.errorBody().string()));
                    }
                } catch (IOException e) {
                    handler.onError(-1, new Exception("接口格式异常"));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Exception exception = new Exception(t.getMessage());
                handler.onError(-1, exception);
            }
        });

    }
}
