package com.yufan.library.inter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

public interface Vu {
    void init(LayoutInflater inflater, ViewGroup container);
    View getView();

    int getLayoutId();
    boolean initTitle(AppToolbar appToolbar);
    void initStateLayout(StateLayout stateLayout);
    int getRootStateLayout();
}
