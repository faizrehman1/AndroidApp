package com.example.faiz.music_player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Songname> arrayList;
    Context context;


    public MyAdapter(ArrayList<Songname> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();

    }

    @Override
    public Songname getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.myview, null);

        TextView txt = (TextView)view.findViewById(R.id.txtname);
        txt.setText(arrayList.get(position).getSongnamee());

        TextView txttitle = (TextView)view.findViewById(R.id.txttitle);
        txttitle.setText(arrayList.get(position).getSongpath());
        return view;
    }
}
