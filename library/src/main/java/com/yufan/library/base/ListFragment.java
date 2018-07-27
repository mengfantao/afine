package com.yufan.library.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yufan.library.inject.AnnotateUtils;
import com.yufan.library.inter.Vu;

/**
 * Created by mengfantao on 18/3/30.
 *
 */

public abstract class ListFragment<V extends Vu> extends Fragment {
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
            view = vu.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }
}
