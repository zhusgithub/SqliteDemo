package com.zhushen.sqlitedemo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zhushen on 2018/6/11.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sqlite_demo.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Test (id integer PRIMARY KEY autoincrement, testName text);");
//        db.execSQL("create table if not exists Memoran (id integer PRIMARY KEY autoincrement,memotitle text, createTime text, clockTime text, content text,isClock integer, groupId integer,repeat text);");
        System.out.println("SQL onCreate()");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table Test");

    }
}
