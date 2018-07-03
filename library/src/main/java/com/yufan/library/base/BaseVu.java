package com.yufan.library.base;

import android.content.Context;
import android.support.annotation.IdRes;
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
import com.yufan.library.inter.IVu;
import com.yufan.library.inter.Vu;
import com.yufan.library.util.PxUtil;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

import java.util.HashMap;

/**
 * Created by mengfantao on 18/6/28.
 * vu view模块基础类,
 */

public abstract class BaseVu implements Vu {

    private final HashMap<Integer, View> mViews = new HashMap<>();
    private RelativeLayout mRootLayout;
    private View mContentLayout;
    private StateLayout mStateLayout;
    protected AppToolbar mToolbarLayout;
    private Context mContext;
    private IVu iVu;



    /**
     * 子类需要重写，初始化头
     */
    @Override
    public boolean initTitle(AppToolbar toolbar) {
        return false;
    }

    /**
     * 子类需要重写，初始化状态view
     */
    @Override
    public void initStateLayout(StateLayout stateLayout) {
      View  errorView=View.inflate(getContext(), R.layout.layout_error,null);
        View emptyView=View.inflate(getContext(),R.layout.layout_empty,null);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iVu!=null){
                    iVu.onRefresh();
                }
            }
        });
        stateLayout.setEmptyView(emptyView);
        stateLayout.setErrorView(errorView);
    }


    /**
     * 子类需要重写，要覆盖viewgroup id
     */
    @Override
    public int getRootStateLayout() {
        return R.id.root_content_id;
    }
    @Override
    public final View getView() {
        return mRootLayout;
    }
    public final Context getContext() {
        return mContext;
    }

    public final IVu getiVu() {
        return iVu;
    }

    public final void setiVu(IVu iVu) {
        this.iVu = iVu;
    }

    @Override
    public final void init(LayoutInflater inflater, ViewGroup container) {
        this.mContext = inflater.getContext();
        mRootLayout = new RelativeLayout(mContext);
        mRootLayout.setId(R.id.root_content_id);
        mContentLayout = inflater.inflate(getLayoutId(), container, false);
        mToolbarLayout = new AppToolbar(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootLayout.addView(mContentLayout, layoutParams);
        addTitle(mToolbarLayout, initTitle(mToolbarLayout));
        initState();
        initView(mRootLayout);
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
        if (mStateLayout != null && getRootStateLayout() != 0) {
            ViewGroup stateViewGroup = mRootLayout.findViewById(getRootStateLayout());
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
    public final void setStateVisibility() {
        mStateLayout.setVisibility(View.VISIBLE);
    }
    public final void setStateError() {
        mStateLayout.setStateError();
    }
    public final void setStateEmpty() {
        mStateLayout.setStateEmpty();
    }


    /**
     * findview
     *
     * @param id
     * @return
     */
    protected final TextView findTextView(@IdRes int id) {
        return $(id, TextView.class);
    }

    protected final ImageView findImageView(@IdRes int id) {
        return $(id, ImageView.class);
    }

    protected final CheckBox findCheckBox(@IdRes int id) {
        return $(id, CheckBox.class);
    }

    protected final EditText findEditText(@IdRes int id) {
        return $(id, EditText.class);
    }

    protected final Button findButton(@IdRes int id) {
        return $(id, Button.class);
    }

    protected final View findView(@IdRes int id) {
        return $(id, View.class);
    }

    protected final <T> T $(@IdRes int id, Class<T> clazz) {
        if (mViews.containsKey(id)) {
            return (T) mViews.get(id);
        } else {
            View tempView = mContentLayout.findViewById(id);
            mViews.put(id, tempView);
            return (T) tempView;
        }

    }
}
