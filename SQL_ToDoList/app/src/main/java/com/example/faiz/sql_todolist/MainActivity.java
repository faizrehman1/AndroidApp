package com.example.faiz.sql_todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDoObjects> arrayList = new ArrayList<>();
    private MyAdapter adapter;

    private Database db = new Database(this);
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // collect(arrayList);
            getTodo();
            addItems();

        //arrayList.add(new ToDoObjects("LOL", "Hahaaha", false));
    }
       private void addItems() {

            Button addToDobtn = (Button) findViewById(R.id.addbtn);
            addToDobtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater layout = LayoutInflater.from(MainActivity.this);
                    View view = layout.inflate(R.layout.alertview, null);
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Add Some Todo");
                    alert.setMessage("Would You Like tO Add Some ToDo");

                    final EditText edt = (EditText) view.findViewById(R.id.editText);
                    final EditText edt1 = (EditText) view.findViewById(R.id.editText2);
                    final CheckBox check = (CheckBox) view.findViewById(R.id.checkBoxcom);


                    alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                         //   arrayList.add(new ToDoObjects(edt.getText().toString(), edt1.getText().toString(), check.isChecked(), 0));
                            ToDoObjects toDoObjects = new ToDoObjects(edt.getText().toString(), edt1.getText().toString(), check.isChecked(), 0);
                            arrayList.add(toDoObjects);
                           db.saveData(arrayList);

                            adapter.notifyDataSetChanged();
                            //   db.getData();
                            //   getTodo();

                        }
                    });
                    alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alert.setView(view);
                    alert.show();


                }
            });
        }






    private void getTodo(){
        listView= (ListView) findViewById(R.id.listvieww);
      //  arrayList.add(new ToDoObjects("adsa","asd",true,0));
       // Log.d("LOL", String.valueOf(arrayList));
        arrayList = db.getData();
        adapter = new MyAdapter(arrayList,MainActivity.this);


        listView.setAdapter(adapter);
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
