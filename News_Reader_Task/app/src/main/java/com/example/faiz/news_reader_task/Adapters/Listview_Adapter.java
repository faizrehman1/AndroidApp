package com.example.faiz.news_reader_task.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.faiz.news_reader_task.R;
import com.example.faiz.news_reader_task.models.News_Object;

import java.util.ArrayList;

public class Listview_Adapter extends BaseAdapter{

   private ArrayList<News_Object> arrayList;
    private Context context;

    public Listview_Adapter(ArrayList<News_Object> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dataview,null);




        return view;
    }
}
