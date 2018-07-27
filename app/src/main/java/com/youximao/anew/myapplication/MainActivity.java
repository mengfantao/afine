package com.youximao.anew.myapplication;

import android.os.Bundle;

import com.yufan.library.Global;
import com.yufan.library.base.BaseActivity;
import com.yufan.library.browser.BrowserFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BrowserFragment fragment=  new BrowserFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Global.BUNDLE_KEY_BROWSER_URL,"http://www.baidu.com");
        fragment.setArguments(bundle);
        loadRootFragment(R.id.rl_fragment,fragment);
    }
}
