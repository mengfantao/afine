package com.yufan.library.browser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.yufan.library.Global;
import com.yufan.library.base.BaseFragment;
import com.yufan.library.base.BaseRecycleAdapter;
import com.yufan.library.dialog.CommonDialogAdapter;
import com.yufan.library.filter.GifSizeFilter;
import com.yufan.library.inject.VuClass;
import com.yufan.library.util.SoftInputUtil;
import com.yufan.library.view.ptr.PtrFrameLayout;
import com.yufan.library.view.ptr.PtrHandler;
import com.yufan.library.webview.WVJBWebViewClient;
import com.yufan.library.widget.BottomMenu;
import com.yufan.library.widget.ScrollWebView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mengfantao on 18/7/26.
 */
@VuClass(BrowserVu.class)
public class BaseBrowserFragment extends BaseFragment<BrowserContract.View> implements BrowserContract.Presenter {
    private String TAG = "BrowserActivity";
    private ValueCallback<Uri> uploadFile;
    private String mIntentUrl;
    private boolean isTop;
    private int REQUEST_CODE_CHOOSE=8;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mIntentUrl = bundle.getString(Global.BUNDLE_KEY_BROWSER_URL);
        }
        init(vu.getWebView());
    }

    protected WebViewClient getWebViewClient() {

        return new BrowserWebViewClient(vu.getWebView());
    }
    private void init(ScrollWebView webView) {

        webView.setWebViewClient(getWebViewClient());
        webView.setOnScrollChangeListener(new ScrollWebView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (l <= 10 && t <= 10 && oldl <= 10) {
                    isTop = true;
                } else {
                    isTop = false;
                }
            }
        });
        vu.getPtr().setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (vu.getWebView() != null) {
                    vu.getWebView().reload();
                }
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return isTop && isPtrEnable();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                vu.onProgressChanged(webView, i);

            }


            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2, JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            CustomViewCallback callback;
            //

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
                vu.onShowCustomView(view, customViewCallback);
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                vu.onHideCustomView();
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2, JsResult arg3) {
                return super.onJsAlert(null, "www.baidu.com", "aa", arg3);
            }

            /**
             * 对应js 的通知弹框 ，可以用来实现js 和 android之间的通信
             */


            @Override
            public void onReceivedTitle(WebView view, final String arg1) {
                super.onReceivedTitle(view, arg1);
                Log.i(TAG, "webpage title is " + arg1);
                vu.onReceivedTitle(view, arg1);
            }

            private void openFileChooseProcess() {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"),
                        0);
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("test", "openFileChooser 3");
                BaseBrowserFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }



        });

        webView.setDownloadListener(new DownloadListener() {
            private boolean isDownloadAction;

            @Override
            public void onDownloadStart(final String arg0, String arg1, String arg2,
                                        String arg3, long arg4) {
                TbsLog.d(TAG, "url: " + arg0);
                if (isDownloadAction == true) {
                    isDownloadAction = false;
                    return;
                }
                isDownloadAction = true;
                Uri uri = Uri.parse(arg0);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Bundle data = new Bundle();
        data.putBoolean("standardFullScreen", false);
        //true表示标准全屏，false表示X5全屏；不设置默认false，
        //data.putBoolean("supportLiteWnd", false);
        //false：关闭小窗；true：开启小窗；不设置默认true，
        data.putInt("DefaultVideoScreen", 2);
        //1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
        //
        webView.setVerticalScrollBarEnabled(false);
        if (webView.getX5WebViewExtension() != null) {
            webView.getX5WebViewExtension().setScrollBarFadingEnabled(false);
            webView.getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(getContext().getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();
        if (mIntentUrl == null) {
            throw new IllegalArgumentException("intent data can not null");
        } else {
            Log.d("browser","load:"+mIntentUrl);
            webView.loadUrl(mIntentUrl);
        }
        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
       loadCookie(CookieManager.getInstance());
    }
    protected void loadCookie(CookieManager cookie) {
        CookieSyncManager.createInstance(getActivity());
        cookie.setAcceptCookie(true);
        cookie.removeSessionCookie();// 移除旧的[可以省略]
        cookie.setCookie(mIntentUrl, "token=" +  "");
        cookie.setCookie(mIntentUrl, "channelId=" + "");
        cookie.setCookie(mIntentUrl, "type=2");
        cookie.setCookie(mIntentUrl, "sid=" + "");
        cookie.setCookie(mIntentUrl, "uniqueId=" +"");
        cookie.setCookie(mIntentUrl, "apiVersion=" + "");
        cookie.setCookie(mIntentUrl, "hardware=" + "");
        cookie.setCookie(mIntentUrl, "cpu=" + "");
        cookie.setCookie(mIntentUrl, "cpu_abi=" + Build.CPU_ABI);
        cookie.setCookie(mIntentUrl, "product_cpu_abi=" + "");
        CookieSyncManager.getInstance().sync();
    }



    @Override
    public boolean onBackPressedSupport() {
        SoftInputUtil.closeKeybordForActivity(getActivity());
        if (vu.getWebView().canGoBack()) {
            vu.getWebView().goBack();
            return true;
        }
        return super.onBackPressedSupport();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TbsLog.d(TAG, "onActivityResult, requestCode:" + requestCode
                + ",resultCode:" + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null || resultCode != RESULT_OK ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

        }

    }

    private void upPhoto() {
        // 修改头像
        List<String> nameList = new ArrayList<>();
        nameList.add( "选择照片");
        final BottomMenu dialog = new BottomMenu(getActivity());
        dialog.setAdapter(new CommonDialogAdapter(nameList, new BaseRecycleAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                switch (postion) {
                    case 0:
                        Matisse.from(BaseBrowserFragment.this)
                                .choose(MimeType.allOf())
                                .countable(true)
                                .maxSelectable(1)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.8f)
                                .captureStrategy(//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                                        new CaptureStrategy(true, "com.youximao.anew.myapplication.fileprovider"))
                                .imageEngine(new GlideEngine())
                                .forResult(REQUEST_CODE_CHOOSE);
                        break;
                    case 1:

                        break;
                    case -1:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, int postion) {
                return false;
            }
        }));
        dialog.show();
    }


    @Override
    public void onDestroy() {
        if (vu.getWebView() != null)
            vu.getWebView().destroy();
        super.onDestroy();
    }

    @Override
    public void onLoadMore(int index) {

    }

    @Override
    public void onRefresh() {
        if (vu.getWebView() != null) {
            vu.getWebView().reload();
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean isPtrEnable() {
        return true;
    }


    class BrowserWebViewClient extends WVJBWebViewClient {
        private boolean isError = false;
        /**
         * @param webView
         */
        public BrowserWebViewClient(WebView webView) {

            super(webView, new WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {

                }
            });


            //返回
            registerHandler("web_backPrevious", new WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    if (getActivity() != null) {
                        onBackPressed();
                    }
                }
            });


        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(isError){
                vu.setStateError();
            }else {
                vu.setStateGone();
            }
            vu.onPageFinished(view, url);
        }
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();  // 接受所有网站的证书
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            isError=true;
          vu.onReceivedError(view,errorCode,description,failingUrl);
        }

    }


}
