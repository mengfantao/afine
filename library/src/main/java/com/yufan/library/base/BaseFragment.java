package com.yufan.library.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.inter.IFragment;
import com.yufan.library.inter.IVu;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by mengfantao on 18/3/16.
 * 所有fragment基类,与vu绑定，在baseFragment中实现业务逻辑相关，在vu中实现ui相关，
 *
 */

public abstract class BaseFragment<V extends BaseVu> extends SupportFragment implements IFragment {
    protected V vu;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            vu = getVuClass().newInstance();
            vu.init(inflater, container);
            onBindVu();
            view = vu.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;

    }
    /**
     * 获取根fragment 即activity加载的第一个fragment
     *
     * @return
     */
    public final SupportFragment getRootFragment() {
        SupportFragment fragment = this;
        while (fragment.getParentFragment() != null) {
            fragment = (SupportFragment) fragment.getParentFragment();
        }
        return fragment;
    }

    protected  void onBindVu() {
        vu.setiVu(new IVu() {
            @Override
            public void onRefresh() {
                BaseFragment.this.onRefresh();
            }

            @Override
            public void onLoadMore(int index) {

            }

            @Override
            public void finish() {
                BaseFragment.this.pop();
            }
        });
    }

    protected  abstract Class<V> getVuClass();
}
