package com.example.faiz.tablayout_with_viewpager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Tab2 extends android.support.v4.app.Fragment{
    private String mParam1;
    private String mParam2;
    private ListView listView;
    private List<ToDoObject> arrayList =new ArrayList<ToDoObject>();
    private ListViewAdapter2 adapter;
    private Database2 database2;
    private Database database1;


    public Tab2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Tag", "onCreate");

       }
    public void refreshInvoked() {
        getItems();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tab2, container, false);

      //  arrayList.add(new ToDoObject("LOL","Hahhahha",0));
        listView= (ListView)view.findViewById(R.id.listt2);
        getItems();
        addToDb();
        Log.v("Tab2", "onCreateView");

        return view;
    }

    @Override
    public void onPause() {
        Log.v("Tab2","onPause");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("Tab2", "onDestroy");

    }

    public void getItems(){
        database1 = new Database(getActivity());
        database2 = new Database2(getActivity());
        arrayList = database2.getData();
        adapter = new ListViewAdapter2(arrayList,getActivity());


        listView.setAdapter(adapter);

    }



    public void addToDb(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("ToDo was Completed or Not . ?");
                builder.setTitle("ToDos");
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int idd = arrayList.get(position).getId();
                        arrayList.remove(position);
                        database2.deleteItem(idd);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Undo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToDoObject toDoObject = arrayList.get(position);
                        database1.saveData(toDoObject);
                        refreshInvoked();
                        int idd = arrayList.get(position).getId();
                        arrayList.remove(position);
                        database2.deleteItem(idd);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        getItems();
        Log.v("Tab2", "onResume");

    }


}
