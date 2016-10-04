package com.example.faiz.twitterauth;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Main2Activity extends AppCompatActivity {


    private Picasso picasso;
    private TextView name, id;
    private String namee, idd, imageUrl;
    private ImageView imgView;
    private FirebaseAuth mAuth;

    Button twitterLoginButton;
    private static final String TWITTER_KEY = "uhJ88zd7CehgjbNdSvVB2nIPo";
    private static final String TWITTER_SECRET = "72xGu52AKRYSFY9RfS85PjGcH16LewWyQ44zWxtzhhrcRK4ZDd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(Main2Activity.this, new Twitter(authConfig));
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();


        name = (TextView) findViewById(R.id.name);
        id = (TextView) findViewById(R.id.id);
        twitterLoginButton = (Button) findViewById(R.id.twitterLoginButtonnew);
        imgView = (ImageView)findViewById(R.id.imageviewww);




        namee = getIntent().getExtras().getString("name");
        idd = getIntent().getExtras().getString("locationn");
        imageUrl = getIntent().getExtras().getString("imageurl");

        name.setText(namee);
        id.setText(idd);

        picasso.with(Main2Activity.this).load(imageUrl).resize(150,150).centerCrop().into(imgView);


        twitterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mAuth.signOut();
                logoutTwitter();


            }
        });





    }

    public void logoutTwitter() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            ClearCookies(getApplicationContext());

            Twitter.getSessionManager().clearActiveSession();
            Twitter.logOut();
            finish();
        }
    }

    public static void ClearCookies(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }


}
