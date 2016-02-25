package com.example.faiz.fragment_intro;

import java.util.ArrayList;

public class CourseList extends ArrayList<Course> {
    public CourseList(){
    add(new Course("Adnan","Bara Bc Hai",R.drawable.f));
        add(new Course("Moosa","Bhai Admission Karado Bss ",android.R.drawable.alert_dark_frame));
        add(new Course("Imran","Bhai Agar Chutti Ho tO Bata Dia Kar :D",android.R.drawable.toast_frame));
    }
}
