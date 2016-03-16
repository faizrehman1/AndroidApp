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
public class Tab4 extends android.support.v4.app.Fragment {


    public Tab4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab4, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("OnStart of Tab4", "is Running");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("OnResume of Tab4", "is Running");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("OnPause of Tab4", "is Running");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("OnStop of Tab4", "is Running");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("OnDestroyView of Tab4", "is Running");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroy of Tab4", "is Running");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("OnDetach of Tab4", "is Running");
    }
}
