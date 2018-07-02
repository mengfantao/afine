package com.yufan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.yufan.library.R;
import com.yufan.library.base.BaseVu;


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
public class StateLayout extends RelativeLayout {
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    Class vuClass;
    public StateLayout(BaseVu vu) {
        this(vu.getContext(), null);
       vuClass=vu.getClass();
    }
    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    int[] layouts = new int[]{R.layout.layout_loading, R.layout.layout_error, R.layout.layout_empty};

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }



}
