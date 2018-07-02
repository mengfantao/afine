package com.youximao.anew.myapplication;

import android.view.View;
import android.widget.TextView;

import com.yufan.library.base.BaseVu;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

/**
 * Created by mengfantao on 18/7/2.
 */

public class TestVu extends BaseVu {


    @Override
    public int getLayoutId() {
        return R.layout.fragemnt_test;
    }

    @Override
    public int getRootStateLayout() {
        return R.id.rl_content;
    }


    @Override
    public void initStateLayout(StateLayout stateLayout) {
        super.initStateLayout(stateLayout);
     View view=   View.inflate(getContext(),R.layout.empty_view,null);
      // stateLayout.setEmptyView(view);
       stateLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean initTitle(AppToolbar toolbar) {
        toolbar.creatCenterView(TextView.class).setText("ccececece");
        toolbar.build();
        return true;
    }
}
