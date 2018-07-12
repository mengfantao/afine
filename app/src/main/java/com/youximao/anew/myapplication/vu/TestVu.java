package com.youximao.anew.myapplication.vu;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.youximao.anew.myapplication.Person;
import com.youximao.anew.myapplication.R;
import com.yufan.library.base.BaseListVu;
import com.yufan.library.base.BaseRecycleAdapter;
import com.yufan.library.base.BaseVu;
import com.yufan.library.inter.ICallBack;
import com.yufan.library.widget.AppToolbar;
import com.yufan.library.widget.StateLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by mengfantao on 18/7/2.
 */

public class TestVu extends BaseListVu {

    @Override
    public int getRootStateLayout() {
        return R.id.rl_content;
    }



    @Override
    public void initStateLayout(StateLayout stateLayout) {
        setStateEmpty();
        stateLayout.getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getVuCallBack().onRefresh();
            }
        });

    }

    @Override
    public boolean initTitle(AppToolbar toolbar) {
        toolbar.creatCenterView(TextView.class).setText("WCDB数据库");

      TextView textView=  toolbar.creatRightView(TextView.class);
      textView.setText("插入");
      textView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              showInsertDialog();
          }
      });
        TextView textView1=  toolbar.creatRightView(TextView.class);
        textView1.setText("批量插入");
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new Person("batchinsert","",0));
            }
        });
        TextView textView2=  toolbar.creatRightView(TextView.class);
        textView2.setText("更改");
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdateDialog();
            }
        });
        TextView textView3=  toolbar.creatRightView(TextView.class);
        textView3.setText("删除");
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new Person("delete","",0));
            }
        });
        TextView textView4=  toolbar.creatRightView(TextView.class);
        textView4.setText("查询");
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new Person("query","",0));
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
                            EventBus.getDefault().post(new Person("insert",etName.getText().toString(),Integer.parseInt(etAge.getText().toString())));
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
        AlertView mAlertViewExtMoney = new AlertView("请输入", null, "取消", null, new String[]{"确定"}, getContext(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if(position==0){
                    try {
                        int age=0;
                        if(!TextUtils.isEmpty(etAge.getText().toString())){
                           age= Integer.parseInt(etAge.getText().toString());
                        }
                        EventBus.getDefault().post(new Person("update",etName.getText().toString(),age));
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
