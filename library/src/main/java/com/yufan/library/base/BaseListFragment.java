package com.yufan.library.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.inject.AnnotateUtils;
import com.yufan.library.inter.IListFragment;

/**
 * Created by mengfantao on 18/3/30.
 * 列表基础类，继承自baseFragment，需重写onBindVu,增加onLoadMore接口，
 * YFRecyclerView 为列表封装RecyclerView，只需要调initRecyclerview()初始化即可
 *
 *
 */

public abstract class BaseListFragment <V extends BaseListVu> extends BaseFragment implements IListFragment {
    protected V vu;
    public V getVu() {
        return vu;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            vu =(V) AnnotateUtils.getVu(this).newInstance();
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



}
