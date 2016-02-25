package com.example.adnan.panachatfragment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Adaptors.Search_Bar_Adaptor;
import com.example.adnan.panachatfragment.Signatures.Search_Signature;
import com.example.adnan.panachatfragment.Signatures.User;
import com.example.adnan.panachatfragment.UTils.Global;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFriends extends Fragment implements SearchView.OnQueryTextListener {
    // List view

    LayoutInflater inflater;
    ArrayList<User> list;
    Firebase fire;
    ListView mListView;
    private SearchView mSearchView;
    Search_Bar_Adaptor adapter;

    public FindFriends() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fire = new Firebase(Global.url);
        View v = inflater.inflate(R.layout.fragment_friend, container, false);
        mSearchView = (SearchView) v.findViewById(R.id.searchView1);
        mListView = (ListView) v.findViewById(R.id.listView1);

        list = new ArrayList<>();

        fire.child("AppData").child("BasicInfo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
                    Global.userIds.add(dataSnapshot1.getKey());
                    list.add(new User(dataSnapshot1.child("name").getValue().toString(), dataSnapshot1.child("picUrl").getValue().toString(), dataSnapshot1.child("status").getValue().toString(), dataSnapshot1.child("email").getValue().toString(), dataSnapshot1.child("contact").getValue().toString(), dataSnapshot1.child("birthday").getValue().toString(), dataSnapshot1.getKey()));


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        adapter = new Search_Bar_Adaptor(getActivity(), list);
        mListView.setAdapter(adapter);

        mListView.setTextFilterEnabled(true);
        setupSearchView();


        return v;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Click Here");
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    @Override
    public void onStart() {
        super.onStart();
        Global.addFriendFlag = true;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Global.addFriendFlag = false;

    }


}


