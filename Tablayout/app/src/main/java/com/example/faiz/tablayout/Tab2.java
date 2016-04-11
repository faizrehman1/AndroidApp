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
public class Tab2 extends android.support.v4.app.Fragment {
    private ImageView imageView;

    public Tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab2, container, false);
        imageView = (ImageView) view.findViewById(R.id.image2);
        imageView.setImageResource(R.drawable.faizz);
        return view;
    }


}
