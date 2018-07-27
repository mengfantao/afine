package com.youximao.anew.myapplication.operation.dbtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.youximao.anew.myapplication.PlainDBManager;
import com.youximao.anew.myapplication.bean.Person;
import com.yufan.library.base.BaseListFragment;
import com.yufan.library.inject.VuClass;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by mengfantao on 18/7/2.
 */
@VuClass(TestVu.class)
public class TestFragment extends BaseListFragment<TestVu> implements DbTestContract.Presenter {
    private PlainDBManager plainDBManager;

    @Override
    public void onRefresh() {
        Observable.create(new ObservableOnSubscribe<List<Person>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Person>> e) throws Exception {
                List<Person> persons = plainDBManager.getPersonListData();
                e.onNext(persons);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(@NonNull List<Person> persons) throws Exception {
                        getVu().setData(persons);
                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plainDBManager = new PlainDBManager(getContext());
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
        plainDBManager.updatePersonByName(person.name, person.age);
    }

    @Override
    public void batchinsert() {
        plainDBManager.addPersons(10000, "游戏猫", 20);
    }
}
