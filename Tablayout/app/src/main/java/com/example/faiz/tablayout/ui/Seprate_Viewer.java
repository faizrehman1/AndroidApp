package com.example.faiz.tablayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faiz.tablayout.R;

public class Seprate_Viewer extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.seprate_view,null);
        String Para = getArguments().getString("YourPara");
        String Head = getArguments().getString("YourHead");


        TextView textViewPara = (TextView)view.findViewById(R.id.textView_paraaa);
        TextView textViewHead = (TextView)view.findViewById(R.id.textView_headd);

        textViewHead.setText(Head);
        textViewPara.setText(Para);

        return view;

    }
}
