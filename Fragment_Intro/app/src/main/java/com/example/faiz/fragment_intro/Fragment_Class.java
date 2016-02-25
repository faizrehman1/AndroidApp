package com.example.faiz.fragment_intro;


import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_Class extends Fragment {


    private Course course;

    public Fragment_Class() {

    }


    public Fragment_Class(Course course) {
        this.course = course;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        if (course != null) {
            setCourse(course, view);
        }

        Button btnback = (Button) view.findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main, new CouseListFrag()).addToBackStack(null).commit();

            }
        });


        return view;
    }

    public void setCourse(Course course) {
        View view = getView();

        setCourse(course, view);
    }

    private void setCourse(Course course, View view) {


        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView1 = (TextView) view.findViewById(R.id.textView1);


        Drawable icon = getResources().getDrawable(course.getImage());

        imageView.setImageDrawable(icon);

        textView.setText(course.getTitle());
        textView1.setText(course.getDiscription());


    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
