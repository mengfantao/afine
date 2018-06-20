package com.yufan.library.manager;


import com.yufan.library.inter.ICallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mengfantao on 17/4/7.
 */

public class PageManager {
    //页面请求网络状态
    public static final int PAGE_STATE_NONE=0;//加载更多，空闲状态
    public static final int PAGE_STATE_LOADING=1;//加载中
    public static final int PAGE_STATE_NO_MORE=2;//无更多数据
    public static final int PAGE_STATE_ERROR=3;//加载失败
    private int mState=0;
    private int mCurrentIndex=0;
    private ICallBack callBack;
    private List list=new ArrayList();

    public void setCallBack(ICallBack callBack) {

        this.callBack = callBack;
    }

    public PageManager() {


    }

    public List getList() {
        return list;
    }

    public boolean isIdle(){
        return mState!=PAGE_STATE_LOADING;
    }
    public void resetIndex(){
        mCurrentIndex=0;
    }


  public int   getCurrentIndex(){
        return mCurrentIndex;

    }

    public int next(){
        return mCurrentIndex++;
    }

    public int getmState() {
        return mState;
    }

    public void setPageState(int pageState){
        mState=pageState;
        if(callBack!=null){
            callBack.OnBackResult(pageState);
        }

    }
}
