package com.yufan.library.inter;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.yufan.library.widget.AppToolbar;

/**
 * Created by mengfantao on 18/3/16.
 */

public interface IFragment {

    void initData();

    void initView(View view);

    void onRefresh();


}
