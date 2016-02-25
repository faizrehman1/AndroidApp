package com.example.adnan.panachatfragment.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
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

import com.example.adnan.panachatfragment.Activities.ChatActivity;
import com.example.adnan.panachatfragment.Activities.MainActivity;
import com.example.adnan.panachatfragment.Adaptors.ConversationAdaptor;
import com.example.adnan.panachatfragment.Adaptors.Friend_List_Adaptor;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.Signature_Friend_List;
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
public class ConversationFragment extends Fragment {

    ListView listView;
    ConversationAdaptor adaptor;
    ArrayList<Signature_Friend_List> list;
    ArrayList<String> picUrlss;
    Firebase fire = new Firebase(Global.url);

    public ConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conversation, container, false);
        list = new ArrayList<>();
        picUrlss = new ArrayList<>();
        visibility();


        listView = (ListView) v.findViewById(R.id.converList);
        adaptor = new ConversationAdaptor(getActivity(), list);
        listView.setAdapter(adaptor);
        registerForContextMenu(listView);


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
                Intent i = new Intent(getActivity(), ChatActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });


        return v;
    }

    public void visibility() {
        ImageButton img = (ImageButton) getActivity().findViewById(R.id.addFriendButton);
        img.setVisibility(View.VISIBLE);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("What Do you want");
        String[] options = {"Unfriend", "Delete Msgs", "Return"};
        for (String opt : options) {
            menu.add(opt);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int selectedIndex = info.position;

        if (item.getTitle() == "Unfriend") {
            fire.child("AppData").child("Friends").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    dataSnapshot.child(Global.uid).child(list.get(selectedIndex).getSenderId()).getRef().removeValue();
                    dataSnapshot.child(list.get(selectedIndex).getSenderId()).child(Global.uid).getRef().removeValue();

                    list.remove(selectedIndex);
                    adaptor.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
//            adaptor.notifyDataSetChanged();
        if (item.getTitle() == "Delete Msgs") {
//            fire.child("AppData").child("Conversations")
            Firebase messRef = fire.child("AppData").child("Conversations").child(Global.uid).child(list.get(selectedIndex).getSenderId());
            messRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSnapshot.getRef().removeValue();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        return true;
    }
}