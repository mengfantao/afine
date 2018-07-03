package com.yufan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yufan.library.R;
import com.yufan.library.base.BaseVu;

/**
 * 状态layout
 * 根据不同情况切换状态样式
 */

public class StateLayout extends RelativeLayout {
    private RelativeLayout rl_empty;
    private RelativeLayout rl_error;
    Class vuClass;
    public StateLayout( BaseVu vu) {
        this(vu.getContext(), null);
        vuClass=vu.getClass();
        setBackgroundColor(getResources().getColor(R.color.white));
        View.inflate(getContext(),R.layout.state_layout,this);
        rl_empty= findViewById(R.id.rl_empty);
        rl_error=  findViewById(R.id.rl_error);
    }
    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEmptyView(View emptyView){
        rl_empty.removeAllViews();
        RelativeLayout.LayoutParams layoutParams1=  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rl_empty.addView(emptyView,layoutParams1);
    }
    public void setErrorView(View errorView){
        rl_error.removeAllViews();
        RelativeLayout.LayoutParams layoutParams2=  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rl_error.addView(errorView,layoutParams2);
    }


    public View getErrorView(){
        return rl_error;
    }
    public View getEmptyView(){
        return rl_empty;
    }


    public void setStateError(){
        setVisibility(VISIBLE);
        rl_error.setVisibility(VISIBLE);
        rl_empty.setVisibility(GONE);
    }

    public void setStateEmpty(){
        setVisibility(VISIBLE);
        rl_error.setVisibility(GONE);
        rl_empty.setVisibility(VISIBLE);

    }



}
