package com.example.faiz.tablayout.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faiz.tablayout.models.News_Objects;
import com.example.faiz.tablayout.R;

import java.util.ArrayList;

public class News_View_Adapter extends BaseAdapter{

   private ArrayList<News_Objects> arrayList;
   private  Context context;

    public News_View_Adapter(ArrayList<News_Objects> arrayList, Context context) {
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
        View view = layoutInflater.inflate(R.layout.news_view,null);

        TextView text_heading = (TextView)view.findViewById(R.id.textView_heading);
        TextView text_paragraph = (TextView)view.findViewById(R.id.textView_paragraph);


        ImageView img = (ImageView)view.findViewById(R.id.imagelike);
        ImageView img1 = (ImageView)view.findViewById(R.id.imageView_share);
        ImageView img2 = (ImageView)view.findViewById(R.id.imageView_thumb);
        ImageView img3 = (ImageView)view.findViewById(R.id.imageView_main);
        img3.setImageResource(R.drawable.bbc1);


        text_heading.setText(arrayList.get(position).getHeading());
        text_paragraph.setText(arrayList.get(position).getParagraph());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Favourite",Toast.LENGTH_LONG).show();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Share",Toast.LENGTH_LONG).show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Like",Toast.LENGTH_LONG).show();
            }
        });





        return view;
    }
}
