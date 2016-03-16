package com.example.faiz.tablayout_with_viewpager;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2 extends android.support.v4.app.Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("OnStart of Tab2", "is Running");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("OnResume of Tab2", "is Running");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("OnPause of Tab2", "is Running");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("OnStop of Tab2", "is Running");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroy of Tab2","is running");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("OnDistroyView of Tab2","is running");
    }

    @Override
    public void onDetach() {
        super.onDetach();
   Log.d("OnDetach of Tab2","is running");
    }
}
