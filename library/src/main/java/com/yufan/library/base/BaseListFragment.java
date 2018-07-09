package com.yufan.library.base;


import com.yufan.library.inter.IListFragment;
import com.yufan.library.inter.VuCallBack;
import com.yufan.library.manager.PageManager;
import com.yufan.library.view.recycler.YFRecyclerView;

/**
 * Created by mengfantao on 18/3/30.
 * 列表基础类，继承自baseFragment，需重写onBindVu,增加onLoadMore接口，
 * YFRecyclerView 为列表封装RecyclerView，只需要调initRecyclerview()初始化即可
 *
 *
 */

public abstract class BaseListFragment<V extends BaseListVu> extends BaseFragment implements IListFragment {




    protected void onBindVu() {
        vu.setVuCallBack(new VuCallBack() {
            @Override
            public void onRefresh() {
                BaseListFragment.this.onRefresh();
            }

            @Override
            public void onLoadMore(int index) {
                BaseListFragment.this.onLoadMore(index);
            }

            @Override
            public void finish() {
                BaseListFragment.this.pop();
            }
        });
    }

}
