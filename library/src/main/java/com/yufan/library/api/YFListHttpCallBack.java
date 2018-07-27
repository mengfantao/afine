package com.yufan.library.api;

import com.yufan.library.base.BaseListVu;
import com.yufan.library.manager.DialogManager;
import com.yufan.library.view.recycler.PageInfo;

import java.util.List;

/**
 * Created by mengfantao on 18/4/20.
 */

public abstract class YFListHttpCallBack extends BaseHttpCallBack {

    private PageInfo pageInfo;
    private BaseListVu vu;
    public abstract void onEmpty();
    public abstract List onListSuccess(ApiBean mApiBean);
    public YFListHttpCallBack(BaseListVu vu) {
        this.pageInfo = vu.getRecyclerView().getPageManager();
        this.vu=vu;
    }

    private void setData(List listSource,List subList){
        if(subList!=null&&subList.size()>0){
            if(pageInfo.getCurrentIndex()==0){
                listSource.clear();
                listSource.addAll(subList);
            }else{
                listSource.addAll(subList);
            }
        }
    }


    @Override
    public void onSuccess(ApiBean mApiBean) {
        setData(pageInfo.getList(),onListSuccess(mApiBean));
        if(pageInfo.getList().size()==0){
            onEmpty();
            vu.setStateEmpty();
        }else {
            vu.setStateGone();
        }
        pageInfo.setPageState(PageInfo.PAGE_STATE_NONE);
        vu.getRecyclerView().getAdapter().notifyDataSetChanged();
        onFinish();
    }

    @Override
    public void onError(int id, Exception e) {
        vu.setStateError();
        pageInfo.setPageState(PageInfo.PAGE_STATE_ERROR);
        DialogManager.getInstance().toast(e.getMessage());
        onFinish();
    }

    @Override
    public void onFinish() {

    }
}
