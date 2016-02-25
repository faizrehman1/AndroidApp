package com.example.adnan.panachatfragment.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.adnan.panachatfragment.R;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    int progressStatus = 0;
    TextView logo, textView2, line;
    Handler handler = new Handler();
    TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView2 = (TextView) findViewById(R.id.load_per);
//        textView7 = (TextView) findViewById(R.id.textView7);

        logo = (TextView) findViewById(R.id.textView5);
        line = (TextView) findViewById(R.id.textView6);

        YoYo.with(Techniques.Shake)
                .duration(1000)
                .playOn(findViewById(R.id.textView5));
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .playOn(findViewById(R.id.textView6));

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView2.setText(progressStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 100) {

                    Intent ii = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(ii);
                    finish();

                }

            }
        }).start();
    }


}
