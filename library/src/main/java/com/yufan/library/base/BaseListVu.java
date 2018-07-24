package com.yufan.library.base;

import android.support.annotation.IdRes;
import android.view.View;

import com.yufan.library.inject.AnnotateUtils;
import com.yufan.library.manager.PageManager;
import com.yufan.library.view.recycler.YFRecyclerView;

/**
 * Created by mengfantao on 18/7/9.
 */

public abstract class BaseListVu <T extends BasePresenter>extends BaseVu {
    protected T mPersenter;
    private YFRecyclerView mYFRecyclerView;
    @Override
    public T getPresenter() {
        return mPersenter;
    }
    @Override
    public void setPresenter(Object presenter) {
        mPersenter= (T) presenter;
    }
    @Override
    public void initView(View view) {
        int recyclerviewId=AnnotateUtils.getRecyclerView(this);
        if(recyclerviewId==0){
            throw new IllegalArgumentException("加入类注解 @FindRecyclerView( R.id.recyclerview)") ;
        }
        mYFRecyclerView= (YFRecyclerView) findViewById(recyclerviewId);
        initRecyclerview(mYFRecyclerView);
    }
    public YFRecyclerView getRecyclerView() {
        return mYFRecyclerView;
    }


    protected     void initRecyclerview(final YFRecyclerView recyclerViewModel){
        recyclerViewModel.initPTR();
        recyclerViewModel.setOnPagerListener(new YFRecyclerView.OnPagerListener() {
            @Override
            public void onLoadMore(int index) {
                if (recyclerViewModel.getPageManager().isIdle()) {
                    recyclerViewModel.getPageManager().setPageState(PageManager.PAGE_STATE_LOADING);
                   mPersenter.onLoadMore(index);
                }
            }
            @Override
            public void onRefresh() {
                if (recyclerViewModel.getPageManager().isIdle()) {
                    recyclerViewModel.getPageManager().resetIndex();
                    recyclerViewModel.getPageManager().setPageState(PageManager.PAGE_STATE_LOADING);
                    mPersenter.onRefresh();
                }
            }
        });
    }


}
