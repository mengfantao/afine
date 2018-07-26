package com.yufan.library.dialog;

import android.widget.TextView;

import com.yufan.library.R;
import com.yufan.library.base.BaseRecycleAdapter;

import java.util.List;


public class CommonDialogAdapter extends BaseRecycleAdapter {

    public CommonDialogAdapter(List datas, ItemClickListener itemClickListener) {
        super(datas, itemClickListener);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        TextView  textView = (TextView) holder.getView(R.id.btn_name);
        textView.setText((String)datas.get(position));
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_bottom_item;
    }


}
