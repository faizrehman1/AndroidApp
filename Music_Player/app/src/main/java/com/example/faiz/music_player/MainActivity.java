package com.example.faiz.music_player;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private Button btnplay, btnnext, btnback, btnsongs;
    private SeekBar seekBar;
    private TextView textviewsongtime, textviewendtime, textviewsongname;
    private MediaPlayer mediaPlayer;
    private final Handler handler = new Handler();
    private ImageView imageview;
    private ArrayList<Songname> arraylist = new ArrayList();
   // private ArrayList<HashMap<String, String>> arraylist = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter(arraylist, this);
    private ListView listview_item;
    private static final String MEDIA_PATH = new String("/sdcard/download");
    private int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnplay = (Button) findViewById(R.id.buttonplay);
        seekBar = (SeekBar) findViewById(R.id.seeekbar);
        textviewsongtime = (TextView) findViewById(R.id.textView);
        textviewendtime = (TextView) findViewById(R.id.textViewendtime);
        //   imageview = (ImageView) findViewById(R.id.imageView);
        //   imageview.setImageResource(R.drawable.a);
        textviewsongname = (TextView) findViewById(R.id.songname);
        // btnsongs = (Button) findViewById(R.id.btnallsongs);
        listview_item = (ListView) findViewById(R.id.listView);
        listview_item.setAdapter(adapter);



       final SongManager songManager=new SongManager(arraylist);

        mediaPlayer = new MediaPlayer();
        songManager.updateSongList();



        listview_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mediaPlayer.reset();

                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(arraylist.get(position).getSongpath().toString());
                   mediaPlayer.prepare();
                  //  mediaPlayer.start();
                  //  btnplay.setText("Pause");
                    buttonClick();


                } catch (IOException e) {
                    e.printStackTrace();
                }


             //   String pos = String.valueOf(tracks.get(position));
            //    Toast.makeText(MainActivity.this,position+" = "+ColId[0],Toast.LENGTH_SHORT).show();


            }
        });


        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  seekBar.setProgress(12);

                buttonClick();
                int mediaPos_new = mediaPlayer.getCurrentPosition();
                int mediaMax_new = mediaPlayer.getDuration();
                seekBar.setMax(mediaMax_new);
                seekBar.setProgress(mediaPos_new);


            }
        });
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);

                return false;
            }
        });
    }


    private void buttonClick() {
        if (btnplay.getText() == getString(R.string.play_str)) {
            btnplay.setText(getString(R.string.pause_str));
            try {

                mediaPlayer.start();
                int mediaPos_new = mediaPlayer.getCurrentPosition();
                int mediaMax_new = mediaPlayer.getDuration();
                seekBar.setMax(mediaMax_new);
                seekBar.setProgress(mediaPos_new);
                startPlayProgressUpdater();
            } catch (IllegalStateException e) {
              //  mediaPlayer.pause();
            }
        } else {
            btnplay.setText(getString(R.string.play_str));
            mediaPlayer.pause();
        }
    }

    public void startPlayProgressUpdater() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    int seekValue = seekBar.getProgress();

                    String x = Integer.toString(seekValue / 1000);
                    textviewsongtime.setText(String.format("%d:%d ",
                            TimeUnit.MILLISECONDS.toMinutes(seekValue),
                            TimeUnit.MILLISECONDS.toSeconds(seekValue) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(seekValue))));
                    textviewendtime.setText(String.format("%d:%d ",
                            TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()),
                            TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration()) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mediaPlayer.getDuration()))));
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        } else {
            mediaPlayer.pause();
            btnplay.setText(getString(R.string.play_str));
            //  seekBar.setProgress(0);
        }
    }

    private void seekChange(View v) {
        if (mediaPlayer.isPlaying()) {


            SeekBar sb = (SeekBar) v;
            mediaPlayer.seekTo(sb.getProgress());
            seekBar.setProgress(mediaPlayer.getDuration());


        }
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

//class MyMP3Filter implements FilenameFilter {
//    public boolean accept(File dir, String name) {
//        return (name.endsWith(".mp3") || name.endsWith(".MP3"));
//    }
//}
