package com.example.faiz.udacitytask4.Adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faiz.udacitytask4.R;
import com.example.faiz.udacitytask4.models.Numbers;

import java.util.ArrayList;

/**
 * Created by Faiz on 8/1/2016.
 */
public class ListAdapter extends BaseAdapter {

    private int background;
    private ArrayList<Numbers> arrayList;
    private Context context;

    public ListAdapter(ArrayList<Numbers> arrayList, Context context, int background) {
        this.arrayList = arrayList;
        this.context = context;
        this.background=background;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.numberview,null);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        TextView textnumber =(TextView) view.findViewById(R.id.number);
        TextView textname =(TextView) view.findViewById(R.id.name);

        textname.setText(arrayList.get(position).getName());
        textnumber.setText(arrayList.get(position).getNumbers());
        imageView.setImageResource(arrayList.get(position).getImageResourse());

        int color = ContextCompat.getColor(context,background);
        view.setBackgroundColor(color);

        return view;
    }
}
