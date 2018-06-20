package com.yufan.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yufan.library.bean.Label;
import com.yufan.library.util.PxUtil;

import java.util.List;

/**
 * Created by mengfantao on 17/9/11.
 *
 */

public class LabelView extends LinearLayout {
    List<Label> list;
    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOrientation(HORIZONTAL);
    }

    public void setLabel(Label label){
        removeAllViews();
        TextView textView=new TextView(getContext());
        textView.setText(label.name);
        textView.setBackground(getContext().getResources().getDrawable(label.drawable));
        textView.setTextColor(getContext().getResources().getColor(label.textColor));

        textView.setIncludeFontPadding(false);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(label.size);
        textView.setMinHeight(label.minHeight);
        textView.setMinWidth(label.minWidth);
        LinearLayout.LayoutParams lp=   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0, PxUtil.convertDIP2PX(getContext(),5),0);
        addView(textView,lp);
    }
    public void setData(List<Label> list){
        this.list=list;
       removeAllViews();
        for (int i=0;i<list.size();i++){
            TextView textView=new TextView(getContext());
            Label label= list.get(i);
            textView.setText(label.name);
            textView.setBackground(getContext().getResources().getDrawable(label.drawable));
            textView.setTextColor(getContext().getResources().getColor(label.textColor));

            textView.setMinHeight(label.minHeight);
            textView.setMinWidth(label.minWidth);

            textView.setIncludeFontPadding(false);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(label.size);
            LinearLayout.LayoutParams lp=   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,PxUtil.convertDIP2PX(getContext(),5),0);
            addView(textView,lp);
        }
    }


}
