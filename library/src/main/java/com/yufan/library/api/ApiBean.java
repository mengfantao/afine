package com.yufan.library.api;

import android.text.TextUtils;

/**
 * Created by mengfantao on 18/2/27.
 */

public class ApiBean {
    public final static String  TOKEN_LOSE="002";
    public final static String  SUCCESS="200";


    public String code;
    public String message;
    public String data;
    public String json;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public static boolean checkOK(String code) {
        return TextUtils.equals(SUCCESS, code);
    }

    public static boolean checkTokenLose(String code) {
        return TextUtils.equals(TOKEN_LOSE, code);
    }
}
