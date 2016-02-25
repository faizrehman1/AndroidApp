package com.example.adnan.panachatfragment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.adnan.panachatfragment.Adaptors.Friend_List_Adaptor;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.Signature_Friend_List;
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
public class FriendsListFragment extends Fragment {
    ListView listView;
    ArrayList<Signature_Friend_List> list;
    Firebase fire;
    int i = 0;
    ArrayList<String> picUrls;
    Friend_List_Adaptor adaptor;


    public FriendsListFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        fire = new Firebase(Global.url);
        View v = inflater.inflate(R.layout.friends_list_fragment, null);
        listView = (ListView) v.findViewById(R.id.ListViewfriendList);
        registerForContextMenu(listView);

        list = new ArrayList<>();
        picUrls = new ArrayList<>();
        adaptor = new Friend_List_Adaptor(getActivity(), list, picUrls);
        fire.child("AppData").child("Friends").child(Global.uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Dogs", dataSnapshot.child("name").getValue().toString());
                list.add(new Signature_Friend_List(dataSnapshot.child("name").getValue().toString(), dataSnapshot.child("senderId").getValue().toString(), dataSnapshot.child("picUrl").getValue().toString()));
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
                Global.PartnaerId = list.get(position).getSenderId();
                Global.partnerName = list.get(position).getName();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.RelativeLayoutHomeScreen, new friend_profile());
                transaction.addToBackStack("friendProfile");
                try{
                    if (Global.statusFlag == true) {
                        manager.popBackStack("stackStatus", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    if (Global.myProfileFlag == true) {
                        manager.popBackStack("myProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }
                    if (Global.addNewGroupFlag == true) {
                        manager.popBackStack("addGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    if (Global.infoFlag == true) {
                        manager.popBackStack("stackInfo", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    if (Global.addFriendFlag == true) {
                        manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }
                    if (Global.searchGroupFlag == true) {
                        manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }
                }catch(Exception e){

                }
                transaction.commit();
            }
        });
        listView.setAdapter(adaptor);

        return v;
    }


    @Override
    public void onResume() {
        Log.d("kk", "resume");

        adaptor.notifyDataSetChanged();
        super.onResume();

    }

    @Override
    public void onStart() {
        Log.d("kk", "start");

        adaptor.notifyDataSetChanged();

        super.onStart();
    }



}
