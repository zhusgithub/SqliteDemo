package com.zhushen.sqlitedemo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zhushen.sqlitedemo.entity.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/6/11.
 */
public class SqliteManager extends DBProvider {
    private Context context;

    protected SqliteManager(Context context) {
        super(context);
        this.context = context;
    }


    /**
     * 进数据库前需要变成ContentValues格式
     */
    private ContentValues setContentValues(Test test) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("testName", test.getName());

        return contentValues;
    }

    /**
     * 新增
     */
    public boolean insert(Test test) {
        getWritableDatabase();
        ContentValues value = setContentValues(test);
        sqliteDB.beginTransaction();
        boolean result = true;
        try {
            sqliteDB.insert("Test", null, value);
            sqliteDB.setTransactionSuccessful();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.e("INSERT E", "添加失败...");
            result = false;
        } finally {
            sqliteDB.endTransaction();// 成功则提交事务，失败则回滚事务
        }
        closeDatabase();
        return result;
    }

    /**
     * 查询
     */
    public List<Test> query() {
        getReadableDatabase();
        String sql = "select * from Test";

        Cursor cursor = sqliteDB.rawQuery(sql, null);
        List<Test> list = new ArrayList<Test>();
        while (cursor.moveToNext()) {
            Test test = new Test();
            test.setId(cursor.getInt(cursor.getColumnIndex("id")));
            test.setName(cursor.getString(cursor.getColumnIndex("testName")));
            list.add(test);
        }
        cursor.close();
        closeDatabase();
        return list;
    }

    /**
     * 修改
     */
    public boolean update(Test test){
        getWritableDatabase();
        sqliteDB.beginTransaction();;
        boolean result = true;

        ContentValues value = setContentValues(test);
        String[] whereArgs={test.getId() + ""};

        try {
            sqliteDB.update("Test",value,"id=?",whereArgs);
            sqliteDB.setTransactionSuccessful();
        }catch (Exception e){
            result = false;
        }finally {
            sqliteDB.endTransaction();// 成功则提交事务，失败则回滚事务
        }
        closeDatabase();
        return result;
    }


    /**
     * 删除
     */
    public boolean delete(int id) {
        getWritableDatabase();
        sqliteDB.beginTransaction();
        boolean result = true;
        try {
            sqliteDB.delete("Test", "id  = " + id, null);
            sqliteDB.setTransactionSuccessful();
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("DELETE E", "删除失败...");
            result = false;
        } finally {
            sqliteDB.endTransaction();// 成功则提交事务，失败则回滚事务
        }
        closeDatabase();
        return result;
    }

}
