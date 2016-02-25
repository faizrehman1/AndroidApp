package com.example.faiz.listview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Message> messages;
    private Context context;

    public MyAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.myview, null);
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.imageViewMessage);
        TextView name = (TextView) view.findViewById(R.id.textViewMessagesName);
        TextView mesg = (TextView) view.findViewById(R.id.textView2Messagesmsg);
        radioButton.setChecked(messages.get(position).isRadio());
     //   imageView.setImageResource(messages.get(position).getImageRes());
        name.setText(messages.get(position).getName());
        mesg.setText(messages.get(position).getMessage());
        return view;
    }
}
