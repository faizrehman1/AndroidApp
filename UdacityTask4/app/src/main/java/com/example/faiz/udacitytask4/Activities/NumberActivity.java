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

public class NumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ListView listView =(ListView)findViewById(R.id.listt);
        final ArrayList<Numbers> array = new ArrayList<Numbers>();

        array.add(new Numbers("One","Faiz",R.drawable.number_one,R.raw.number_one));
        array.add(new Numbers("Two","Sami",R.drawable.number_two,R.raw.number_two));
        array.add(new Numbers("Three","Kamran",R.drawable.number_three,R.raw.number_three));
        array.add(new Numbers("Four","Moosa",R.drawable.number_four,R.raw.number_four));
        array.add(new Numbers("Five","Adnan",R.drawable.number_five,R.raw.number_five));
        array.add(new Numbers("Six","Kashan",R.drawable.number_six,R.raw.number_six));
        array.add(new Numbers("Seven","Zeeshan",R.drawable.number_seven,R.raw.number_seven));
        array.add(new Numbers("Eight","Kamil",R.drawable.number_eight,R.raw.number_eight));
        array.add(new Numbers("Nine","Raazi",R.drawable.number_nine,R.raw.number_nine));
        array.add(new Numbers("Ten","Fawad",R.drawable.number_ten,R.raw.number_ten));


      //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        ListAdapter adapter = new ListAdapter(array,this,R.color.cat_numbers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NumberActivity.this,"List is clicked",Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer = MediaPlayer.create(NumberActivity.this,array.get(position).getAudioResource());
                mediaPlayer.start();
            }
        });

    }
}
