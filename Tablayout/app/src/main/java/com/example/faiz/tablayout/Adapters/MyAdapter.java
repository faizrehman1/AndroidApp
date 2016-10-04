package com.example.faiz.tablayout.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class MyAdapter extends FragmentStatePagerAdapter {
  private   ArrayList<Fragment> fragments;

    public MyAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments =fragments ;
    }

    @Override
    public Fragment getItem(int i) {

       //han bhai..jo tumhara single frag hai usky layout me jao

            return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
