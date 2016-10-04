package com.example.faiz.udacityexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int healthLevel;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void yes(View view) {
        healthLevel = healthLevel + 1;
        message = "YOur Answered is Yes, Your Current health is: " + healthLevel;
        display(message);
    }

    public void no(View view) {
        healthLevel = healthLevel - 1;
        message = "YOur Answered is No, Your Current health is: " + healthLevel;
        display(message);
    }

    public void sometimes(View view) {
        healthLevel = healthLevel + 0;
        message = "YOur Answered is Sometimes, Your Current health is: " + healthLevel;
        display(message);
    }

    public void display(String msg) {
        // Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.neww) {
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            return true;
        }

        if (id==R.id.neww2){
            Intent intent = new Intent(MainActivity.this,Main3Activity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
