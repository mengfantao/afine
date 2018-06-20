package com.yufan.library.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

public class CustomImageView extends SimpleDraweeView {
    private Context context;

    public CustomImageView(Context context) {
        super(context);
        this.context = context;
    }



    public CustomImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        this.context = context;
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }
public void loadImageURL(String url){
    if(TextUtils.isEmpty(url)){
        return;
    }
    setImageURI(Uri.parse(url));
}


}
