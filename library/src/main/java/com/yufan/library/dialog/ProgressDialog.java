package com.yufan.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.yufan.library.R;


public class ProgressDialog extends Dialog {
private AnimationDrawable drawable;
	public ProgressDialog(Context context, String hint) {
		super(context, R.style.progress_dialog);
		getWindow().setGravity(Gravity.CENTER);
		setContentView(R.layout.load_progress);
		TextView text = (TextView) findViewById(R.id.tv_message);
		AVLoadingIndicatorView iv_loading= (AVLoadingIndicatorView) findViewById(R.id.indicatorview);
		setCanceledOnTouchOutside(false);
		if(!TextUtils.isEmpty(hint)){
			text.setText(hint);
		}
	}

	@Override
	public void setOnDismissListener(OnDismissListener listener) {
		super.setOnDismissListener(listener);
		if(drawable!=null){
			drawable.stop();
		}
	}
}
