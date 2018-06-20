package com.yufan.library.view.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by moon.zhong on 2015/7/20.
 * time : 15:14
 * 带头尾的recyclerview
 *
 */
public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>() ;

    private ArrayList<View> mFootViews = new ArrayList<>() ;

    private Adapter mAdapter ;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Adapter getExtendAdapter() {
        return mAdapter;
    }

    public void clearHeaderView(){
        mHeaderViews.clear();
    }
    public void addHeaderView(View view){
        if(mHeaderViews.contains(view)){
            return;
        }
        mHeaderViews.add(view);
        if (mAdapter != null){
            if (!(mAdapter instanceof RecyclerWrapAdapter)){
                mAdapter = new RecyclerWrapAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }
    public void addHeaderView(int position,View view){
    if(mHeaderViews.contains(view)){
        return;
    }
        mHeaderViews.add(position,view);
        if (mAdapter != null){
            if (!(mAdapter instanceof RecyclerWrapAdapter)){
                mAdapter = new RecyclerWrapAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void clearFootView(){
        mFootViews.clear();
    }
    public void addFootView(int position,View view){
        if(mFootViews.contains(view)){
            return;
        }
        mFootViews.add(position,view);
        if (mAdapter != null){
            if (!(mAdapter instanceof RecyclerWrapAdapter)){
                mAdapter = new RecyclerWrapAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }
    public void addFootView(View view){
        if(mFootViews.contains(view)){
            return;
        }
        mFootViews.add(view);
        if (mAdapter != null){
            if (!(mAdapter instanceof RecyclerWrapAdapter)){
                mAdapter = new RecyclerWrapAdapter(mHeaderViews,mFootViews,mAdapter) ;
//                mAdapter.notifyDataSetChanged();
            }
        }
    }
    public void removeFootView(View view){
       if(mFootViews.contains(view)) {
           mFootViews.remove(view);
       }
    }
    public void removeHeadView(View view){
        if(mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
        }
    }
    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViews.isEmpty()&&mFootViews.isEmpty()){
            super.setAdapter(adapter);
        }else {
            adapter = new RecyclerWrapAdapter(mHeaderViews,mFootViews,adapter) ;
            super.setAdapter(adapter);
        }
        mAdapter = adapter ;
    }
    public void setRealAdapter(Adapter adapter){
        super.setAdapter(adapter);
    }

}
