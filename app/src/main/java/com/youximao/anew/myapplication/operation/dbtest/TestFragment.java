package com.youximao.anew.myapplication.operation.dbtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.youximao.anew.myapplication.PlainDBManager;
import com.youximao.anew.myapplication.bean.Person;
import com.yufan.library.api.ApiBean;
import com.yufan.library.api.BaseHttpCallBack;
import com.yufan.library.api.YFListHttpCallBack;
import com.yufan.library.base.BaseListFragment;
import com.yufan.library.inject.VuClass;
import com.yufan.library.manager.PageManager;


import java.util.List;


/**
 * Created by mengfantao on 18/7/2.
 */
@VuClass(TestVu.class)
public class TestFragment extends BaseListFragment<TestVu> implements DbTestContract.Presenter{
    private PlainDBManager plainDBManager;
    @Override
    public void onRefresh() {
        List<Person>persons=  plainDBManager.getPersonListData();
       vu.setDate(persons);
        YFListHttpCallBack yfListHttpCallBack=  new YFListHttpCallBack(vu){
            @Override
            public void onEmpty() {
            }
            @Override
            public List onListSuccess(ApiBean mApiBean) {
                return null;
            }

        };

        BaseHttpCallBack callBack=new BaseHttpCallBack(vu) {
            @Override
            public void onSuccess(ApiBean mApiBean) {
            }
            @Override
            public void onError(int id, Exception e) {

            }

            @Override
            public void onFinish() {

            }
        };
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plainDBManager=new   PlainDBManager(getContext());
        vu.getRecyclerView().setAdapter(new PersonAdapter(vu.getRecyclerView().getList()));

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        plainDBManager.closeDB();
    }

    @Override
    public void onLoadMore(int index) {
        onRefresh();
    }

    @Override
    public void insert(Person person) {
        plainDBManager.addPersonData(person);
    }
    @Override
    public void delete() {
        plainDBManager.delPersonByAge("20");
    }

    @Override
    public void update(Person person) {
        plainDBManager.updatePersonByName(person.name,person.age);
    }

    @Override
    public void batchinsert() {
        plainDBManager.addPersons(10000,"游戏猫",20);
    }
}
