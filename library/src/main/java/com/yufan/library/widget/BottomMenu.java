package com.yufan.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yufan.library.R;

import java.util.List;

/**
 * Created by mft on 2016/5/24 0024.
 */
public class BottomMenu extends Dialog implements View.OnClickListener {
    private final RecyclerView rc_dialog;
    private TextView tv_cancel;
    private List list;
    public BottomMenu(Context context) {
        super(context, R.style.dialog_bottom);
        View shareView = getLayoutInflater().inflate(
                R.layout.dialog_bottom_list, null);
        rc_dialog = (RecyclerView) shareView.findViewById(R.id.rc_dialog);
        tv_cancel = (TextView) shareView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        setContentView(shareView);
    }
    public void setAdapter(RecyclerView.Adapter adapter){
        rc_dialog.setAdapter(adapter);
    }
    public void setData(List list){
        this.list=list;
    }

    protected int getLayoutId() {
        return R.layout.dialog_bottom_list;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            this.dismiss();

        }
    }



}
