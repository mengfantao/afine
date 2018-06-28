package com.yufan.library.manager;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yufan.library.R;
import com.yufan.library.dialog.ProgressDialog;
import com.yufan.library.util.ToastUtil;

/**
 * Created by mengfantao on 18/3/28.
 */

public class DialogManager {
    private static DialogManager instance=new DialogManager();
    private ProgressDialog mProgressDialog;//loading dialog
    private Activity mActivity;
    private MaterialDialog mDialog;//对话框loading dialog

    private DialogManager() {
    }
    public void init(Activity activity){
        mActivity=activity;
    }

    private boolean isAttach() {
        return mActivity != null && !mActivity.isDestroyed();
    }

    public static DialogManager getInstance(){
        return instance;
    }

    public final void toast(String hint) {
        if(TextUtils.isEmpty(hint)){
            return ;
        }
        ToastUtil.showToast(mActivity, hint, Toast.LENGTH_SHORT);
    }
    public final void toast(int strId){
        String text = mActivity.getResources().getString(strId);
        toast(text);
    }

    /**
     *
     * @param hint
     * @param success   是否成功dialog框
     */
    public final void toastPostSuccess(String hint, boolean success){
        if(TextUtils.isEmpty(hint)) {
            if(success){hint = "操作成功";}
            else{hint = "操作失败";}
        }
        Drawable drawable = null;
        if(success){
           drawable  = mActivity.getResources().getDrawable(R.drawable.post_ok);

        }else{
           drawable = mActivity.getResources().getDrawable(R.drawable.post_error);
        }
        ToastUtil.normal(mActivity, hint, drawable).show();
    }

    /**
     *
     * @param strId
     * @param success 是否成功dialog框
     */
    public final void toastPostSuccess(@StringRes int strId, boolean success){
        String text = mActivity.getResources().getString(strId);
        toastPostSuccess(text, success);
    }

    ////dialog
    public final void showLoadingDialog() {
        String hint = "加载中...";
        showLoadingDialog(hint);
    }

    public final void showLoadingDialog(String hint) {
        if (isAttach()) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                return;
            }
            mProgressDialog = new ProgressDialog(mActivity, hint);
            mProgressDialog.show();
        }
    }
    public final void dismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


    public final void showDialog() {
        String hint = "加载中...";
        showDialog(hint);
    }

    public final void showDialog(String hint) {
        if (isAttach()) {
            if (mDialog != null && mDialog.isShowing()) {
                return;
            }
            mDialog = new MaterialDialog.Builder(mActivity)
//                    .title(R.string.progress_dialog)
                    .content(hint)
                    .progress(true, 0)
                    .progressIndeterminateStyle(false)
                    .show();
        }
    }

}
