package com.yufan.library.bean;


import com.yufan.library.R;

/**
 * Created by mengfantao on 17/9/11.
 */
public class Label {
    public String name;
    public int drawable= R.drawable.shape_type_storke_b8ccee;
    public int size=11;
    public int textColor= R.color.white;
    public int minWidth= 68;
    public int minHeight=30;

    public Label(String name) {
        this.name = name;
    }
}