package com.yufan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
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
        errorView=View.inflate(vu.getContext(), R.layout.layout_error,this);
        emptyView=View.inflate(vu.getContext(),R.layout.layout_empty,this);
        errorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
             IVu iVu= vu.getiVu();
             if(iVu!=null){
                 iVu.onRefresh();
             }
            }
        });

    }
    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setErrorView(View view){
        removeView(errorView);
        errorView=view;
    }
    public void setEmptyView(View view){
        removeView(emptyView);
        emptyView=view;
    }
    public void hintState(){
        setVisibility(GONE);
    }

    public void onErrorState(){
        setVisibility(VISIBLE);
        errorView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }

    public void onEmptyState(){
        setVisibility(VISIBLE);
        errorView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
    }



}
