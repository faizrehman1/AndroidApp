package com.example.faiz.todolist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class Myadapter extends BaseAdapter {
    private ArrayList<Message> msg;
    private Context context;

    public Myadapter(ArrayList<Message> msg, Context context) {
        this.msg = msg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return msg.size();
    }

    @Override
    public Message getItem(int position) {
        return msg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vieww = layoutInflater.inflate(R.layout.myview, null);
       EditText text = (EditText) vieww.findViewById(R.id.textViewMessagesName);
       EditText text1 = (EditText) vieww.findViewById(R.id.textView2Messagesmsg);
        text.setText(msg.get(position).getName());
        text1.setText(msg.get(position).getMessage());


        return vieww;
    }
}
