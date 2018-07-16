package com.youximao.anew.myapplication.operation.dbtest;

import android.text.TextUtils;
import android.widget.TextView;

import com.youximao.anew.myapplication.Person;
import com.youximao.anew.myapplication.R;
import com.yufan.library.base.BaseRecycleAdapter;

import java.util.List;

public class PersonAdapter extends BaseRecycleAdapter{

        public PersonAdapter(List datas) {
            super(datas);
        }



    @Override
        protected void bindData(BaseRecycleAdapter.BaseViewHolder holder, int position) {
         Person person= (Person) datas.get(position);
         TextView tv_name= (TextView) holder.getView(R.id.tv_name);
        TextView tv_age= (TextView)   holder.getView(R.id.tv_age);
        tv_age.setText("年龄："+person.getAge());
        tv_name.setText("姓名："+person.getName());
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_person;
        }
    }