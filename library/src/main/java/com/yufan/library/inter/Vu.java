package com.yufan.library.inter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.base.BasePresenter;
import com.yufan.library.view.recycler.YFRecyclerView;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

public interface Vu  <T >  {
    void init(LayoutInflater inflater, ViewGroup container);

    View getView();

    @LayoutRes
    int getLayoutId();

    boolean initTitle(AppToolbar appToolbar);

    void initStateLayout(StateLayout stateLayout);

    @IdRes
    int getRootStateLayout();

    void initView(View view);
    void setPresenter(T presenter);
    T getPresenter();
}
