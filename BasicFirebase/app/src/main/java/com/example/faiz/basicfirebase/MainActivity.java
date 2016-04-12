package com.example.faiz.basicfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Firebase firebase;
    TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        firebase = new Firebase("https://adnang.firebaseio.com/");

        //set value in firebase
        firebase.child("fawad").setValue(new Message("Fawad", "Kahan ho?"));
        textView = (TextView) findViewById(R.id.aloo);
        textView1 = (TextView) findViewById(R.id.textView1);

        //get value on firebase from child node whose name is =  fawad
        firebase.child("fawad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("",dataSnapshot.getValue().toString());

                    Message newPost = dataSnapshot.getValue(Message.class);
                    textView.setText(newPost.getName1().toString());
                    textView1.setText(newPost.getMessage1().toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//        hogaya masla hal ? konsa masla?
    }

    //ek min ruk
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it present.
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
