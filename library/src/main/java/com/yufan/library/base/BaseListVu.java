package com.yufan.library.base;

import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.R;
import com.yufan.library.manager.PageManager;
import com.yufan.library.view.recycler.YFRecyclerView;

/**
 * Created by mengfantao on 18/7/9.
 */

public abstract class BaseListVu <T extends BasePresenter>extends BaseVu {
    protected T mPersenter;
    protected abstract YFRecyclerView getRecyclerView();
    @Override
    public T getPresenter() {
        return mPersenter;
    }
    @Override
    public void setPresenter(Object presenter) {
        mPersenter= (T) presenter;
    }
    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_list;
    }

    protected   void initRecyclerview(final YFRecyclerView recyclerViewModel){
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
