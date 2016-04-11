package com.example.faiz.tablayout_with_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class LayoutAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments;

    public LayoutAdapter(FragmentManager fm, ArrayList<Fragment> fragment) {
        super(fm);
        this.fragments = fragment;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
