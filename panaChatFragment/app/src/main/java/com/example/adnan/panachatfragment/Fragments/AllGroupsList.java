package com.example.adnan.panachatfragment.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adnan.panachatfragment.Adaptors.Adaptor_Groups_List;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.groupsListSignature;
import com.example.adnan.panachatfragment.UTils.Global;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllGroupsList extends Fragment {


    public AllGroupsList() {
        // Required empty public constructor
    }


    ImageButton img;
    ListView listView;
    ArrayList<groupsListSignature> list;
    Firebase fire = new Firebase(Global.url);
    ProgressDialog pd;
    Adaptor_Groups_List adaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        list = new ArrayList<>();

        View v = inflater.inflate(R.layout.fragment_all_groups_list, container, false);
        listView = (ListView) v.findViewById(R.id.GrouplistView);

        fire.child("AppData").child("GroupInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    list.add(new groupsListSignature(dataSnapshot1.child("picUrl").getValue().toString(), dataSnapshot1.child("admin").getValue().toString(), dataSnapshot1.child("groupName").getValue().toString()));
                    adaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        adaptor = new Adaptor_Groups_List(list, getActivity());
        listView.setAdapter(adaptor);
//        adaptor.notifyDataSetChanged();
        return v;
    }

    public void visibility() {
    }

    @Override
    public void onStart() {
        super.onStart();
        Global.searchGroupFlag = true;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Global.searchGroupFlag = false;

    }
}
