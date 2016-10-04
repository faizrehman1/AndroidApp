package com.example.faiz.tablayout.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faiz.tablayout.R;
import com.example.faiz.tablayout.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button btnSignin, btnSignup;
    private FirebaseAuth mAuth;

    private DatabaseReference firebase;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  FirebaseDatabase database = FirebaseDatabase.getInstance();
        firebase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        email = (EditText) findViewById(R.id.editText_username);
        pass = (EditText) findViewById(R.id.editText_password);
        btnSignin = (Button) findViewById(R.id.button_login);
        btnSignup = (Button) findViewById(R.id.button_signup);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String passo = pass.getText().toString();
                String em = email.getText().toString();

                if(passo.length()==0){
                    pass.setError("Required Field");
                }
                if(em.length()==0){
                    email.setError("Required Field");
                }


                try {
                    mAuth.signInWithEmailAndPassword(em,passo).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             Log.d("Tag", String.valueOf(task.isSuccessful()));

                            if(task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, Create_PostActivity.class);
                                startActivity(intent);
                                finish();
                                //  openNavigationActivity();
                            }
                           else if (!task.isSuccessful()) {
                                //         AppLogs.logw("signInWithEmail" + task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch(Exception ex){
                    ex.printStackTrace();
                    Toast.makeText(LoginActivity.this,"SOmeTing Went Wrong in Email Or Pass",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Create Account ");
                builder.setTitle("YOu Must Have a Accout to create post !!");
                LayoutInflater layoutInflater = LayoutInflater.from(LoginActivity.this);
                View view = layoutInflater.inflate(R.layout.alert_view, null);

                final EditText edt_fname = (EditText) view.findViewById(R.id.editText_fname);
                final EditText edt_lname = (EditText) view.findViewById(R.id.editText_lname);
                final EditText edt_email = (EditText) view.findViewById(R.id.editText_email);
                final EditText edt_pass = (EditText) view.findViewById(R.id.editText_password);
                TextView textView = (TextView) view.findViewById(R.id.textViewa);


                builder.setView(view);
                builder.setPositiveButton("Sign-up",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                final String pass = edt_pass.getText().toString();
                                String email = edt_email.getText().toString();
                                String fname =edt_fname.getText().toString();
                                String lname = edt_lname.getText().toString();
                                final User user = new User(fname,lname,email,pass);


//                                if (!isValidPassword(pass)) {
//                    edt_pass.setError("Invalid Password");
//                }





                                try {

                                    mAuth.createUserWithEmailAndPassword((edt_email.getText().toString()), (edt_pass.getText().toString()))
                                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    // Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                                                    firebase.child("Users").child(mAuth.getCurrentUser().getUid()).push().setValue(user);

                                                    // If sign in fails, display a message to the user. If sign in succeeds
                                                    // the auth state listener will be notified and logic to handle the
                                                    // signed in user can be handled in the listener.
                                                    if (!task.isSuccessful()) {
                                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                                Toast.LENGTH_SHORT).show();
                                                    }

                                                    // ...
                                                }
                                            });
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }
                        });
                builder.setNegativeButton("Back",null);


                builder.create().show();
            }
        });

    }
    private boolean isValidPassword(String pass) {
        if (pass != null ) {
            return true;
        }
        return false;
    }
}



