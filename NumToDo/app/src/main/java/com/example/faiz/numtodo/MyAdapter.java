package com.example.faiz.numtodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    private ArrayList<TodoItems> item;
    Context context;
    private MainActivity mainActivity;

    public MyAdapter(ArrayList<TodoItems> item, Context context) {
        this.item = item;
        this.context = context;
        mainActivity = (MainActivity) context;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public TodoItems getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vieww = inflater.inflate(R.layout.myview, null);

        TextView title = (TextView) vieww.findViewById(R.id.Title);
        TextView discrip = (TextView) vieww.findViewById(R.id.discription);
        final CheckBox checkBox = (CheckBox) vieww.findViewById(R.id.checkBox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check check = mainActivity;
                check.checked(position, checkBox.isChecked());

            }
        });


        title.setText(item.get(position).getTitle());
        discrip.setText(item.get(position).getDiscription());
        checkBox.setChecked(item.get(position).isCheck());

        return vieww;

    }

    interface Check {
        void checked(int position, boolean check);
    }
}

