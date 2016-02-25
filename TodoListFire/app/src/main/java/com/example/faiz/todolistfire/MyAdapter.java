package com.example.faiz.todolistfire;


import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Message> message;
    private Context context;
    private MainActivity mainActivity;

    public MyAdapter(ArrayList<Message> message, Context context) {
        this.message = message;
        this.context = context;
        mainActivity=(MainActivity)context;

    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public Message getItem(int position) {

      return   message.get(position);


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.myview, null);

        TextView text = (TextView) v.findViewById(R.id.textViewN);
        TextView text1 = (TextView) v.findViewById(R.id.textViewD);
        TextView text2 = (TextView) v.findViewById(R.id.textViewDate);
        final CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkBoxM);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Check check = mainActivity;
                check.checked(position,checkBox.isChecked());

            }
        });


        text.setText(message.get(position).getName());
        text1.setText(message.get(position).getDiscription());
        text2.setText(message.get(position).getDate());
        checkBox.setChecked(message.get(position).isCheck());


        return v;
    }
    interface Check {
        void checked(int position,boolean checked);
    }
}
