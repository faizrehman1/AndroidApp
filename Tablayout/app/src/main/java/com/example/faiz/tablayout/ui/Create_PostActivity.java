package com.example.faiz.tablayout.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.faiz.tablayout.models.News_Objects;
import com.example.faiz.tablayout.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Create_PostActivity extends AppCompatActivity {

   private  Spinner spinner;
    private EditText title,news;
    

    private FirebaseAuth mAuth;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> list;
    private Button post_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post);


        final DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
//        mAuth = FirebaseAuth.getInstance();
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference firebase = database.getReference();





        spinner = (Spinner)findViewById(R.id.spinner_items);
        title = (EditText)findViewById(R.id.editText_title);
        news = (EditText)findViewById(R.id.editText_news);
        post_news = (Button)findViewById(R.id.button_create_post);


        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
        list.add("Politics");
        list.add("Business");
        list.add("Sports");
        list.add("Entertainment");



       spinner.setAdapter(adapter);



        post_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = spinner.getSelectedItem().toString();
                Log.d("Tag",text);




                firebase.child("Data").child(text).push().setValue(new News_Objects(title.getText().toString(),news.getText().toString()));

                Intent intent  = new Intent(Create_PostActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        });




    }
}
