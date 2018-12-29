package com.zhushen.sqlitedemo.sqlite;

import android.content.Context;

import com.zhushen.sqlitedemo.entity.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/6/11.
 */
public class Controller  {
    private SqliteManager manager;
    private List<Test> list = new ArrayList<>();

    private static Controller instance;
    private static Context context;

    public static Controller getInstance(Context context) {
        Controller.context = context;
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public Controller() {
        manager = new SqliteManager(context);
    }

    public boolean add(Test test){
        return manager.insert(test);
    }

    public boolean delete(int id){
        return manager.delete(id);
    }

    public List<Test> notifyList(){
        list.clear();
        list = manager.query();
        return list;
    }

    public boolean update(Test test){
        return manager.update(test);
    }
}
