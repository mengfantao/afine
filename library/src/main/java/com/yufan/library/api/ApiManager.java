package com.yufan.library.api;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yufan.library.manager.UserManager;
import com.yufan.library.util.YFUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
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
                       .headers(Headers.of(getApiHeader()))
                        .build();
                RequestBody requestBody= authorised.body();
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset();
                }

                String paramsStr = buffer.readString(charset);
                Log.d("http","body"+paramsStr);
                return  chain.proceed(authorised);
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
        call.request().body();
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


    /**
     * 获取HTTP header信息
     *
     * @return
     */
    private  Map<String, String> getApiHeader() {
        LinkedHashMap apiHeaders = new LinkedHashMap<String, String>();
        apiHeaders.put("Accept-Language", Locale.getDefault().toString() );
        apiHeaders.put("Connection", "Keep-Alive");
        apiHeaders.put("User-Agent", "");
        apiHeaders.put("cpu", YFUtil.getCpuName());
        apiHeaders.put("hardware", YFUtil.getprop("ro.hardware", ""));
        apiHeaders.put("cpu_abi", Build.CPU_ABI);
        apiHeaders.put("product_cpu_abi", YFUtil.getCpuProduct());
        apiHeaders.put("terminalType", "2"); // 1:ios; 2:android
        apiHeaders.put("terminalName", Build.DEVICE + "");
        apiHeaders.put("channelId", "");
        apiHeaders.put("version", YFUtil.getVersionName() + "");
        apiHeaders.put("devicesId", YFUtil.getIMEI());
        apiHeaders.put("sid", "");
        apiHeaders.put("apiVersion", "");
        apiHeaders.put("paltform","android");
        apiHeaders.put("userId","");
        apiHeaders.put("token","");
        return apiHeaders;
    }
}
