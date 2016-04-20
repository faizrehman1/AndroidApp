package com.example.faiz.todolistfire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MyAdapter.Check {
    private ArrayList<Message> list = new ArrayList<>();
    MyAdapter adapter = new MyAdapter(list, MainActivity.this);
    private ListView listView;
    Button btn;
    EditText text, text1;
    CheckBox checkBox;
    Firebase firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://todolistf.firebaseio.com/");
        //time
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);

        final String var = dateFormat.format(date);


        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                LayoutInflater layoutinflate = LayoutInflater.from(MainActivity.this);
                View vieww = layoutinflate.inflate(R.layout.myview1, null);

                text = (EditText) vieww.findViewById(R.id.edt);
                text1 = (EditText) vieww.findViewById(R.id.edt1);
                checkBox = (CheckBox) vieww.findViewById(R.id.checkboxN);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(" Add Your Next ToDo");
                builder.setMessage("Add some in ToDoList");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        firebase.child("Data").push().setValue(new Message(text.getText().toString(), text1.getText().toString(), var, checkBox.isChecked()));
                        Toast.makeText(MainActivity.this, "Item Add in the List.", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("Return", null);
                builder.setView(vieww);
                builder.show();


            }
        });

        firebase.child("Data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //    Log.d("",dataSnapshot.getValue().toString());
                Message newPost = dataSnapshot.getValue(Message.class);
                list.add(new Message(newPost.getName().toString(), newPost.getDiscription().toString(), newPost.getDate(), newPost.isCheck()));
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Remove");
                builder.setMessage("You want tO Delete..?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int i = 0;
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    if (i == position) {
                                        Firebase ref = dataSnapshot1.getRef();
                                        ref.removeValue();
                                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                        list.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                    i++;
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("Back", null);
                builder.show();

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

    @Override
    public void checked(final int position, final boolean checked) {

        firebase.child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int j = 0;
                for (DataSnapshot m : dataSnapshot.getChildren()) {
                    Firebase f = m.getRef();
                    if (j == position) {
                        f.child("check").setValue(!checked);
                    }
                    j++;

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}


