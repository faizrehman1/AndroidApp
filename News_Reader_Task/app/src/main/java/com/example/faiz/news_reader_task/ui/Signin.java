package com.example.faiz.news_reader_task.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.faiz.news_reader_task.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Signin extends AppCompatActivity {

    private EditText email, pass;
    private Button btnSignin, btnSignup;
    private FirebaseAuth mAuth;
    private EditText id, password, fname, lname;
    private DatabaseReference firebase;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        email = (EditText) findViewById(R.id.editText_username);
        pass = (EditText) findViewById(R.id.editText_password);
        btnSignin = (Button) findViewById(R.id.button_login);
        btnSignup = (Button) findViewById(R.id.button_signup);






        AlertDialog.Builder builder = new AlertDialog.Builder(Signin.this);
        builder.setTitle("Add");
        builder.setMessage("Add New Email or Password");
        LayoutInflater inflater = LayoutInflater.from(Signin.this);
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
}
