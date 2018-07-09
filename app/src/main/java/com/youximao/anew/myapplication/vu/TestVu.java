package com.youximao.anew.myapplication.vu;

import android.view.View;
import android.widget.TextView;

import com.youximao.anew.myapplication.R;
import com.yufan.library.base.BaseListVu;
import com.yufan.library.base.BaseVu;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

/**
 * Created by mengfantao on 18/7/2.
 */

public class TestVu extends BaseListVu {


    @Override
    public int getLayoutId() {
        return R.layout.fragemnt_test;
    }

    @Override
    public int getRootStateLayout() {
        return R.id.rl_content;
    }

    @Override
    public void initView(View view) {



    }

    @Override
    public int getRecyclerViewId() {
        return 0;
    }


    @Override
    public void initStateLayout(StateLayout stateLayout) {
        setStateEmpty();
        stateLayout.getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getVuCallBack().onRefresh();
            }
        });

    }

    @Override
    public boolean initTitle(AppToolbar toolbar) {
        toolbar.creatCenterView(TextView.class).setText("ccececece");
        toolbar.build();
        return true;
    }
}
