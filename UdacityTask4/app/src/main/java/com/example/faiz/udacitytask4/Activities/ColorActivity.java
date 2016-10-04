package com.example.faiz.udacitytask4.Activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.faiz.udacitytask4.Adapters.ListAdapter;
import com.example.faiz.udacitytask4.R;
import com.example.faiz.udacitytask4.models.Numbers;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ListView listView =(ListView)findViewById(R.id.listt);
        final ArrayList<Numbers> array = new ArrayList<Numbers>();

        array.add(new Numbers("Red","Faiz",R.drawable.color_red,R.raw.color_red));
        array.add(new Numbers("White","Sami",R.drawable.color_white,R.raw.color_white));
        array.add(new Numbers("Green","Kamran",R.drawable.color_green,R.raw.color_green));
        array.add(new Numbers("Mustard Yello","Moosa",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        array.add(new Numbers("Dusty Yello","Adnan",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        array.add(new Numbers("Brown","Kashan",R.drawable.color_brown,R.raw.color_brown));
        array.add(new Numbers("Dusty Yellow","Zeeshan",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        array.add(new Numbers("Red","Kamil",R.drawable.color_red,R.raw.color_red));
        array.add(new Numbers("Black","Raazi",R.drawable.color_black,R.raw.color_black));
        array.add(new Numbers("Grey","Fawad",R.drawable.color_gray,R.raw.color_gray));


        //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        ListAdapter adapter = new ListAdapter(array,this,R.color.cat_color);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ColorActivity.this,"List is clicked",Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer = MediaPlayer.create(ColorActivity.this,array.get(position).getAudioResource());
                mediaPlayer.start();
            }
        });
    }
}
