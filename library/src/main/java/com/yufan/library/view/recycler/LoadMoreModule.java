package com.yufan.library.view.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.yufan.library.R;
import com.yufan.library.inter.ICallBack;


/**
 * Created by mengfantao on 17/4/10.
 */

public class LoadMoreModule {

    private Context context;
    private View mFootView;
    private TextView mTextView;
    private PageInfo mPageInfo;
    private String mErrorMessage="加载失败!";
    private String mLoadingMessage="正在加载...";
    private String mNoneMessage="用力过猛，到底了！";
    private String mNoMoreMessage="无更多数据!";
    private AVLoadingIndicatorView mAVLoadingIndicatorView;


    public LoadMoreModule(Context context, PageInfo mPageInfo) {
        this.context=context;
        this.mPageInfo = mPageInfo;
        mPageInfo.setCallBack(new ICallBack() {
            @Override
            public void OnBackResult(Object... s) {
                notifyDataChanged();
            }
        });
        init();
    }


    public void setmErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public void setmLoadingMessage(String mLoadingMessage) {
        this.mLoadingMessage = mLoadingMessage;
    }

    public void setmNoneMessage(String mNoneMessage) {
        this.mNoneMessage = mNoneMessage;
    }

    public void setmNoMoreMessage(String mNoMoreMessage) {
        this.mNoMoreMessage = mNoMoreMessage;
    }

    private void init() {

         mFootView = LayoutInflater.from(context).inflate(R.layout.load_progress_foot, null, false);
       mTextView= (TextView) mFootView.findViewById(R.id.text_loading);
        mAVLoadingIndicatorView =(AVLoadingIndicatorView) mFootView.findViewById(R.id.indicatorview);
        mAVLoadingIndicatorView.setIndicator("BallBeatIndicator");
    }
    public View getFootView(){
        return mFootView;
    }
  public void notifyDataChanged(){
      switch (mPageInfo.getmState()){
          case PageInfo.PAGE_STATE_LOADING:
              mAVLoadingIndicatorView.setVisibility(View.VISIBLE);
              mTextView.setVisibility(View.INVISIBLE);
              mTextView.setText(mLoadingMessage);
              break;
          case PageInfo.PAGE_STATE_NONE:
              mAVLoadingIndicatorView.setVisibility(View.INVISIBLE);
              mTextView.setVisibility(View.VISIBLE);
              mTextView.setText(mNoneMessage);
              break;
          case PageInfo.PAGE_STATE_ERROR:
              mAVLoadingIndicatorView.setVisibility(View.INVISIBLE);
              mTextView.setVisibility(View.VISIBLE);
              mTextView.setText(mErrorMessage);
              break;
          case PageInfo.PAGE_STATE_NO_MORE:
              mAVLoadingIndicatorView.setVisibility(View.INVISIBLE);
              mTextView.setVisibility(View.VISIBLE);
              mTextView.setText(mNoMoreMessage);
              break;

          default:


              break;
      }
  }


}
