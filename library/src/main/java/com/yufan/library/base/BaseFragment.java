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

public abstract class BaseFragment extends SupportFragment implements IFragment {
    protected AppToolbar toolbar;
    private HashMap<Integer,View> views = new HashMap<>();

    private RelativeLayout mRootLayout;
    private View mContent;
    private View mStateContent;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootLayout=    new RelativeLayout(getContext());
        mRootLayout.setId(R.id.root_content_id);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        mContent = layoutInflater.inflate(getLayoutId(), container, false);
        toolbar = new AppToolbar(getContext());
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootLayout.addView(mContent,layoutParams);
        addTitle(toolbar,initTitle(toolbar));
        initState();
        return mRootLayout;
    }
    /**
     *  添加头
     */
    private void addTitle(AppToolbar appToolbar, boolean isShowTitle){
        if(isShowTitle){
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, PxUtil.convertDIP2PX(getContext(), 56));
            mRootLayout.addView(appToolbar, lp);
            if(appToolbar.isVertical()){
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
                rlp.addRule(RelativeLayout.BELOW, R.id.title_id);
                mContent.setLayoutParams(rlp);
            }else {
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                appToolbar.getBackgroundView().setAlpha(0);
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
                rlp.removeRule(RelativeLayout.BELOW);
            }
        }

    }

    /**
     *  初始化状态view
     */
    private void initState(){
        if(initStateLayout()!=0&&initRootStateLayout()!=0){
            ViewGroup stateViewGroup= mRootLayout.findViewById(initRootStateLayout());
            if(stateViewGroup!=null){
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
             mStateContent = layoutInflater.inflate(initStateLayout(), stateViewGroup, false);

                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                stateViewGroup.addView(mStateContent,layoutParams);
            }
        }
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

    /**
     * 获取状态view
     * @return
     */
    protected final View getStateView(){
        return mStateContent;
    }
    /**
     * 状态view id
     */
    protected int  initStateLayout(){
        return 0;
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
        ImageView leftView = toolbar.creatLeftView(ImageView.class);
        leftView.setImageDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.ic_arrow_back_white));
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        toolbar.build();
        return true;
    }
    protected final RelativeLayout getRootLayout() {
        return mRootLayout;
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
            View tempView=  mContent.findViewById(id);
            views.put(id,tempView);
            return(T) tempView;
        }

    }

    @Override
    public void initData() {

    }
}
