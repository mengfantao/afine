package com.yufan.library.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mengfantao on 17/4/19.
 * 列表adapter基础类
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter {


    public List<T> datas;
    protected ItemClickListener itemClickListener;

    public BaseRecycleAdapter(List<T> datas, ItemClickListener itemClickListener) {
        this.datas = datas;
        this.itemClickListener=itemClickListener;
    }
    public BaseRecycleAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false);
        return new BaseViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if( holder instanceof BaseRecycleAdapter.BaseViewHolder){
            ((BaseViewHolder) holder).position=position;
            bindData((BaseRecycleAdapter.BaseViewHolder)holder,position);
        }
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 刷新数据
     * @param datas
     */
    public void refresh(List<T> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     * @param datas
     */
    public void addData(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     *  绑定数据
     * @param holder  具体的viewHolder
     * @param position  对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position);



    @Override
    public int getItemCount() {

        return datas==null?0:datas.size();
    }




    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public static class BaseViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private int position;
        private Map<Integer, View> mViewMap;
        private  ItemClickListener itemClickListener;
        private Context context;
        public BaseViewHolder(View itemView,ItemClickListener itemClickListener) {
            super(itemView);
            this.context=itemView.getContext();
            mViewMap = new HashMap<>();
            if(itemClickListener!=null) {
                this.itemClickListener=itemClickListener;
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }
        }
        public Context getContext(){
            return context;
        }

        /**
         * 获取设置的view
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(v,position);
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if(itemClickListener!=null) {
                return itemClickListener.onItemLongClick(v, position);
            }else{
                return false;
            }
        }
    }

    /**
     * 获取子item
     * @return
     */
    public abstract int getLayoutId();



    /**
     * 设置文本属性
     * @param view
     * @param text
     */
    public void setItemText(View view,String text){
        if(view instanceof TextView){
            ((TextView) view).setText(text);
        }
    }

    public interface ItemClickListener {
         void onItemClick(View view, int postion);
        boolean onItemLongClick(View view, int postion);
    }
}