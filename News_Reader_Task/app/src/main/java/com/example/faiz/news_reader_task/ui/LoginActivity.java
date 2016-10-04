package com.example.faiz.news_reader_task.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faiz.news_reader_task.R;
import com.example.faiz.news_reader_task.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button btnSignin, btnSignup;
    private FirebaseAuth mAuth;
    private EditText id, password, fname, lname;
    private DatabaseReference firebase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user ;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editText_username);
        pass = (EditText) findViewById(R.id.editText_password);
        btnSignin = (Button) findViewById(R.id.button_login);
        btnSignup = (Button) findViewById(R.id.button_signup);

        firebase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");

                }
                // ...
            }
        };


        //  login_firebase();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_firebase();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup_firebaes();
            }
        });

    }

    private void login_firebase() {



    }
    public void signup_firebaes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Add");
        builder.setMessage("Add New Email or Password");
        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        View vv = inflater.inflate(R.layout.signup_view, null);
        id = (EditText) vv.findViewById(R.id.edtviewEmail);
        password = (EditText) vv.findViewById(R.id.edtviewPassword);
        fname = (EditText) vv.findViewById(R.id.edtviewFirstName);
        lname = (EditText) vv.findViewById(R.id.edtviewLastName);


        builder.setView(vv);
        builder.setPositiveButton("SIGN-UP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {






        }
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();

    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
}


//
//firebase.child("User").push().setValue(new
//        User(fname.getText().toString(),
//        lname.getText().toString(),
//        id.getText().toString(),
//        password.getText().toString()));
