package com.zhushen.sqlitedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhushen.sqlitedemo.R;
import com.zhushen.sqlitedemo.entity.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhushen on 2018/6/11.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInlfater;
    private List<Test> datas = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
        mInlfater = LayoutInflater.from(context);
    }

    public void setDatas(List<Test> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mInlfater.inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.id = (TextView)convertView.findViewById(R.id.item_id);
            viewHolder.text = (TextView)convertView.findViewById(R.id.item_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.id.setText(datas.get(position).getId() + "");
        viewHolder.text.setText(datas.get(position).getName());

        return convertView;
    }

    private class ViewHolder{
        private TextView id,text;

        public TextView getId() {
            return id;
        }

        public void setId(TextView id) {
            this.id = id;
        }

        public TextView getText() {
            return text;
        }

        public void setText(TextView text) {
            this.text = text;
        }
    }
}
