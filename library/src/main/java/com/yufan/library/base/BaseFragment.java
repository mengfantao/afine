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
import android.widget.TextView;

import com.yufan.library.R;
import com.yufan.library.inter.IFragment;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.RootLayout;

import java.util.HashMap;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by mengfantao on 18/3/16.
 * 所有fragment基类
 */

public abstract class BaseFragment extends SupportFragment implements IFragment {
    protected AppToolbar toolbar;
    private RootLayout mSateLayout;
    private HashMap<Integer,View> views = new HashMap<>();
    protected String title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSateLayout = new RootLayout(container.getContext());
        mSateLayout.setStateLayout(RootLayout.STATE_SUCCESS, getLayoutId());
        mSateLayout.setBackgroundColor(getResources().getColor(R.color.colorMainBackground));
        toolbar = new AppToolbar(getContext());
        mSateLayout.addTitle(toolbar,initTitle(toolbar));
        initView(mSateLayout.getStateView(RootLayout.STATE_SUCCESS));
        mSateLayout.setOnStateLayoutClickListener(mStateLayoutClickListener);
        if(isShowLoading()){
            mSateLayout .setState(RootLayout.STATE_LOADING);
        }else {
            mSateLayout .setState(RootLayout.STATE_SUCCESS);
        }
        return mSateLayout;
    }

    /**
     * 获取根fragment 即activity加载的第一个fragment
     * @return
     */
    public SupportFragment getRootFragment(){
        SupportFragment fragment= this;
        while (fragment.getParentFragment()!=null){
            fragment=(SupportFragment) fragment.getParentFragment();
        }
        return fragment;
    }

    @Override
    public boolean initTitle(AppToolbar toolbar) {
        ImageView leftView = toolbar.creatLeftView(ImageView.class);
        leftView.setImageDrawable(ContextCompat.getDrawable(_mActivity,R.drawable.ic_arrow_back_white));
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        TextView centerView = toolbar.creatCenterView(TextView.class);
        centerView.setText(title);
        centerView.setTextSize(18);
        centerView.setTextColor(getResources().getColor(R.color.white));

        toolbar.build();
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void setState(@RootLayout.State int state) {
        mSateLayout.setState(state);
    }

    protected final RootLayout getRootLayout() {
        return mSateLayout;
    }

    private RootLayout.OnStateLayoutClickListener mStateLayoutClickListener = new RootLayout.OnStateLayoutClickListener() {
        @Override
        public void onClick() {
            setState(isShowLoading() ? RootLayout.STATE_LOADING : RootLayout.STATE_SUCCESS);
            initData();
        }
    };

    @Override
    public void initData() {

    }

    protected boolean  isShowLoading(){
        return  false;
    }


    /**
     * findview
     * @param id
     * @return
     */

    protected TextView findTextView(int id){
        return  $( id,TextView.class);
    }
    protected ImageView findImageView(int id){
        return  $( id,ImageView.class);
    }
    protected CheckBox findCheckBox(int id){
        return  $( id,CheckBox.class);
    }
    protected EditText findEditText(int id){
        return  $( id,EditText.class);
    }
    protected Button findButton(int id){
        return  $( id,Button.class);
    }
    protected View findView(int id){
        return  $( id,View.class);
    }
    protected <T> T $(int id,Class<T> clazz){
        if(views.containsKey(id)){
            return (T)views.get(id);
        }else {
            View tempView=  mSateLayout.findViewById(id);
            views.put(id,tempView);
            return(T) tempView;
        }

    }

}
