package com.youximao.anew.myapplication;

import android.content.ContentValues;
import android.content.Context;

import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.youximao.anew.myapplication.bean.Person;


import java.util.ArrayList;
import java.util.List;

public class PlainDBManager {
    private PlainDBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public PlainDBManager(Context context) {
     // mDBHelper = new PlainDBHelper(context);
       mDBHelper = new PlainDBHelper(context,"youximao".getBytes());
         mDB = mDBHelper.getWritableDatabase();
    }

    public void addPersonData(Person person) {
        try {
            // 开启事务
            mDB.beginTransaction();

            // 执行插入语句
            final String sql = "INSERT INTO person VALUES(NULL,?,?)";
            Object[] objects = new Object[]{person.getName(), person.getAge()};
            mDB.execSQL(sql, objects);

            // 设置事务完成成功
            mDB.setTransactionSuccessful();
        } finally {
            // 关闭事务
            mDB.endTransaction();
        }
    }

    public boolean addPersons(int count,String name,int age) {
        try {
            // 开启事务
            mDB.beginTransaction();
            // 执行插入语句
            for (int i=0;i<count;i++) {
                Object[] objects = new Object[]{name+i, age};
                final String sql = "INSERT INTO person VALUES(NULL,?,?)";
                mDB.execSQL(sql, objects);
            }

            // 设置事务完成成功
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            return false;
        } finally {
            // 关闭事务
            mDB.endTransaction();
        }
        return true;
    }

    /**
     * 拿到数据库中所有的Person并放入集合中
     */
    public List<Person> getPersonListData() {
        List<Person> listData = new ArrayList<>();
        Cursor c = getAllPersonInfo();
        while (c.moveToNext()) {
            Person person = new Person();
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setAge(c.getInt(c.getColumnIndex("age")));
            listData.add(person);
        }
        c.close();
        return listData;
    }
    public boolean updatePersonByName(String name,int age) {
        ContentValues values = new ContentValues();
        values.put("age", age);
        int zhuiTao = mDB.update("person", values, "name=?", new String[]{name});
        if (zhuiTao > 0) {
            return true;
        } else {
            return false;
        }

    }
    public boolean delPersonByAge(String age) {
        try {
            // 开启事务
            mDB.beginTransaction();
            mDB.delete("person","age = ?",new String[]{age});
            // 设置事务完成成功
            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            return false;
        } finally {
            // 关闭事务
            mDB.endTransaction();
        }
        return true;
    }

    private Cursor getAllPersonInfo() {
        return mDB.rawQuery("SELECT * FROM person", null);
    }

    /**
     * 关闭  database；
     */
    public void closeDB() {
        mDB.close();
    }

    /**
     * 删除数据库
     */
    public Boolean deleteDatabase() {
        return mDBHelper.onDelete();
    }
}
