package com.example.faiz.tablayout_with_viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    List<ToDoObject> arrayList;
    Context context;


    public ListViewAdapter(List<ToDoObject> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ToDoObject getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview, null);

        TextView text1 = (TextView) view.findViewById(R.id.textView);
        TextView text2 = (TextView) view.findViewById(R.id.textView2);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.todo);



        text1.setText(arrayList.get(position).getTitle());
        text2.setText(arrayList.get(position).getDiscription());

        return view;
    }
}
