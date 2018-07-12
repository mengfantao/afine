package com.yufan.library.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.yufan.library.inter.IActivity;
import com.yufan.library.manager.DialogManager;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by mengfantao on 18/3/16.
 */

public abstract class BaseActivity extends SupportActivity implements IActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogManager.getInstance().init(this);
    }
}
