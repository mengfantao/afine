package com.yufan.library.util;

import android.text.TextUtils;
import android.widget.EditText;
import com.yufan.library.manager.DialogManager;

/**
 * Created by mengfantao on 17/12/15.
 */

public class InputChecker {

    /**
     * j检查手机号是否正确
     * @param phone
     * @return
     */
    public static boolean checkPhone(EditText phone){

        return true;



    }

    /**
     * j检查验证码是否为空
     * @param vcode
     * @return
     */
    public static boolean checkVCode(EditText vcode){
        if(TextUtils.isEmpty(vcode.getText())){
            DialogManager.getInstance().toast("验证码为空");
            vcode.requestFocus();
            return false;
        }
        return true;

    }
    /**
     * j检查密码是否
     * @param password
     * @return
     */
    public static boolean checkPassword(EditText password){
        String pwd = password.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            DialogManager.getInstance().toast("密码为空");
            password.requestFocus();
            return false;
        }
        if (pwd.length() < 6) {
            DialogManager.getInstance().toast("密码长度不够");
            password.requestFocus();
            return false;
        }
        if (pwd.length() > 18) {
            DialogManager.getInstance().toast("密码太长");
            password.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * 检查手机号，验证码，密码，如果不需要check可以传null
     * @param phone
     * @param vcode
     * @param password
     * @return
     */
    public static boolean checkAll(EditText phone, EditText vcode, EditText password){
        boolean checkPhone=true;
        boolean checkVCode=true;
        boolean checkPassword=true;
        if (!YFUtil.hasInternet()) {
            DialogManager.getInstance().toast("无网络");
            return false;
        }
        if(phone!=null){
            checkPhone=  checkPhone(phone);
        }
        if(vcode!=null){
            checkVCode=checkVCode(vcode);
        }
        if(password!=null){
            checkPassword=checkPassword(password);
        }
        return checkPhone&&checkVCode&&checkPassword;

    }

}
