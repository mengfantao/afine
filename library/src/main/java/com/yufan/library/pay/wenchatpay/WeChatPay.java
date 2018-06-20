package com.yufan.library.pay.wenchatpay;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;


public class WeChatPay {
    private static WeChatPay weChatPay;
    PayReq req;
    private IWXAPI api;
    String tmpTn;
    String Key;
    static String appid = null;
    IWXAPI msgApi;
    public static WeChatPay getInstance() {
        if (weChatPay == null) {
            weChatPay = new WeChatPay();
        }
        return weChatPay;
    }

    public void toWeChatPay(Context context, JSONObject data) {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        Toast.makeText(context, "微信初始化中...", Toast.LENGTH_SHORT).show();
        try {
            appid = data.getString("appid");
            msgApi.registerApp(appid);
            api = WXAPIFactory.createWXAPI(context, appid);
            if (api.isWXAppInstalled()) {
                req = new PayReq();
                req.appId = appid;
                req.partnerId = data.getString("partnerid");
                req.prepayId = data.getString("prepayid");
                req.packageValue = data.getString("package");
                req.nonceStr = data.getString("noncestr");
                req.timeStamp = data.getString("timestamp");
                req.sign = data.getString("sign");
                sendPayReq();
            } else {
                Toast.makeText(context, "请安装微信再进行支付",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    private void sendPayReq() {
        api.sendReq(req);

    }
}
