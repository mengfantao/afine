/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yufan.library.browser;


import android.view.View;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.yufan.library.view.ptr.PtrClassicFrameLayout;
import com.yufan.library.widget.ScrollWebView;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 *业务接口
 */
public interface BrowserContract {
    interface View  {
        ScrollWebView getWebView();
        void onProgressChanged(WebView webView, int i);
        void onShowCustomView(android.view.View view, IX5WebChromeClient.CustomViewCallback customViewCallback);
        void onHideCustomView();
        void onReceivedTitle(WebView view, final String arg1);
        void onPageFinished(WebView view, String url);
        PtrClassicFrameLayout getPtr();
      void   onReceivedError(WebView view, int errorCode, String description, String failingUrl);
    }

    interface Presenter {
        void onBackPressed();
    }
}
