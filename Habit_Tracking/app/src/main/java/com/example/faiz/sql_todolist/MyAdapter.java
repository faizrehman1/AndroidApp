package com.example.faiz.sql_todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

public class MyAdapter extends BaseAdapter implements ListAdapter {

    List<Habits> arrayList;
    Context context;


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
        return arrayList.get(position).getId();
    }

    public MyAdapter(List<Habits> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.myview,null);

        TextView text1= (TextView) view.findViewById(R.id.textView);
 //       TextView text2= (TextView) view.findViewById(R.id.textView2);
     //   CheckBox check = (CheckBox) view.findViewById(R.id.checkBox);

        CheckBox checkMon = (CheckBox)view.findViewById(R.id.Monday);
        CheckBox checkTue = (CheckBox)view.findViewById(R.id.Tuesaday);
        CheckBox checkWed = (CheckBox)view.findViewById(R.id.wed);
        CheckBox checkThu = (CheckBox)view.findViewById(R.id.thu);
        CheckBox checkFri = (CheckBox)view.findViewById(R.id.fir);
        CheckBox checkSat = (CheckBox)view.findViewById(R.id.sat);
        CheckBox checkSun = (CheckBox)view.findViewById(R.id.sunday);

        text1.setText(arrayList.get(position).getTitle());
   //     text2.setText(arrayList.get(position).getDiscription());


     //   if(arrayList.get(position).equals("true")) {
        //    check.setChecked(Boolean.parseBoolean(arrayList.get(position).getCheck()));
      //  }else{
         String s = arrayList.get(position).getDiscription();
        if(s.equals("Monday")){
            checkMon.setChecked(true);
        }
       else if(s.equals("Tuesday")){
            checkTue.setChecked(true);
        }
       else if(s.equals("Wednesday")){
            checkWed.setChecked(true);
        }
      else  if(s.equals("Thursday")){
            checkThu.setChecked(true);
        }
      else  if(s.equals("Friday")){
            checkFri.setChecked(true);
        }
     else   if(s.equals("Saturday")){
            checkSat.setChecked(true);
        }
      else  if(s.equals("Sunday")){
            checkSun.setChecked(true);
        }
       // }



        return view;
    }

}
