package com.youximao.anew.myapplication.operation.dbtest;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.youximao.anew.myapplication.R;
import com.youximao.anew.myapplication.bean.Person;
import com.yufan.library.base.BaseListVu;
import com.yufan.library.base.BasePresenter;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

/**
 * Created by mengfantao on 18/7/2.
 */

public class TestVu extends BaseListVu <DbTestContract.Presenter> implements DbTestContract.View{

    @Override
    public int getRootStateLayout() {
        return R.id.rl_content;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }


    @Override
    public void initStateLayout(StateLayout stateLayout) {
        setStateEmpty();
        stateLayout.getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mPersenter.onRefresh();
            }
        });

    }


    @Override
    public boolean initTitle(AppToolbar toolbar) {
        toolbar.creatCenterView(TextView.class).setText("WCDB数据库");

      TextView tvInsert=  toolbar.creatRightView(TextView.class);
        tvInsert.setText("插入");
        tvInsert.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              showInsertDialog();
          }
      });
        TextView tvBatchInsert=  toolbar.creatRightView(TextView.class);
        tvBatchInsert.setText("批量插入");
        tvBatchInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersenter.batchinsert();
                mPersenter.onRefresh();
            }
        });
        TextView tvUpdate=  toolbar.creatRightView(TextView.class);
        tvUpdate.setText("更改");
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateDialog();
            }
        });
        TextView tvDel=  toolbar.creatRightView(TextView.class);
        tvDel.setText("删除");
        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersenter.delete();
                mPersenter.onRefresh();

            }
        });
        TextView tvQuery=  toolbar.creatRightView(TextView.class);
        tvQuery.setText("查询");
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPersenter.onRefresh();
            }
        });
        toolbar.build();
        return true;
    }

    private void showInsertDialog(){
        ViewGroup extViewmoney = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.alertext_form, null);
        final EditText etName = (EditText) extViewmoney.findViewById(R.id.et_name);
        final EditText etAge = (EditText) extViewmoney.findViewById(R.id.et_age);
        AlertView mAlertViewExtMoney = new AlertView("请输入", null, "取消", null, new String[]{"确定"}, getContext(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if(position==0){
                    if(!TextUtils.isEmpty(etName.getText())&&!TextUtils.isEmpty(etAge.getText())){
                        try {
                            Person person= new Person();
                            person.setAge(Integer.parseInt(etAge.getText().toString()));
                            person.setName(etName.getText().toString());
                            mPersenter.insert(person);
                            mPersenter.onRefresh();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        });
        mAlertViewExtMoney.addExtView(extViewmoney);
        mAlertViewExtMoney.show();
    }
    private void showUpdateDialog(){
        ViewGroup extViewmoney = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.alertext_form, null);
        final EditText etName = (EditText) extViewmoney.findViewById(R.id.et_name);
        final EditText etAge = (EditText) extViewmoney.findViewById(R.id.et_age);
        final AlertView mAlertViewExtMoney = new AlertView("请输入", null, "取消", null, new String[]{"确定"}, getContext(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if(position==0){
                    try {
                        int age=0;
                        if(!TextUtils.isEmpty(etAge.getText().toString())){
                           age= Integer.parseInt(etAge.getText().toString());
                        }
                       Person person= new Person();
                        person.setAge(age);
                        person.setName(etName.getText().toString());
                        mPersenter.update(person);
                        mPersenter.onRefresh();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        mAlertViewExtMoney.addExtView(extViewmoney);
        mAlertViewExtMoney.show();
    }


}
