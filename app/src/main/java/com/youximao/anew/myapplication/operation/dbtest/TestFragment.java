package com.youximao.anew.myapplication.operation.dbtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.youximao.anew.myapplication.PlainDBManager;
import com.youximao.anew.myapplication.bean.Person;
import com.yufan.library.base.BaseListFragment;
import com.yufan.library.manager.PageManager;


import java.util.List;


/**
 * Created by mengfantao on 18/7/2.
 */

public class TestFragment extends BaseListFragment<TestVu> implements TaskDetailContract.Presenter{


    private PlainDBManager plainDBManager;

    @Override
    public void onRefresh() {
        vu.setStateGone();
        List<Person>persons=  plainDBManager.getPersonListData();
        vu.getRecyclerViewModel().getList().clear();
        vu.getRecyclerViewModel().getList().addAll(persons);
        vu.getRecyclerViewModel().getAdapter().notifyDataSetChanged();
        vu.getRecyclerViewModel().getPTR().refreshComplete();
        vu.getRecyclerViewModel().getPageManager().setPageState(PageManager.PAGE_STATE_NONE);
    }

    @Override
    protected Class<TestVu> getVuClass() {

        return TestVu.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plainDBManager=new   PlainDBManager(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vu.getRecyclerViewModel().setAdapter(new PersonAdapter(vu.getRecyclerViewModel().getList()));

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
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

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