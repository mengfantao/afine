package com.youximao.anew.myapplication;

import android.os.Bundle;

import com.youximao.anew.myapplication.fragment.TestFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRootFragment(R.id.rl_fragment,new TestFragment());
    }
}
