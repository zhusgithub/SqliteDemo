package com.zhushen.sqlitedemo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by Zhushen on 2018/6/11.
 */
public class DBProvider {

    private Context context;
    protected SQLiteDatabase sqliteDB;
    protected DBHelper sqliteHelper;

    protected DBProvider(Context context){
        this.context = context;
    }

    /**
     * 从数据库获得可读数据
     */
    protected void getReadableDatabase(){
        sqliteHelper = new DBHelper(context);
        System.out.println("context = "+context);
        if(sqliteHelper!=null){
            try {
                sqliteDB = sqliteHelper.getWritableDatabase();
            } catch (SQLiteException e) {
                // TODO: handle exception
                sqliteDB = sqliteHelper.getReadableDatabase();
            }
        }else {
            System.out.println("sqlitehelper is null");
        }
    }

    /**
     * 从数据库获得可写数据
     */
    protected void getWritableDatabase(){
        sqliteHelper = new DBHelper(context);
        sqliteDB = sqliteHelper.getWritableDatabase();
    }

    /**
     * 关闭数据库
     */
    protected void closeDatabase(){
        sqliteDB.close();
        sqliteHelper.close();
    }
}
