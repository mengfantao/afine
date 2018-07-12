package com.youximao.anew.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.youximao.anew.myapplication.Person;
import com.youximao.anew.myapplication.PlainDBManager;
import com.youximao.anew.myapplication.adapter.PersonAdapter;
import com.youximao.anew.myapplication.vu.TestVu;
import com.yufan.library.base.BaseListFragment;
import com.yufan.library.base.BaseRecycleAdapter;
import com.yufan.library.inter.ICallBack;
import com.yufan.library.manager.DialogManager;
import com.yufan.library.manager.PageManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * Created by mengfantao on 18/7/2.
 */

public class TestFragment extends BaseListFragment<TestVu>{


    private PlainDBManager plainDBManager;

    @Override
    public void onRefresh() {
        vu.setStateGone();
        EventBus.getDefault().post(new Person("query","",0));

    }

    @Override
    protected Class<TestVu> getVuClass() {

        return TestVu.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        plainDBManager=new   PlainDBManager(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vu.getRecyclerViewModel().setAdapter(new PersonAdapter(vu.getRecyclerViewModel().getList()));

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUI(List<Person> persons) {
        vu.getRecyclerViewModel().getList().clear();
        vu.getRecyclerViewModel().getList().addAll(persons);
        vu.getRecyclerViewModel().getAdapter().notifyDataSetChanged();
        vu.getRecyclerViewModel().getPTR().refreshComplete();
        vu.getRecyclerViewModel().getPageManager().setPageState(PageManager.PAGE_STATE_NONE);
    }

    private long diffTime=0;
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void eventDB(final Person person) {
    List<Person>list=null;
        long  startTime=System.currentTimeMillis();
        switch (person.getType()){
            case "insert":
                plainDBManager.addPersonData(person);
                break;
            case "delete":
                plainDBManager.delPersonByAge("20");
                break;
            case "update":
                plainDBManager.updatePersonByName(person.name,person.age);
                break;
            case "batchinsert":

                plainDBManager.addPersons(10000,"游戏猫",20);

                break;
            case "query":
                list=  plainDBManager.getPersonListData();
                break;
        }
        diffTime=System.currentTimeMillis()-startTime;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogManager.getInstance().toast(person.getType()+"耗时"+diffTime+"ms");
            }
        });
        EventBus.getDefault().post(list);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        plainDBManager.closeDB();
    }

    @Override
    public void onLoadMore(int index) {
        EventBus.getDefault().post(new Person("query","",0));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
