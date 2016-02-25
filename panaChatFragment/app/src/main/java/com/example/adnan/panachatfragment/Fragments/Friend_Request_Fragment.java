package com.example.adnan.panachatfragment.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adnan.panachatfragment.Adaptors.FriendReq_ADaptor;
import com.example.adnan.panachatfragment.Signatures.Frient_Req_Signature;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.UTils.Global;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Friend_Request_Fragment extends Fragment {
    ListView listView;
    ArrayList<Frient_Req_Signature> list;
    Firebase fire;
    FriendReq_ADaptor adaptor;
    String key;

    public Friend_Request_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fire = new Firebase(Global.url);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.friend_req_fragment, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        list = new ArrayList<>();

        fire.child("AppData").child("FriendRequests").child(Global.uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                key = dataSnapshot.getKey();

                Frient_Req_Signature signature = dataSnapshot.getValue(Frient_Req_Signature.class);

                list.add(new Frient_Req_Signature(signature.getName(), signature.getPicUrl(), key));

                adaptor.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        adaptor = new FriendReq_ADaptor(list, getActivity());
        listView.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final int posi = position;
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("What You Want To Do");
                alert.setMessage("Are you sure you want to add this person ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idFriend = list.get(position).getSenderId();
                        fire.child("AppData").child("Friends").child(list.get(position).getSenderId()).child(Global.uid).setValue(new Frient_Req_Signature(Global.name, Global.picUrl, Global.uid));
                        fire.child("AppData").child("Friends").child(Global.uid).child(list.get(position).getSenderId()).setValue(new Frient_Req_Signature(list.get(position).getName(), list.get(position).getPicUrl(), list.get(position).getSenderId()));

                        fire.child("AppData").child("FriendRequests").child(Global.uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int i = 0;
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    if (i == position) {
                                        Firebase ref = dataSnapshot1.getRef();
                                        ref.removeValue();
                                        list.remove(position);

                                        adaptor.notifyDataSetChanged();
                                    } else {
                                        i++;
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        Toast.makeText(getActivity(), "Friend Is Add To Your List ", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.create();
                alert.show();
            }
        });

        return v;

    }



}
