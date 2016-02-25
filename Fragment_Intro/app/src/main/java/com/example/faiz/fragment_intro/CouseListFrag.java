package com.example.faiz.fragment_intro;

import android.app.ListFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CouseListFrag extends ListFragment {

//this class is for clicklisterner
   private OnCourseItemClickListerner onCourseItemClickListerner;

    public interface OnCourseItemClickListerner {
        public void onCourseItemClickListerner(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            }


    public void setOnCourseItemClickListerner(OnCourseItemClickListerner onCourseItemClickListerner) {
        this.onCourseItemClickListerner = onCourseItemClickListerner;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if(onCourseItemClickListerner != null){
            onCourseItemClickListerner.onCourseItemClickListerner(position);
        }

    /*        Course courses = course.get(position);
        Toast.makeText(getActivity(),courses.getDiscription(),Toast.LENGTH_SHORT).show();
*/
    }

}
