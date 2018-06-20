package com.yufan.library.base;


import com.yufan.library.inter.IListFragment;
import com.yufan.library.manager.PageManager;
import com.yufan.library.view.recycler.YFRecyclerView;

/**
 * Created by mengfantao on 18/3/30.
 */

public abstract class BaseListFragment extends BaseFragment implements IListFragment {



    protected final void initRecyclerview(final YFRecyclerView recyclerViewModel){
        recyclerViewModel.initPTR();
        recyclerViewModel.setOnPagerListener(new YFRecyclerView.OnPagerListener() {
            @Override
            public void onLoadMore(int index) {
                if (recyclerViewModel.getPageManager().isIdle()) {
                    recyclerViewModel.getPageManager().setPageState(PageManager.PAGE_STATE_LOADING);
                    BaseListFragment.this.onLoadMore(index);
                }
            }
            @Override
            public void onRefresh() {
                if (recyclerViewModel.getPageManager().isIdle()) {
                    recyclerViewModel.getPageManager().resetIndex();
                    recyclerViewModel.getPageManager().setPageState(PageManager.PAGE_STATE_LOADING);
                    BaseListFragment.this.onRefresh();
                }
            }
        });
    }
}
