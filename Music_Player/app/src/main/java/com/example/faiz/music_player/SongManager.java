package com.example.faiz.music_player;

import android.app.ListActivity;
import android.os.Environment;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongManager {

    ArrayList<Songname> arraylist = new ArrayList<>();
    File sdDir = Environment.getExternalStorageDirectory();



    public SongManager(ArrayList<Songname> arraylist) {
        this.arraylist = arraylist;
    }

    private  String MEDIA_PATH = new String("/sdcard/download");


    public void updateSongList() {
        File home = new File(MEDIA_PATH);
        if (home.listFiles(new MyMP3Filter()).length > 0) {
            for (File file : home.listFiles(new MyMP3Filter())) {
                arraylist.add(new Songname(file.getName(),file.getPath()));


            }

            }


//            ArrayAdapter<Songname>  songList = new ArrayAdapter<Songname>(this,R.layout.myview, arraylist);
  //            setListAdapter(songList);
            //play the song from playSong method here we are passing song path to play
            //
            //            playSong(MEDIA_PATH + arraylist.get(currentPosition));
            //     playSong(MEDIA_PATH + arraylist.get(currentPosition));


        }





    }
class MyMP3Filter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3") || name.endsWith(".MP3"));
    }
}

