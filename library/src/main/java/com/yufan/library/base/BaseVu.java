package com.yufan.library.base;

import android.content.Context;
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
import com.yufan.library.util.PxUtil;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

import java.util.HashMap;

/**
 * Created by mengfantao on 18/6/28.
 * vu view模块基础类
 */

public  class BaseVu implements Vu {
    private HashMap<Integer,View> mViews = new HashMap<>();
    private RelativeLayout mRootLayout;
    private View mContentLayout;
    private StateLayout mStateLayout;
    protected AppToolbar mToolbarLayout;
    private Context mContext;


    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        this.mContext=inflater.getContext();
        mRootLayout=    new RelativeLayout(mContext);
        mRootLayout.setId(R.id.root_content_id);
        mContentLayout = inflater.inflate(getLayoutId(), container, false);
        mToolbarLayout = new AppToolbar(mContext);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootLayout.addView(mContentLayout,layoutParams);
        addTitle(mToolbarLayout,initTitle(mToolbarLayout));
        initState();
    }
    @Override
    public View getView() {
        return mRootLayout;
    }
    @Override
    public int getLayoutId() {
        return 0;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     *  添加头
     */
    private void addTitle(AppToolbar appToolbar, boolean isShowTitle){
        if(isShowTitle){
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PxUtil.convertDIP2PX(mContext, 56));
            mRootLayout.addView(appToolbar, lp);
            if(appToolbar.isVertical()){
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mContentLayout.getLayoutParams();
                rlp.addRule(RelativeLayout.BELOW, R.id.title_id);
                mContentLayout.setLayoutParams(rlp);
            }else {
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                appToolbar.getBackgroundView().setAlpha(0);
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mContentLayout.getLayoutParams();
                rlp.removeRule(RelativeLayout.BELOW);
            }
        }

    }

    /**
     *  初始化状态view
     */
    private void initState(){
        mStateLayout=initStateLayout(new StateLayout(this));
        if(mStateLayout!=null&&initRootStateLayout()!=0){
            ViewGroup stateViewGroup= mRootLayout.findViewById(initRootStateLayout());
            if(stateViewGroup!=null){
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                stateViewGroup.addView(mStateLayout,layoutParams);
            }
        }
    }


    /**
     * 状态view
     */
    protected StateLayout initStateLayout(StateLayout stateLayout){
        return stateLayout;
    }
    /**
     *  要覆盖viewgroup id
     */
    protected int initRootStateLayout(){
        return R.id.root_content_id;
    }

    /**
     *  初始化头
     */
    @Override
    public boolean initTitle(AppToolbar toolbar) {
        return false;
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
        if(mViews.containsKey(id)){
            return (T)mViews.get(id);
        }else {
            View tempView=  mContentLayout.findViewById(id);
            mViews.put(id,tempView);
            return(T) tempView;
        }

    }
}
