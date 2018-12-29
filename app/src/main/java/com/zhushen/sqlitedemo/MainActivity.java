package com.zhushen.sqlitedemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.zhushen.sqlitedemo.adapter.MyAdapter;
import com.zhushen.sqlitedemo.entity.Test;
import com.zhushen.sqlitedemo.sqlite.Controller;
import com.zhushen.sqlitedemo.sqlite.SqliteManager;
import com.zhushen.sqlitedemo.swipemenulistview.SwipeMenu;
import com.zhushen.sqlitedemo.swipemenulistview.SwipeMenuCreator;
import com.zhushen.sqlitedemo.swipemenulistview.SwipeMenuItem;
import com.zhushen.sqlitedemo.swipemenulistview.SwipeMenuListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,  AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener, SwipeMenuListView.OnMenuItemClickListener {
    private Button add;
    private ListView listView;
    private Controller controller;

    private SwipeMenuListView swipeMenuListView;

    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = Controller.getInstance(this);
        init();

    }

    private void init() {
        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(this);

        listView = (ListView)findViewById(R.id.listview);
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);

        adapter.setDatas(controller.notifyList());

        listView.setOnItemLongClickListener(this);
        listView.setOnItemClickListener(this);

        swipeMenuListView = (SwipeMenuListView)findViewById(R.id.swipelist);
        initCreator();
        swipeMenuListView.setAdapter(adapter);
        swipeMenuListView.setOnMenuItemClickListener(this);
    }

    private void initCreator() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                openItem.setWidth(200);
                openItem.setTitle("change");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(200);
                deleteItem.setTitle("delete");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
                //https://github.com/baoyongzhang/SwipeMenuListView
            }
        };
        swipeMenuListView.setMenuCreator(creator);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                Random random = new Random();
                int i = random.nextInt(10);

                Test test = new Test();
                test.setId(i);
                test.setName("name " + i);

                controller.add(test);

                adapter.setDatas(controller.notifyList());

                break;
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Test test = controller.notifyList().get(position);


        controller.delete(test.getId());
        adapter.setDatas(controller.notifyList());

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Test test = controller.notifyList().get(position);

        test.setName(test.getName() + "changed");
        controller.update(test);

        adapter.setDatas(controller.notifyList());
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index){
            case 1:
                // TODO: 2018/6/21 delete
                controller.delete(controller.notifyList().get(position).getId());
                adapter.setDatas(controller.notifyList());
                break;
        }
        return false;
    }
}
