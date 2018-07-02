package com.yufan.library.base;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.widget.AppToolbar;

public interface Vu {
    void init(LayoutInflater inflater, ViewGroup container);
    View getView();
    @LayoutRes
    int getLayoutId();

    boolean initTitle(AppToolbar appToolbar);

}
