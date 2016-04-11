package com.example.faiz.tablayout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends android.support.v4.app.Fragment {
    ImageView imageView;

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        imageView = (ImageView) view.findViewById(R.id.image1);
        imageView.setImageResource(R.drawable.faiz);


        return view;
    }


}
