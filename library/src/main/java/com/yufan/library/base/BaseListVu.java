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
    private YFRecyclerView recyclerViewModel;
    @Override
    public void initView(View view) {
        recyclerViewModel=  (YFRecyclerView) view.findViewById(getRecyclerViewId());
        initRecyclerview();
    }
    @Override
    public YFRecyclerView getRecyclerViewModel(){
        return recyclerViewModel;
    }

    protected T mPersenter;
    @Override
    public T getPresenter() {
        return mPersenter;
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_list;
    }



    @IdRes
    public  int getRecyclerViewId(){
        return R.id.recyclerview;
    }

    private  void initRecyclerview(){
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
