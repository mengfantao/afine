package com.yufan.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebView;

import java.util.Map;

/**
 * Created by james on 2017/7/25.
 */

public class ScrollWebView extends WebView {
    private OnScrollChangeListener listener;
    private boolean isHaveload;
    private ViewGroup parentView;

    public ViewGroup getParentView() {
        return parentView;
    }

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }

    public boolean isHaveload() {
        return isHaveload;
    }

    public void setHaveload(boolean haveload) {
        isHaveload = haveload;
    }



    public ScrollWebView(Context context) {
        super(context);
    }

    public ScrollWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ScrollWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
    }

    public ScrollWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener listener) {

        this.listener = listener;

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (listener == null) {
            return;
        }
        listener.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);

    }
}
