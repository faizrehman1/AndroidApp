package com.example.faiz.mylogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText email, pass, id, password, fname, lname;
    Firebase firebase;
    Button buttonSignup, buttonSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        email = (EditText) findViewById(R.id.edt);
        pass = (EditText) findViewById(R.id.edt1);
        buttonSignup = (Button) findViewById(R.id.but);
        buttonSignin = (Button) findViewById(R.id.but1);



        firebase = new Firebase("https://myloginf.firebaseio.com/");

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add");
                builder.setMessage("Add New Email or Password");
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View vv = inflater.inflate(R.layout.myview, null);
                id = (EditText) vv.findViewById(R.id.edtview);
                password = (EditText) vv.findViewById(R.id.edtview1);
                fname = (EditText) vv.findViewById(R.id.edtview2);
                lname = (EditText) vv.findViewById(R.id.edtview3);
                builder.setView(vv);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                      //  firebase.child(fname.getText().toString()).setValue(new EmailAndPass(id.getText().toString(), password.getText().toString()));
                        firebase.createUser(id.getText().toString(), password.getText().toString(), new Firebase.ResultHandler() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();

                              //  firebase.push().setValue(firebase.getAuth().getUid());
                                //   Intent i = new Intent(MainActivity.this, Main2Activity.class);
                                //    startActivity(i);

                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {

                                switch (firebaseError.getCode()) {
                                    case FirebaseError.EMAIL_TAKEN:
                                        Toast.makeText(MainActivity.this, "Email Alreaddy Exists", Toast.LENGTH_SHORT).show();
                                        break;
                                    case FirebaseError.NETWORK_ERROR:
                                        Toast.makeText(MainActivity.this, "Network Issue", Toast.LENGTH_SHORT).show();
                                        break;

                                }
                            }
                        });


                    }

                });
                builder.setNegativeButton("Return", null);
                builder.show();
            }
        });


        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebase.authWithPassword(email.getText().toString(), pass.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {

                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Signin.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                        switch (firebaseError.getCode()) {
                            case FirebaseError.INVALID_EMAIL:
                                Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                                break;
                            case FirebaseError.AUTHENTICATION_PROVIDER_DISABLED:
                                Toast.makeText(MainActivity.this, "Authentication Provider Disabled", Toast.LENGTH_SHORT).show();
                                break;
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                Toast.makeText(MainActivity.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }


                });
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
