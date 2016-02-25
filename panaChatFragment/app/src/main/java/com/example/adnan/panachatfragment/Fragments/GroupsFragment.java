package com.example.adnan.panachatfragment.Fragments;


import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adnan.panachatfragment.Adaptors.Adaptor_Groups_List;
import com.example.adnan.panachatfragment.Adaptors.myGroupsAdaptor;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.groupsListSignature;
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
public class GroupsFragment extends Fragment {
    ImageButton img;
    ListView listView;
    ArrayList<groupsListSignature> list;
    Firebase fire = new Firebase(Global.url);
    ProgressDialog pd;
    myGroupsAdaptor adaptor;
    int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        list = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_groups, container, false);
        listView = (ListView) v.findViewById(R.id.mGrouplistView);
        adaptor = new myGroupsAdaptor(list, getActivity());
        listView.setAdapter(adaptor);
        registerForContextMenu(listView);
        fire.child("AppData").child("MyGroups").child(Global.uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(new groupsListSignature(dataSnapshot.child("picUrl").getValue().toString(), dataSnapshot.child("admin").getValue().toString(), dataSnapshot.child("groupName").getValue().toString()));
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Global.nameOfGroup = list.get(position).getGroupName();
                android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.RelativeLayoutHomeScreen, new Main_group_chat());
                transaction.addToBackStack("").commit();
            }
        });



        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("What Do you want");
        String [] options={"Delete","Return"};
        for(String opt:options){
            menu.add(opt);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int selectedIndex=info.position;

        if(item.getTitle()=="Delete"){

            fire.child("AppData").child("MyGroups").child(Global.uid).child(list.get(selectedIndex).getGroupName()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                        if (i == selectedIndex) {

                            dataSnapshot.getRef().removeValue();
                            list.remove(selectedIndex);
//
                        } else {
                            i++;
                        }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            adaptor.notifyDataSetChanged();
        }
        return true;
    }
}
