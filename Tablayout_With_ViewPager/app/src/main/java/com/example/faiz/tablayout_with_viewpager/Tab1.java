package com.example.faiz.tablayout_with_viewpager;


import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends android.support.v4.app.Fragment {

    private List<ToDoObject> arrayList = new ArrayList<>();
    private ListView listView;
    private Button btn;
    private ListViewAdapter listViewAdapter;
    private Database db;
    Tab2 tab2;
    private Database2 db2;
    private FloatingActionButton floatingActionButton;


    @Override
    public void onResume() {
        super.onResume();
        getItem();
        Log.d("OnResume of Tab1", "is Running");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("OnCreate  of Tab1", "is Running");
    }

    public void refreshInvoked() {

                getItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("OnCreateView", "is Running");
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        listView = (ListView) view.findViewById(R.id.listt);
      //  btn = (Button) view.findViewById(R.id.addbtn);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);


        //  addItem();
        getItem();
        addItem();
        addDataToDb2();

        return view;

    }

    public void addItem() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layout = LayoutInflater.from(getActivity());
                View view = layout.inflate(R.layout.alertview, null);
                final EditText edt = (EditText) view.findViewById(R.id.editText);
                final EditText edt1 = (EditText) view.findViewById(R.id.editText2);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Add ToDo");
                builder.setMessage("Add Some todo .?");
                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToDoObject email = new ToDoObject(edt.getText().toString(), edt1.getText().toString(), 0);

                        arrayList.add(email);
                        Log.d("haha", edt + " " + edt1);
                        db.saveData(email);

                        listViewAdapter.notifyDataSetChanged();

                    }
                });
                builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view);
                builder.show();
            }


        });

    }

    public void getItem() {

        db = new Database(getActivity().getApplicationContext());
        db2 = new Database2(getActivity().getApplicationContext());
        arrayList = db.getData();
        listViewAdapter = new ListViewAdapter(arrayList, getActivity());
        listView.setAdapter(listViewAdapter);
        //  listViewAdapter.notifyDataSetChanged();


    }

    public void addDataToDb2() {


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("ToDo was Completed or Not . ?");
                builder.setTitle("ToDos");
                builder.setNegativeButton("Back", null);
                builder.setPositiveButton("Completed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ToDoObject toDoObject = arrayList.get(position);
                        db2.saveData(toDoObject);
                        refreshInvoked();
                        int idd = arrayList.get(position).getId();
                        arrayList.remove(position);
                        db.deleteItem(idd);
                        listViewAdapter.notifyDataSetChanged();




                    }
                });
                builder.show();

                return true;
            }
        });


    }


}