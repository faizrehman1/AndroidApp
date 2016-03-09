package com.example.faiz.fragment_to_fragment;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SecondFragment.OnFragmentInteractionListener,FirstFragment.OnFragmentInteractionListener {

    FirstFragment first;
    TextView textView;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first = (FirstFragment) getFragmentManager().findFragmentById(R.id.firstLayForFirstFrag);
        View v = first.getView();
        textView = (TextView) v.findViewById(R.id.textView);


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

    //first Fragment interface method
    @Override
    public void onFragmentInteraction() {
        getFragmentManager().beginTransaction().add(R.id.secondLayoutForSecondFrag,new SecondFragment()).commit();
    }

    //Second Fragment interface method
    @Override
    public void onFragmentInteraction(Uri uri) {
        if (uri.toString() == "inc") {
            i++;
            increamentValueinTextBox();
        }

    }

    private void increamentValueinTextBox() {
        textView.setText("i = " + i);
    }
}