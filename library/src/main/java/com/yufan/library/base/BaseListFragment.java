package com.yufan.library.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public abstract class BaseListFragment <V extends BaseListVu> extends BaseFragment implements IListFragment {
    protected V vu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            vu = getVuClass().newInstance();
            vu.init(inflater, container);
            vu.setPresenter(this);
            onBindVu();
            view = vu.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;

    }


    protected void onBindVu() {

    }


    protected  abstract Class<V> getVuClass();
}
