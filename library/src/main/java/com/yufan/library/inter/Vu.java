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

public interface Vu<T> {
    void init(LayoutInflater inflater, ViewGroup container);

    View getView();

    boolean initTitle(AppToolbar appToolbar);

    void initStatusLayout(StateLayout stateLayout);

    void initView(View view);

    void setPresenter(T presenter);

    T getPresenter();
    void setStateGone();
    void setStateError();
    void setStateEmpty();
}
