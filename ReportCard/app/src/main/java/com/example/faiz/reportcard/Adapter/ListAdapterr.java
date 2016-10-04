package com.example.faiz.reportcard.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.faiz.reportcard.Models.Report;
import com.example.faiz.reportcard.R;

import java.util.ArrayList;

/**
 * Created by Faiz on 8/2/2016.
 */
public class ListAdapterr extends ArrayAdapter<Report> {


    public ListAdapterr(Context context, ArrayList<Report> arrayList) {

        super(context, R.layout.dataview,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.dataview,null);

        // Populate Data
        Report card = getItem(position);

        TextView txtName = (TextView)view.findViewById(R.id.txt_name);
        TextView txtGrade = (TextView)view.findViewById(R.id.txt_grade);
        TextView txtPosition = (TextView)view.findViewById(R.id.txt_position);
        TextView txtClassAttend = (TextView)view.findViewById(R.id.txt_classAttend);



        txtName.setText(card.getName());
            txtGrade.setText(String.valueOf(card.getGrade()));
            txtPosition.setText(String.valueOf(card.getPosition()));
            txtClassAttend.setText(String.valueOf(card.getClassAttend()));



        return view;


    }
}
