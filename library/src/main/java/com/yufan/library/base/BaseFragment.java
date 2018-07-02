package com.yufan.library.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yufan.library.R;
import com.yufan.library.inter.IFragment;
import com.yufan.library.util.PxUtil;
import com.yufan.library.widget.AppToolbar;

import java.util.HashMap;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by mengfantao on 18/3/16.
 * 所有fragment基类
 */

public abstract class BaseFragment<V extends BaseVu> extends SupportFragment implements IFragment {
    protected V vu;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
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
    public SupportFragment getRootFragment() {
        SupportFragment fragment = this;
        while (fragment.getParentFragment() != null) {
            fragment = (SupportFragment) fragment.getParentFragment();
        }
        return fragment;
    }

    protected void onBindVu() {
    }

    protected abstract Class<V> getVuClass();
}
