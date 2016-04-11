package com.example.faiz.sql_todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter implements ListAdapter {

    List<ToDoObjects> arrayList;
    Context context;


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }

    public MyAdapter(List<ToDoObjects> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.myview,null);

        TextView text1= (TextView) view.findViewById(R.id.textView);
        TextView text2= (TextView) view.findViewById(R.id.textView2);
        CheckBox check = (CheckBox) view.findViewById(R.id.checkBox);



        text1.setText(arrayList.get(position).getTitle());
        text2.setText(arrayList.get(position).getDiscription());
        check.setChecked(arrayList.get(position).getCheck());



        return view;
    }

}
