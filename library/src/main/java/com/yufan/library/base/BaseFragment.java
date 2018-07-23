package com.yufan.library.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.inject.AnnotateUtils;
import com.yufan.library.inter.IFragment;
import com.yufan.library.inter.VuCallBack;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by mengfantao on 18/3/16.
 * 所有fragment基类,与vu绑定，在baseFragment中实现业务逻辑相关，在vu中实现ui相关，
 *
 */

public abstract class BaseFragment<V extends BaseVu> extends SupportFragment implements BasePresenter {
    protected V vu;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator(); //super.onCreateFragmentAnimator();
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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

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

    }



}
