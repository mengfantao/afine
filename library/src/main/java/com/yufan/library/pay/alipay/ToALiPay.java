package com.yufan.library.pay.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 */
public class ToALiPay {
    public static final int BASE_ID = 0;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    String sign="";
    String _input_charset="";
    String total_fee="";
    String subject="";
    String notify_url="";
    String service="";
    String seller_id="";
    String partner="";
    String out_trade_no="";
    String payment_type="";
    String body="";

    public void action(final Context context, JSONObject jsonString){

        try {
            sign=jsonString.getString("sign");
            _input_charset=jsonString.getString("_input_charset");
            total_fee=jsonString.getString("total_fee");
            subject=jsonString.getString("subject");
            notify_url=jsonString.getString("notify_url");
            service=jsonString.getString("service");
            seller_id=jsonString.getString("seller_id");
            partner=jsonString.getString("partner");
            out_trade_no=jsonString.getString("out_trade_no");
            payment_type=jsonString.getString("payment_type");
            body=jsonString.getString("body");
            String orderInfo = getOrderInfo(subject,body,total_fee,partner, seller_id,
                    out_trade_no, notify_url,_input_charset,payment_type);
            // 对订单做RSA 签名
            String sign1 =sign;
            try {
                // 仅需对sign 做URL编码
                sign1 = URLEncoder.encode(sign, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // 完整的符合支付宝参数规范的订单信息
           /* final String payInfo = orderInfo + "&sign=\"" + sign1+ "\"&"
                    + getSignType();*/
            final String payInfo = orderInfo + "&sign=\"" + sign1+ "\"&"
                    + getSignType();
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    // 构造PayTask 对象
                    PayTask alipay = new PayTask((Activity) context);
                    // 调用支付接口，获取支付结果
                    String result = alipay.pay(payInfo,true);


                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
//                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price, String partner,
                                String seller, String outTradeNo, String notifyUrl, String _input_charset
    , String payment_type) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + partner + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + seller + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + outTradeNo + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notifyUrl
                + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=" + "\"" + payment_type
                + "\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset="+ "\"" + _input_charset
                + "\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
      /*  // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";*/
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }
    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    PayResult payResult = new PayResult(msg.obj);
//
//                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
//                    String resultInfo = payResult.getResult();
//                    String resultStatus = payResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
//                    if (TextUtils.equals(resultStatus, "9000")) {
//
//
//                    } else {
//                        // 判断resultStatus 为非“9000”则代表可能支付失败
//                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
//                        if (TextUtils.equals(resultStatus, "8000")) {
//                            JSONObject resultJson = new JSONObject();
//                            try {
//                                resultJson.put("status", resultStatus);
//                                resultJson.put("message", "支付结果确认中");
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        } else {
//
//                        }
//                    }
//                    break;
//                }
//                case SDK_CHECK_FLAG: {
//                    JSONObject resultJson = new JSONObject();
//                    try {
//                        resultJson.put("status", "-1");
//                        resultJson.put("message", "检查结果为：" + msg.obj);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//                default:
//                    break;
//            }
//        };
//    };

}
