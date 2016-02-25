package com.example.faiz.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class MyAdapter extends BaseAdapter {
    Context con;
    ArrayList<Message> list;
    LayoutInflater inflater;
    TextView t, tt;
    String amPm,time;


    public MyAdapter(Context con, ArrayList<Message> list) {
        this.con = con;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Message getItem(int position) {
        return list.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View v2 = inflater.inflate(R.layout.leftview, null);
        View v = inflater.inflate(R.layout.rightview, null);

        tt = (TextView) v2.findViewById(R.id.textViewLtime);
        t = (TextView) v.findViewById(R.id.textViewRtime);

        ImageView imageViewL = (ImageView)v2.findViewById(R.id.imageViewL);
        ImageView imageViewR = (ImageView)v.findViewById(R.id.imageViewR);

        imageViewL.setImageResource(R.drawable.faiz);
        imageViewR.setImageResource(R.drawable.moosa);



        Calendar cal = Calendar.getInstance();

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        //12 hour format
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        int hour = cal.get(Calendar.HOUR);
        //24 hour format

        if (hourofday > 12) {
            amPm = "Pm";
        } else {
            amPm = "Am";
        }
        time = hour + ":" + minute + " " + amPm;

        TextView textViewLeftName = (TextView) v2.findViewById(R.id.textViewL);
        TextView textViewLeftMessage = (TextView) v2.findViewById(R.id.textviewL2);

        TextView textViewRightName = (TextView) v.findViewById(R.id.textViewR);
        TextView textViewRightMessage = (TextView) v.findViewById(R.id.textViewR2);

        if (list.get(position).getName().equals("faiz")) {




            tt.setText(time);

            textViewLeftName.setText(list.get(position).getName());
            textViewLeftMessage.setText(list.get(position).getMessage());

            return v2;

        } else {



            t.setText(time);

            textViewRightName.setText(list.get(position).getName().toString());
            textViewRightMessage.setText(list.get(position).getMessage());
            return v;

        }

    }
}