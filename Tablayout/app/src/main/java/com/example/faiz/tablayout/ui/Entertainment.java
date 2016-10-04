package com.example.faiz.tablayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class Entertainment extends android.support.v4.app.Fragment {
    private ListView listView_items;
    private News_View_Adapter adapter;
   private ArrayList<News_Objects> list;
    private DatabaseReference firebase ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dataview_entertainment,null);
        firebase = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();
        adapter = new News_View_Adapter(list,getActivity());

        listView_items = (ListView)view.findViewById(R.id.list_items_entertainment);

        listView_items.setAdapter(adapter);


        listView_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News_Objects obj= list.get(position);




                Seprate_Viewer seprate_viewer = new Seprate_Viewer();
                Bundle args = new Bundle();
                args.putString("YourPara", obj.getParagraph());
                args.putString("YourHead", obj.getHeading());
                seprate_viewer.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction().hide(new Entertainment()).add(R.id.enter,seprate_viewer).addToBackStack(null).commit();
//            Bundle me data add ker dena
                /**
                 * then open fragment
                 * */

            }
        });


        getData();

        return view;
    }


    public void getData(){
        firebase.child("Data").child("Entertainment").addListenerForSingleValueEvent(new ValueEventListener() {
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
