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

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ListView listView =(ListView)findViewById(R.id.listt);
        final ArrayList<Numbers> array = new ArrayList<Numbers>();

        array.add(new Numbers("One","Where Are You Going?",0,R.raw.phrase_where_are_you_going));
        array.add(new Numbers("Two","My name is ...",0,R.raw.phrase_my_name_is));
        array.add(new Numbers("Three","I'm feeling good.",0,R.raw.phrase_im_feeling_good));
        array.add(new Numbers("Four","Yes!.I am Coming",0,R.raw.phrase_im_coming));
        array.add(new Numbers("Five","Come Here ..",0,R.raw.phrase_come_here));
        array.add(new Numbers("Six","Are you coming ",0,R.raw.phrase_are_you_coming));
        array.add(new Numbers("Seven","Lets Go",0,R.raw.phrase_lets_go));
        array.add(new Numbers("Eight","HOw are you feeling ?",0,R.raw.phrase_how_are_you_feeling));
        array.add(new Numbers("Nine","What is Your name?",0,R.raw.phrase_what_is_your_name));
        array.add(new Numbers("Ten","Yes im coming ?",0,R.raw.phrase_yes_im_coming));


        //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        ListAdapter adapter = new ListAdapter(array,this,R.color.cat_phrase);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PhrasesActivity.this,"List is clicked",Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer = MediaPlayer.create(PhrasesActivity.this,array.get(position).getAudioResource());
                mediaPlayer.start();
            }
        });
    }
}
