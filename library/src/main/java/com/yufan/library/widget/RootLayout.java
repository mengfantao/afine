package com.yufan.library.widget;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yufan.library.R;
import com.yufan.library.util.PxUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 状态布局 的 核心是状态和界面的对应关系
 * 0 此控件有 4 种状态 : 正在加载 加载成功 加载失败 服务器没数据
 * 1 外界可以指定状态 和 界面的对应关系（可以利用xml的自定义属性指定，也可以通过代码指定(view 或者 layout)，如果未指定此控件提供默认的）
 * 2 外界可以通过状态获取界面
 * 3 外界可以指定状态，用来切换View
 * 4 外界可以获得当前的状态
 * <p/>
 * <p/>
 * 编程技巧！！ 写出简单&& 无错 的复用性强代码
 * <p/>
 * 用数组代替判断逻辑！
 */
public class RootLayout extends RelativeLayout {
    public static final int STATE_NONE = -1;
    public static final int STATE_LOADING = 0;
    public static final int STATE_SUCCESS = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    private int currentState = STATE_NONE;
    private final RelativeLayout mContent;
    private View[] views = new View[4];
    private OnStateLayoutClickListener onStateLayoutClickListener;

    @IntDef({STATE_NONE,STATE_LOADING, STATE_SUCCESS, STATE_ERROR,STATE_EMPTY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State{}
    public RootLayout(Context context) {
        this(context, null);
    }
    public RootLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    int[] layouts = new int[]{R.layout.layout_loading, R.layout.layout_loading, R.layout.layout_error, R.layout.layout_empty};

    public RootLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContent=    new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mContent,layoutParams);
        for (int i = 0;  i < layouts.length; i++) {
            setStateLayout( i, layouts[i]);
        }
        setBackgroundColor(getResources().getColor(R.color.white));
    }
    public void addTitle(AppToolbar appToolbar,boolean isShowTitle){
        if(isShowTitle){
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, PxUtil.convertDIP2PX(getContext(), 56));
            addView(appToolbar, lp);
            if(appToolbar.isVertical()){
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                LayoutParams rlp = (LayoutParams) mContent.getLayoutParams();
                rlp.addRule(RelativeLayout.BELOW, R.id.title_id);
                mContent.setLayoutParams(rlp);
            }else {
                appToolbar.getBackgroundView().setBackgroundResource(R.drawable.shape_title_backgound);
                appToolbar.getBackgroundView().setAlpha(0);
                LayoutParams rlp = (LayoutParams) mContent.getLayoutParams();
                rlp.removeRule(RelativeLayout.BELOW);
            }


        }

    }

    public void setStateLayout(@State int state,@LayoutRes int layout) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View  v = layoutInflater.inflate(layout, this, false);
        setStateView(state, v);
    }

    private void setStateView(@State int state,@NonNull View view) {
        // 如果之前调过此方法，设置过某状态对应的View，应该先移除再添加
        if(views[state] !=null){
            mContent.removeView(views[state]);
        }
        views[state] = view;
        mContent.addView(view);
        // 如果和当前状态不相符则隐藏
        view.setVisibility(state == currentState ? View.VISIBLE : View.GONE);
        if (state == STATE_EMPTY || state == STATE_ERROR) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onStateLayoutClickListener != null) {
                        onStateLayoutClickListener.onClick();
                    }
                }
            });
        }
    }


    public @NonNull View getStateView(@State  int state) {
        return views[state];
    }

    public void setState(@State int state) {
        currentState = state;
        for (int i = 0; i < views.length; i++) {
            views[i].setVisibility(i == state ? View.VISIBLE : View.GONE);
        }
    }

    public @State  int getState() {
        return currentState;
    }

    public void setOnStateLayoutClickListener(OnStateLayoutClickListener onStateLayoutClickListener) {

        this.onStateLayoutClickListener = onStateLayoutClickListener;
    }

    public interface OnStateLayoutClickListener {
        void onClick();
    }

}
