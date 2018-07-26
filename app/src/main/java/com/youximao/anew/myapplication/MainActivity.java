package com.youximao.anew.myapplication;

import android.os.Bundle;

import com.youximao.anew.myapplication.operation.dbtest.TestFragment;
import com.yufan.library.Global;
import com.yufan.library.base.BaseActivity;
import com.yufan.library.browser.BaseBrowserFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseBrowserFragment fragment=  new BaseBrowserFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Global.BUNDLE_KEY_BROWSER_URL,"http://www.baidu.com");
        fragment.setArguments(bundle);
        loadRootFragment(R.id.rl_fragment,fragment);
    }
}
