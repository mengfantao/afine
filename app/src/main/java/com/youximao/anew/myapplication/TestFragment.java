package com.youximao.anew.myapplication;

import android.view.View;

import com.yufan.library.base.BaseFragment;

/**
 * Created by mengfantao on 18/7/2.
 */

public class TestFragment extends BaseFragment<TestVu> {


    @Override
    public void onRefresh() {
        vu.setStateGone();
    }

    @Override
    protected Class<TestVu> getVuClass() {
        return TestVu.class;
    }
}
