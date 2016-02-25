package com.example.faiz.fragment_intro;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Course> adapter;
    private CourseList courselist = new CourseList();
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final CouseListFrag couseListFrag = (CouseListFrag) getFragmentManager().findFragmentById(R.id.frag1);



        adapter = new ArrayAdapter<Course>(this, R.layout.myview, courselist);
        couseListFrag.setListAdapter(adapter);

        //       Fragment_Class fragment_class = new  Fragment_Class();
        //     fragment_class.setCourse(courselist.get(0));

        //interface method which we created in courseflag class
        couseListFrag.setOnCourseItemClickListerner(new CouseListFrag.OnCourseItemClickListerner() {
            @Override
            public void onCourseItemClickListerner(int position) {
                //assign courselist position from course and set into fragment_class
                Course courses = courselist.get(position);
                //fragment_class.setCourse(coursess);
                Fragment_Class fragment_class = new Fragment_Class(courses);
                //  fragment_class.setCourse(courselist.get(0));

                getFragmentManager().beginTransaction().hide(couseListFrag).replace(R.id.main,fragment_class).addToBackStack(null).commit();





            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
