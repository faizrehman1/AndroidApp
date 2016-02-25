package com.example.faiz.chatapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText text;
    ListView listView;
    ArrayList<Message> listt = new ArrayList<Message>();
    MyAdapter adapter;
    RadioButton radioButton, radioButton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View v = inflater.inflate(R.layout.users, null);

        // checkBox=(CheckBox)v.findViewById(R.id.checkBox);

        radioButton = (RadioButton) v.findViewById(R.id.radioButton);
        radioButton1 = (RadioButton) v.findViewById(R.id.radioButton2);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Member");
        builder.setMessage("Select Member which you want to chat");
        builder.setView(v);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Return", null);

        builder.show();

        listView = (ListView) findViewById(R.id.listv);

        text = (EditText) findViewById(R.id.etMessage);
        adapter = new MyAdapter(MainActivity.this, listt);
        listt.add(new Message("Moosa", "kh"));
        listt.add(new Message("faiz", "kahan ho"));
        listt.add(new Message("Moosa", "Kal samjha Dunga"));
        //  listt.add("Aloo","sas");

        listView.setAdapter(adapter);
        Button btn = (Button) findViewById(R.id.btSend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   listt.add(new Message(radioButton.getText().toString(), text.getText().toString()));

                if (radioButton.isChecked()) {
                    listt.add(new Message(radioButton.getText().toString(), text.getText().toString()));
                    adapter.notifyDataSetChanged();
                } else {
                    listt.add(new Message(radioButton1.getText().toString(), text.getText().toString()));
                    adapter.notifyDataSetChanged();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder1= new AlertDialog.Builder(MainActivity.this);
                        builder1.setMessage("You want to Delete ?");
                        builder1.setTitle("Delete");
                        builder1.setIcon(android.R.drawable.ic_delete);
                        builder1.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                           listt.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                        builder1.setNegativeButton("Back",null);
                        builder1.show();
                    }
                });

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
