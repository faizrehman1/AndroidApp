package com.example.adnan.panachatfragment.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.adnan.panachatfragment.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.Firebase;

//import java.security.Signature;


public class Main2Activity extends AppCompatActivity {

    public static Context con;
    public static Activity fa;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker tracker;
    android.support.v4.app.FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fa = Main2Activity.this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);
        con = Main2Activity.this;
        loginButton = (LoginButton) findViewById(R.id.login_button);

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };


        ft = this.getSupportFragmentManager().beginTransaction();
        checkAuth();
    }

    public void checkAuth() {
        accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            Intent i = new Intent(Main2Activity.this, AfterLoginScreen.class);
            startActivity(i);

        } else {
            getMeLogIn();
        }
    }


    public void getMeLogIn() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager check = (ConnectivityManager) Main2Activity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo[] info = check.getAllNetworkInfo();

                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {

                                accessToken = loginResult.getAccessToken();
                                if (Profile.getCurrentProfile() == null) {
                                    tracker = new ProfileTracker() {
                                        @Override
                                        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                            tracker.stopTracking();


                                            Intent i = new Intent(Main2Activity.this, AfterLoginScreen.class);
                                            startActivity(i);
                                        }
                                    };
                                    tracker.startTracking();
                                } else {
                                    Profile profile = Profile.getCurrentProfile();
                                    Intent i = new Intent(Main2Activity.this, AfterLoginScreen.class);
                                    startActivity(i);

                                }
                            }

                            @Override
                            public void onCancel() {
                            }

                            @Override
                            public void onError(FacebookException e) {

                                Snackbar.make(loginButton, "Connection Problem", Snackbar.LENGTH_SHORT).show();
                            }
                        });

                    } else {

                        YoYo.with(Techniques.Shake)
                                .duration(1000)
                                .playOn(findViewById(R.id.login_button));
                        YoYo.with(Techniques.Shake)
                                .duration(1000)
                                .playOn(findViewById(R.id.imageView4));
                    }
                }
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        accessTokenTracker.startTracking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
