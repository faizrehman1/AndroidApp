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

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ListView listView =(ListView)findViewById(R.id.listt);
        final ArrayList<Numbers> array = new ArrayList<Numbers>();

        array.add(new Numbers("Father","Aziz",R.drawable.family_father,R.raw.family_father));
        array.add(new Numbers("Mother","Housewife",R.drawable.family_mother,R.raw.family_mother));
        array.add(new Numbers("Son","Kamran",R.drawable.family_son,R.raw.family_son));
        array.add(new Numbers("Daughter","Moosa",R.drawable.family_daughter,R.raw.family_daughter));
        array.add(new Numbers("Grand Father","Adnan",R.drawable.family_grandfather,R.raw.family_grandfather));
        array.add(new Numbers("Grand Mother","Kashan",R.drawable.family_grandmother,R.raw.family_grandmother));
        array.add(new Numbers("Young Sister","Zeeshan",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        array.add(new Numbers("Older Brother","Kamil",R.drawable.family_older_brother,R.raw.family_older_brother));
        array.add(new Numbers("Young Brother","Raazi",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        array.add(new Numbers("Older Sister","Fawad",R.drawable.family_older_sister,R.raw.family_older_sister));


        //  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        ListAdapter adapter = new ListAdapter(array,this,R.color.cat_family);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FamilyActivity.this,"List is clicked",Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer = MediaPlayer.create(FamilyActivity.this,array.get(position).getAudioResource());
                mediaPlayer.start();
            }
        });
    }
}
