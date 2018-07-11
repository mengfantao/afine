package com.youximao.anew.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.youximao.anew.myapplication.vu.TestVu;
import com.yufan.library.api.ApiBean;
import com.yufan.library.api.YFListHttpCallBack;
import com.yufan.library.base.BaseFragment;
import com.yufan.library.base.BaseListFragment;
import com.yufan.library.view.recycler.YFRecyclerView;

import java.util.List;

/**
 * Created by mengfantao on 18/7/2.
 */

public class TestFragment extends BaseListFragment<TestVu> {


    @Override
    public void onRefresh() {
        vu.setStateGone();
    }

    @Override
    protected Class<TestVu> getVuClass() {

        return TestVu.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new YFListHttpCallBack(vu.getRecyclerViewModel()){

            @Override
            public void onEmpty() {
                vu.setStateEmpty();
            }

            @Override
            public List onListSuccess(ApiBean mApiBean) {
                return null;
            }
        };
    }

    @Override
    public void onLoadMore(int index) {

    }
}
