package com.yufan.library.bean;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangyu on 2018/5/21.
 */

public class DataSource {

    public static final int OPTIONS_TYPE_HEIGHT = 0;
    public static final int OPTIONS_TYPE_WEIGHT = 1;
    public static final int OPTIONS_TYPE_DATE_PROGRAM = 2;
    public static final int OPTIONS_TYPE_DATE_CONDITION = 3;
    public static final int OPTIONS_TYPE_DRESS_STYLE = 4;


    public static final String[] MALE_INFO = new String[]{"身材棒", "厨艺好", "有腹肌", "浪漫主义", "土豪",
            "萌萌哒", "逗比少年", "暖男", "高智商", "高管", "大叔范", "小鲜肉", "游戏大神", "荣耀大神", "吃鸡大神", "大方", "阳光", "文艺", "纹身", "衣品好", "AJ控", "走肾不走心"};

    public static final String[] FEMALE_INFO = new String[]{"吃土少女", "女神", "厨艺好", "苗条", "学霸",
            "购物狂", "贤惠", "文艺范", "好身材", "欧美风", "纹身", "AJ控", "青春少女", "氧气少女", "coser", "二次元少女", "萝莉", "吃货", "走肾不走心", "农药少女", "吃鸡少女", "走心不走肾"};

    public static final String[] HEIGHT_OPTIONS = new String[]{"150cm","151cm","152cm","153cm","154cm","155cm","156cm","157cm","158cm","159cm","160cm","161cm","162cm"
            ,"163cm","164cm","165cm","166cm","167cm","168cm","169cm","170cm","171cm","172cm","173cm","174cm","175cm","176cm","177cm","178cm","179cm","180cm","181cm"
            ,"182cm","183cm","184cm","185cm","186cm","187cm","188cm","189cm","190cm"
    };

    public static final String[] WEIGHT_OPTIONS = new String[]{"40kg以下","40kg","41kg","42kg","43kg","44kg","45kg","46kg","47kg","48kg","49kg","50kg","51kg","52kg"
            ,"53kg","54kg","55kg","56kg","57kg","58kg","59kg","60kg","61kg","62kg","63kg","64kg","65kg","66kg","67kg","68kg","69kg","70kg","71kg","72kg","73kg"
            ,"74kg","75kg","76kg","77kg","78kg","79kg","80kg"};

    public static final String[] PROGRAM_OPTIONS = new String[]{"看电影", "吃吃喝喝", "伴游","烹饪", "运动","聚会", "唱歌","商务应酬", "内容不限"};

    public static final String[] CONDITION_OPTIONS = new String[]{"收费约会", "跟钱没关系", "我要帅哥","我要好玩", "我需要关爱","看情况"};

    public static final String[] DRESS_STYLE_OPTIONS = new String[]{"黑丝", "长筒袜", "吊带袜", "蕾丝", "超短裙", "吊带裙", "雪纺裙", "连衣裙", "牛仔裤", "萝莉", "清纯", "可爱", "御姐"
            , "甜美", "角色扮演"};

    public static List<String> getOptions(int type) {
        List<String> options = null;
        switch (type) {
            case OPTIONS_TYPE_HEIGHT:
                options = Arrays.asList(HEIGHT_OPTIONS);
                break;
            case OPTIONS_TYPE_WEIGHT:
                options = Arrays.asList(WEIGHT_OPTIONS);
                break;
            case OPTIONS_TYPE_DATE_PROGRAM:
                options = Arrays.asList(PROGRAM_OPTIONS);
                break;
            case OPTIONS_TYPE_DATE_CONDITION:
                options = Arrays.asList(CONDITION_OPTIONS);
                break;
            case OPTIONS_TYPE_DRESS_STYLE:
                options = Arrays.asList(DRESS_STYLE_OPTIONS);
                break;
        }
        return options;
    }

    public static final String CLOUD_DOMIN = "http://p744j002d.bkt.clouddn.com/";
}
