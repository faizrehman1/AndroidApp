package com.example.faiz.tablayout_with_viewpager;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends android.support.v4.app.Fragment {


    @Override
    public void onStart() {
        super.onStart();
        Log.d("OnStart","is Running");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("OnResume of Tab1", "is Running");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("OnPause of Tab1", "is Running");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("OnActivityCreated of Tab1", "is Running");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("OnCreate  of Tab1", "is Running");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("OnDetach of Tab1", "is Running");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("OnStop of Tab1", "is Running");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroy of Tab1", "is Running");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("OnDestroyView of Tab1", "is Running");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("OnAttach of Tab1", "is Running");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("OnCreateView","is Running");
        return inflater.inflate(R.layout.fragment_blank, container, false);

    }


}
