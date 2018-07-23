package com.yufan.library.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yufan.library.R;
import com.yufan.library.inject.AnnotateUtils;
import com.yufan.library.inter.Vu;
import com.yufan.library.util.PxUtil;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;


/**
 * Created by mengfantao on 18/6/28.
 * vu view模块基础类,
 */

public abstract class BaseVu <T extends BasePresenter>implements Vu {
    private RelativeLayout mRootLayout;
    private View mContentLayout;
    private StateLayout mStateLayout;
    protected AppToolbar mToolbarLayout;
    private Context mContext;
    protected  T mPersenter;
    @Override
    public  T getPresenter() {
        return mPersenter;
    }
    @Override
    public  void setPresenter(Object presenter) {
        mPersenter= (T) presenter;
    }
    @Override
    public final View getView() {
        return mRootLayout;
    }
    public final Context getContext() {
        return mContext;
    }
    @Override
    public final void init(LayoutInflater inflater, ViewGroup container) {
        this.mContext = inflater.getContext();
        mRootLayout = new RelativeLayout(mContext);
        mRootLayout.setId(R.id.root_content_id);
        mContentLayout = inflater.inflate(AnnotateUtils.getLayoutId(this), container, false);
        mToolbarLayout = new AppToolbar(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootLayout.addView(mContentLayout, layoutParams);
        addTitle(mToolbarLayout, initTitle(mToolbarLayout));
        initState();
        AnnotateUtils.injectViews(this);
        initView(mContentLayout);
    }
    /**
     * 添加头
     */
    private final void addTitle(AppToolbar appToolbar, boolean isShowTitle) {
        if (isShowTitle) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PxUtil.convertDIP2PX(mContext, 56));
            mRootLayout.addView(appToolbar, lp);
            if (appToolbar.isVertical()) {
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mContentLayout.getLayoutParams();
                rlp.addRule(RelativeLayout.BELOW, R.id.title_id);
                mContentLayout.setLayoutParams(rlp);
            } else {
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                appToolbar.getBackgroundView().setAlpha(0);
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mContentLayout.getLayoutParams();
                rlp.removeRule(RelativeLayout.BELOW);
            }
        }

    }

    /**
     * 初始化状态view
     */
    private final void initState() {
        mStateLayout=    new StateLayout(this);
        initStateLayout(mStateLayout);
      int stateParentId=  AnnotateUtils.getStateParentId(this);
        if (mStateLayout != null && stateParentId != 0) {
            ViewGroup stateViewGroup = mRootLayout.findViewById(stateParentId);
            if (stateViewGroup != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                stateViewGroup.addView(mStateLayout, layoutParams);
            }
        }
    }
    public final void setStateGone() {
        mStateLayout.setVisibility(View.GONE);
    }
    public final void setStateError() {
        mStateLayout.setStateError();
    }
    public final void setStateEmpty() {
        mStateLayout.setStateEmpty();
    }
    public final View findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }
}
