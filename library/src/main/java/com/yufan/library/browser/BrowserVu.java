package com.yufan.library.browser;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.yufan.library.R;
import com.yufan.library.base.BaseVu;
import com.yufan.library.inject.FindLayout;
import com.yufan.library.view.ptr.PtrClassicFrameLayout;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

/**
 * Created by mengfantao on 18/7/26.
 */
@FindLayout(layoutName = "browser_layout", statusLayoutParentName = "rl_status")
public class BrowserVu extends BaseVu<BrowserContract.Presenter>  implements BrowserContract.View{
  private   TextView titleView;//标题
    private ViewGroup mViewParent;
    private PtrClassicFrameLayout mPtrClassicFrameLayout;
    private ProgressBar mPageLoadingProgressBar;
    private WebView mWebView;
    private View myVideoView;
    private View myNormalView;

    @Override
    public void initView(View view) {
        mViewParent = (ViewGroup) view.findViewById(R.id.webview);
        mPtrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.webviewprt);
        mPageLoadingProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        mPageLoadingProgressBar.setMax(100);
        mPageLoadingProgressBar.setProgressDrawable(getContext().getResources()
                .getDrawable(R.drawable.color_progressbar));
        if(!mPersenter.isPtrEnable()){
            mPtrClassicFrameLayout.setPullToRefresh(false);
        }
        mWebView = new WebView(getContext(), null);
        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));
    }


    @Override
    public void initStatusLayout(StateLayout stateLayout) {
        super.initStatusLayout(stateLayout);
    }

    @Override
    public boolean initTitle(AppToolbar appToolbar) {
        ImageView leftView=  appToolbar.creatLeftView(ImageView.class);
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersenter.onBackPressed();
            }
        });
        titleView= appToolbar.creatCenterView(TextView.class);
        titleView.setText("");
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersenter.onBackPressed();
            }
        });
        titleView.setLines(1);
        titleView.setEllipsize(TextUtils.TruncateAt.END);
        ImageView rightView=  appToolbar.creatRightView(ImageView.class);
        rightView.setVisibility(View.GONE);
        appToolbar.build();
        return true;
    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public void onProgressChanged(WebView webView, int i) {
        if (i >= 100) {
            mPageLoadingProgressBar.setVisibility(View.GONE);
        } else {
            mPageLoadingProgressBar.setVisibility(View.VISIBLE);
            mPageLoadingProgressBar.setProgress(i);

        }
    }

    @Override
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        mViewParent.removeView(mWebView);
        mViewParent.addView(view);
        myVideoView = view;
        myNormalView = mWebView;
    }

    @Override
    public void onHideCustomView() {
        if (myVideoView != null) {
            ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
            viewGroup.removeView(myVideoView);
            viewGroup.addView(myNormalView);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String arg1) {
        if (!TextUtils.isEmpty(arg1) ) {
            if (titleView!= null) {
                titleView.setText(arg1);
            }
        }
    }


    @Override
    public void onPageFinished(WebView view, String url) {

        if(mPtrClassicFrameLayout!=null){
            mPtrClassicFrameLayout.refreshComplete();
        }
        mPageLoadingProgressBar.setVisibility(View.GONE);

    }

    @Override
    public PtrClassicFrameLayout getPtr() {
        return mPtrClassicFrameLayout;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

    }
}
