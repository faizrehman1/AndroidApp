package com.example.faiz.twitterauth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.StatusesService;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.concurrency.AsyncTask;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "9H98H6CzTnWDsOZY0GaKdvZz1";
    private static final String TWITTER_SECRET = "NjVu9N7qGkKpCYPlNu58TOiWqxiOHUpFUzCG7NAksHxuam1FJf";
    private TwitterLoginButton loginButton;
    private String twitter_name, twitter_id;

    private SharedPreferences preferences;
    private SharedPreferences.Editor setPreference;
    TextView mainTextView;
    TwitterSession session;
    Picasso picasso;
    Button btn;
    String name,imageUrl,location;


    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();



        preferences = getSharedPreferences("twiiterData",MODE_APPEND);
        setPreference = preferences.edit();

      //  Log.d("RequestCode", String.valueOf(authConfig.getRequestCode()));

      //  btn = (Button)findViewById(R.id.firebaselogin);
        mainTextView = (TextView) findViewById(R.id.mainTextView);
        loginButton = (TwitterLoginButton) findViewById(R.id.twitterLoginButton);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                 //   Log.d("imageUrl",result.data.profileImageUrl);
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("name", preferences.getString("name",""));
                    intent.putExtra("locationn",preferences.getString("location","") );
                    intent.putExtra("imageurl",preferences.getString("imageurl",""));
                    startActivity(intent);

                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        if(session!=null){

        }else{

            loginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {


                    session = result.data;

                    Log.d("TAG", result.toString());
                    Log.d("Session", session.toString());
                    Log.d("Testing", "Han Bhaii");


                    twitter_name = session.getUserName();
                    twitter_id = session.getUserId() + "";
                    String info = "Nombre de usuario: " + twitter_name + "\nUsuario ID: " + twitter_id + "\n";

                    Log.d("LOLaa", "Hahhahhahha");


                    String output = "Status: " +
                            "Your login was successful " +
                            result.data.getUserName() +
                            "\nAuth Token Received: " +
                            result.data.getAuthToken().token;

                    mainTextView.setText(output);
                    TwitterAuthToken accessToken = result.data.getAuthToken();
                    Log.d("TokenNO", accessToken.toString());
                    getUserInfo(session);

                }

                @Override
                public void failure(TwitterException exception) {
                    Log.d("TwitterKit", "Login with Twitter failure", exception);
                }
            });


        }



    }





    public void getUserInfo(final TwitterSession session){

        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
        twitterApiClient.getAccountService().verifyCredentials(true,true).enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> result) {

                handleTwitterSession(session);


                location = result.data.location;
                name = result.data.name;
                imageUrl = result.data.profileImageUrl;


                setPreference.putString("imageurl",imageUrl);
                setPreference.putString("location",location);
                setPreference.putString("name",name);
                setPreference.commit();


                Log.d("imageUrl",result.data.profileImageUrl);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name", preferences.getString("name",""));
                intent.putExtra("locationn",preferences.getString("location","") );
                intent.putExtra("imageurl",preferences.getString("imageurl",""));
                startActivity(intent);


            }
            @Override
            public void failure(TwitterException exception) {

            }
        });
    }




    private void handleTwitterSession(TwitterSession session) {
        Log.d("TAG", "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);


        Log.d("Logs",String.valueOf(session.getAuthToken()));

        Log.d("credential",credential.toString());


        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("TAG", "signInWithCredential:onComplete:" + task.isSuccessful());





                        }
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        else {
                            Log.w("TAG", "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        loginButton.onActivityResult(requestCode, resultCode, data);

    }




    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



}