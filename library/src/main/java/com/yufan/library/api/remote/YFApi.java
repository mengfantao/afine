package com.yufan.library.api.remote;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by mengfantao on 18/2/27.
 */
public interface YFApi {

    /**
     * @param route
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8"})
    @POST("/{path}/{action}/")
    Call<ResponseBody> postData(@Path("path") String path, @Path("action") String action, @Body RequestBody route);


    /**
     * @param route
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8"})
    @PATCH("/{path}/{action}/{id}")
    Call<ResponseBody> patchData(@Path("path") String path, @Path("action") String action, @Body RequestBody route);

    /**
     * @param route
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8"})
    @DELETE("/{path}/{action}/{id}")
    Call<ResponseBody> deleteData(@Path("path") String path, @Path("action") String action, @Path("id") int id);
}
