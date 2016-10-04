package com.example.faiz.broadcastrecitask;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText= (EditText)findViewById(R.id.editTextSender);
        Button btnSend = (Button)findViewById(R.id.sendingData);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences("Faiz", Context.MODE_PRIVATE);

                Intent intent=new Intent();
                intent.setAction("com.example.faiz.broadcastrecitask");
                intent.putExtra("KeyName",edtText.getText().toString());
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//                intent.setComponent(
//                        new ComponentName("com.example.faiz.broadcastrecitask2","com.example.faiz.broadcastrecitask2.MainActivity"));
                sendBroadcast(intent);
            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();

//        SharedPreferences.Editor shared = sharedpreferences.edit();
  //      shared.putString("name",edtText.getText().toString());
    //    shared.commit();

    }
}
