package com.youximao.anew.myapplication;

import android.content.Context;

import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;

import java.io.File;

/**
 * 简单的 SQLite Helper
 */
public class PlainDBHelper extends SQLiteOpenHelper {
    // 数据库 db 文件名称
    private static final String DEFAULT_NAME = "plain.db";

    // 默认版本号
    private static final int DEFAULT_VERSION = 1;

    private Context mContext;

    /**
     * 通过父类构造方法创建 plain 数据库
     */
    public PlainDBHelper(Context context) {
        super(context, DEFAULT_NAME, null, DEFAULT_VERSION, null);
        this.mContext = context;
    }

    /**
     * 表创建
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS person (_id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(20) , age INTEGER)";
        db.execSQL(SQL_CREATE);
    }

    /**
     * 版本升级
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
    }

    /**
     * 删除数据库 db 文件
     */
    public boolean onDelete() {
        File file = mContext.getDatabasePath(DEFAULT_NAME);
        return SQLiteDatabase.deleteDatabase(file);
    }
}
