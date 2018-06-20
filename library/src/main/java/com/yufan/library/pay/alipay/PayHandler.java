package com.yufan.library.pay.alipay;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

/**
 * Created by wangyu on 2018/6/8.
 */

public class PayHandler extends Handler {

    public static final int SDK_PAY_FLAG = 1001;
    private OnPayListener onPayListener;

    public PayHandler(OnPayListener onPayListener) {

        this.onPayListener = onPayListener;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SDK_PAY_FLAG:
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                //同步获取结果
                String resultInfo = payResult.getResult();
                Log.i("Pay", "Pay:" + resultInfo);
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    //成功
                    if (onPayListener != null) onPayListener.onPaySuccess();
                } else {
                    //失败
                    if (onPayListener != null) onPayListener.onPayFail();
                }
                break;
        }
    }

    public interface OnPayListener {
        void onPaySuccess();

        void onPayFail();
    }
}
