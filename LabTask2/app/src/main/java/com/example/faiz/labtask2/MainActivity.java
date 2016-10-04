package com.example.faiz.labtask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private  EditText edtName;
   private   TextView textView;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText)findViewById(R.id.edtName);
        textView = (TextView)findViewById(R.id.getText);
        btn = (Button)findViewById(R.id.tapme);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(edtName.getText().toString());
                Toast.makeText(MainActivity.this,edtName.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
