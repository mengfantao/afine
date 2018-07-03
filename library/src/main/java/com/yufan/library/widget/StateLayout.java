package com.yufan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yufan.library.R;
import com.yufan.library.base.BaseVu;
import com.yufan.library.inter.IVu;

/**
 * 状态layout
 * 根据不同情况切换状态样式
 */

public class StateLayout extends RelativeLayout {
    private View errorView;
    private View emptyView;
    Class vuClass;
    public StateLayout(final BaseVu vu) {
        this(vu.getContext(), null);
        vuClass=vu.getClass();
        setBackgroundColor(getResources().getColor(R.color.white));
        setVisibility(GONE);

    }
    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEmptyView(View emptyView){
        this.emptyView=emptyView;
        RelativeLayout.LayoutParams layoutParams1=  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(emptyView,layoutParams1);
    }
    public void setErrorView(View errorView){
        this.errorView=errorView;
        RelativeLayout.LayoutParams layoutParams2=  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(errorView,layoutParams2);
    }


    public void setStateError(){
        setVisibility(VISIBLE);
        if(errorView!=null){
            errorView.setVisibility(VISIBLE);
        }
        if(emptyView!=null){
            emptyView.setVisibility(GONE);
        }


    }

    public void setStateEmpty(){
        setVisibility(VISIBLE);
        if(errorView!=null){
            errorView.setVisibility(GONE);
        }
       if(emptyView!=null){
           emptyView.setVisibility(VISIBLE);
       }

    }



}
