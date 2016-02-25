package com.example.adnan.panachatfragment.Fragments;


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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.UTils.Global;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import github.ankushsachdeva.emojicon.EmojiconTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class friend_profile extends Fragment {

    TextView name, contact, birthday;
    EmojiconTextView status;
    CircularImageView pic;
    Firebase fire = new Firebase(Global.url);

    public friend_profile() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_my_profile, container, false);
        name = (TextView) v.findViewById(R.id.mProfileName);
        contact = (TextView) v.findViewById(R.id.mProfileContact);
        birthday = (TextView) v.findViewById(R.id.mProfileBirthday);
        status = (EmojiconTextView) v.findViewById(R.id.mProfileStatus);
        pic = (CircularImageView) v.findViewById(R.id.mProfileImage);
        pic.setBorderColor(R.color.grey);
        pic.setBorderWidth(0);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.myanimation);
                pic.startAnimation(animation);
            }
        });
        fire.child("AppData").child("BasicInfo").child(Global.PartnaerId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contact.setText(dataSnapshot.child("contact").getValue().toString());
                birthday.setText(dataSnapshot.child("birthday").getValue().toString());
                status.setText(dataSnapshot.child("status").getValue().toString());
                name.setText(dataSnapshot.child("name").getValue().toString());

                Picasso.with(getActivity()).load(dataSnapshot.child("picUrl").getValue().toString()).error(R.drawable.userdefaul).placeholder(R.drawable.userdefaul).into(pic);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return v;
    }

}


