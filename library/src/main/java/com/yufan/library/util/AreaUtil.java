package com.yufan.library.util;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yufan.library.bean.LocationBean;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mengfantao on 18/4/12.
 */

public class AreaUtil {

   private ArrayList<LocationBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<LocationBean>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<LocationBean>>> options3Items = new ArrayList<>();
    private static AreaUtil areaUtil=new AreaUtil();
    public ArrayList<LocationBean> getOptions1Items() {
        return options1Items;
    }

    public ArrayList<ArrayList<LocationBean>> getOptions2Items() {
        return options2Items;
    }

    public ArrayList<ArrayList<ArrayList<LocationBean>>> getOptions3Items() {
        return options3Items;
    }

    public static AreaUtil getInstance(){
        return areaUtil;
    }

    private AreaUtil() {


    }

    public void init(Context context){
        options1Items.clear();
        options2Items.clear();
        options3Items.clear();
        initJsonData(context);
    }

    private ArrayList<LocationBean> getSuperList(String jsonData){
        ArrayList<LocationBean> items = new ArrayList<>();
        HashMap<String ,JSONObject> jsonmap=   JSON.parseObject(jsonData, HashMap.class);
        for (String key : jsonmap.keySet()){
            JSONObject provin=  jsonmap.get(key);
            LocationBean provinceBean=new LocationBean();
            provinceBean.setId(key);
            provinceBean.setName( provin.getString("name"));
            provinceBean.setCity( provin.getString("child"));
            items.add(provinceBean);
        }
    return items;
    }

    private ArrayList<LocationBean> getAreaList(String jsonData){
        ArrayList<LocationBean> items = new ArrayList<>();
        HashMap<String ,String> jsonmap=   JSON.parseObject(jsonData, HashMap.class);
        for (String key : jsonmap.keySet()){
            String str=  jsonmap.get(key);
            LocationBean provinceBean=new LocationBean();
            provinceBean.setId(key);
            provinceBean.setName(str);
            items.add(provinceBean);
        }
        return items;
    }
    private void initJsonData(Context context) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String jsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        options1Items=  getSuperList(jsonData);
        for (int i = 0; i < options1Items.size(); i++) {
          String city=  options1Items.get(i).getCity();
            /**
             * 添加城市数据
             */
            options2Items.add(getSuperList(city));

            for (int j=0; i < options2Items.size(); j++){
                /**
                 * 添加地区数据
                 */
                options3Items.add(options2Items);
            }

        }

    }



}
