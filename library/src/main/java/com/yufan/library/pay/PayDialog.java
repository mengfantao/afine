package com.yufan.library.pay;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;


import com.yufan.library.R;
import com.yufan.library.pay.alipay.ToALiPay;
import com.yufan.library.pay.wenchatpay.WeChatPay;

import org.json.JSONObject;

/**
 * Created by mengfantao on 18/3/21.
 */

public class PayDialog extends Dialog {


    private View content;
    public PayDialog(@NonNull Context context, float message, float money) {
        super(context, R.style.dialog_common);
        content = LayoutInflater.from(context).inflate(
                R.layout.layout_pay, null);
        setContentView(content);
        content.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
       TextView tv_message= (TextView) content.findViewById(R.id.tv_message);
       tv_message.setText("充值金币："+message);
       TextView tv_money=(TextView)content.findViewById(R.id.tv_money);
        tv_money.setText("¥"+money);
     final CheckBox cb_zfb=(CheckBox) content.findViewById(R.id.cb_zfb);
        final CheckBox cb_weixin=(CheckBox) content.findViewById(R.id.cb_weixin);
       content.findViewById(R.id.rl_zfb).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cb_zfb.setChecked(true);
               cb_weixin.setChecked(false);
           }
       });
       content.findViewById(R.id.rl_weixin).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               cb_weixin.setChecked(true);
               cb_zfb.setChecked(false);
           }
       });
        content.findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PAY
                if(cb_zfb.isChecked()){
                    ToALiPay aLiPay=new ToALiPay();
                    aLiPay.action(getContext(),new JSONObject());

                }else if(cb_weixin.isChecked()){
                    WeChatPay.getInstance().toWeChatPay(getContext(),new JSONObject());

                }
            }
        });
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
        getWindow().setDimAmount(0.6f);

    }
}
