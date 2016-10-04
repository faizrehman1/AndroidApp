package com.example.faiz.tablayout.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.faiz.tablayout.Adapters.News_View_Adapter;
import com.example.faiz.tablayout.R;
import com.example.faiz.tablayout.models.News_Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Politics extends android.support.v4.app.Fragment {
    private ListView listView_items;
    private News_View_Adapter adapter;
    private ArrayList<News_Objects> list;
    private DatabaseReference firebase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dataview_political, container, false);
        firebase = FirebaseDatabase.getInstance().getReference();
        listView_items = (ListView)view.findViewById(R.id.list_items);
        list = new ArrayList<>();
        adapter = new News_View_Adapter(list,getActivity().getApplicationContext());

      //  list.add(new News_Objects("Faiz","Hahahhaha"));

        getData();
        listView_items.setAdapter(adapter);
        listView_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               News_Objects obj= list.get(position);




                Seprate_Viewer seprate_viewer = new Seprate_Viewer();
                Bundle args = new Bundle();
                args.putString("YourPara", obj.getParagraph());
                Log.d("Tagpara",obj.getParagraph());

                args.putString("YourHead", obj.getHeading());
                Log.d("TagHeading",obj.getHeading());
                seprate_viewer.setArguments(args);
//frag ko actvtity se chsnge kerwao tumhara bhand hai raat ko solve kerty hain ui per kaam kero Ok
                //Google kero solution mil jaega me jaa raha hun bhai main activity se hi to kara raha hun i mean main k fragment manager se
                ///**/bhai set kar k jao naao jaa bhai tang mat ker Google ker
                //😂😉😃yr karde bhai raat ko Filhal tum try kero
               // getActivity().getSupportFragmentManager().beginTransaction().hide(Politics.this).add(R.id.poli,seprate_viewer).addToBackStack(null).commit();
//            Bundle me data add ker dena try ker BHai Dp change karwa di ab kar k jae ga  hahaha run kero
        getFragmentManager().beginTransaction().hide(Politics.this).add(R.id.poli,seprate_viewer).addToBackStack(null).commit();
                /**
         * */

            }
        });
        return view;
    }

    public void getData(){
        firebase.child("Data").child("Politics").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    News_Objects value = data.getValue(News_Objects.class);
                    list.add(new News_Objects(value.getHeading(),value.getParagraph()));
                    adapter.notifyDataSetChanged();
                    Log.d("Value", value.getHeading() + " " + value.getParagraph());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void refreshInvoked() {

        getData();
    }

}
