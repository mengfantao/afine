package com.yufan.library.api;

import com.yufan.library.base.BaseListVu;
import com.yufan.library.manager.DialogManager;
import com.yufan.library.manager.PageManager;
import com.yufan.library.view.recycler.YFRecyclerView;

import java.util.List;

/**
 * Created by mengfantao on 18/4/20.
 */

public abstract class YFListHttpCallBack extends BaseHttpCallBack {

    private PageManager  pageManager;
    private BaseListVu vu;
    public abstract void onEmpty();
    public abstract List onListSuccess(ApiBean mApiBean);
    public YFListHttpCallBack(BaseListVu vu) {
        this.pageManager = vu.getRecyclerView().getPageManager();
        this.vu=vu;
    }

    private void setData(List listSource,List subList){
        if(subList!=null&&subList.size()>0){
            if(pageManager.getCurrentIndex()==0){
                listSource.clear();
                listSource.addAll(subList);
            }else{
                listSource.addAll(subList);
            }
        }
    }


    @Override
    public void onSuccess(ApiBean mApiBean) {
        setData(pageManager.getList(),onListSuccess(mApiBean));
        if(pageManager.getList().size()==0){
            onEmpty();
            vu.setStateEmpty();
        }else {
            vu.setStateGone();
        }
        pageManager.setPageState(PageManager.PAGE_STATE_NONE);
        vu.getRecyclerView().getAdapter().notifyDataSetChanged();
        onFinish();
    }

    @Override
    public void onError(int id, Exception e) {
        vu.setStateError();
        pageManager.setPageState(PageManager.PAGE_STATE_ERROR);
        DialogManager.getInstance().toast(e.getMessage());
        onFinish();
    }

    @Override
    public void onFinish() {

    }
}
