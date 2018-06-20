package com.yufan.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class ResizeLayout extends RelativeLayout {

    private static final String TAG = "ResizeLayout";

    private OnKeyboardChangedListener mChangedListener;

    private boolean isKeyboardShown = false;

    private final int THRESHOLD = 300;

    /**
     * @param context
     * @param attrs
     */
    public ResizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
     
    public interface OnKeyboardChangedListener {
        void onKeyboardShow(int keyHeight);
        void onKeyboardHide();
        void onKeyboardShowOver();
    }
 
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        Log.d(TAG, String.format("w=%d,h=%d,oldw=%d,oldh=%d", w, h, oldw, oldh));

        super.onSizeChanged(w, h, oldw, oldh);
        if (oldh - h > THRESHOLD && w==oldw) { //键盘弹出了
            onKeyBoardShow(oldh, h);
        } else if (h - oldh > THRESHOLD && w==oldw) { //键盘隐藏了
            onKeyBoardHide();
        }
    }

    protected void onKeyBoardHide() {
        isKeyboardShown = false;
        if (mChangedListener != null) {
            mChangedListener.onKeyboardHide();
        }
    }

    protected void onKeyBoardShow(int oldh, int newh) {
        isKeyboardShown = true;
        if (mChangedListener != null) {
            mChangedListener.onKeyboardShow(oldh - newh);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mChangedListener != null && isKeyboardShown) {
            mChangedListener.onKeyboardShowOver();
        }
    }

    @SuppressWarnings("unused")
    public boolean isKeyboardShowing() {
        return isKeyboardShown;
    }
 
    public void setOnKeyboardShowListener(OnKeyboardChangedListener listener) {
        mChangedListener = listener;
    }
    
    public OnKeyboardChangedListener getOnKeyboardShowListener() {
        return mChangedListener;
    }

    public static class BaseOnKeyboardChangedListener implements OnKeyboardChangedListener {
        @Override
        public void onKeyboardShow(int keyHeight) {

        }

        @Override
        public void onKeyboardHide() {

        }

        @Override
        public void onKeyboardShowOver() {

        }
    }
}