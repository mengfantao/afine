package com.yufan.library.view.ptr;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

import com.yufan.library.view.ptr.header.MaterialHeader;
import com.yufan.library.view.ptr.header.StoreHouseHeader;
import com.yufan.library.view.ptr.header.StoreHousePath;


public class PtrClassicFrameLayout extends PtrFrameLayout {

    private StoreHouseHeader mPtrClassicHeader;


    public PtrClassicFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mPtrClassicHeader = new StoreHouseHeader(getContext());
        mPtrClassicHeader.initWithString("JingJing");
        mPtrClassicHeader.setTextColor(Color.BLACK);
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
        disableWhenHorizontalMove(true);
    }


}
