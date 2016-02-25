package com.example.faiz.numtodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.Check {

    private ArrayList<TodoItems> item = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter(item, MainActivity.this);
    EditText edtTitle, edtDiscrip;
    Button btnAddTodo;
    private String phoneNumber;
    Firebase firebase;
    ListView listvieww;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        listvieww = (ListView) findViewById(R.id.ToDoItems);
        firebase = new Firebase("https://numtodo.firebaseio.com/");


        TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        phoneNumber = mTelephonyMgr.getSimSerialNumber();


        listvieww.setAdapter(adapter);

        btnAddTodo = (Button) findViewById(R.id.AddToDo);
        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View vv = inflater.inflate(R.layout.myview2, null);
                edtTitle = (EditText) vv.findViewById(R.id.edtTitle);
                edtDiscrip = (EditText) vv.findViewById(R.id.edtDiscrip);
                check = (CheckBox) vv.findViewById(R.id.checkBoxisDone);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add ToDo");
                builder.setMessage("Add Title & Discription ?");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child(phoneNumber).push().setValue(new TodoItems(edtTitle.getText().toString(), edtDiscrip.getText().toString(), check.isChecked()));

                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item Add in the List.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Back", null);
                builder.setView(vv);
                builder.show();

            }
        });

        firebase.child(phoneNumber).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                TodoItems newPost = dataSnapshot.getValue(TodoItems.class);
                item.add(new TodoItems(newPost.getTitle().toString(), newPost.getDiscription().toString(), newPost.isCheck()));
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


        listvieww.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Remove");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Do You Want to Remove.?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebase.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int i = 0;
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    if (i == position) {
                                        Firebase ref = dataSnapshot1.getRef();
                                        ref.removeValue();

                                        //
//                                        ref.child("check").setValue(true/false);
                                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                       //   finish();
                                       //   startActivity(getIntent());
                                        item.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }

                                    i++;
                                }

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                                switch (firebaseError.getCode()) {
                                    case FirebaseError.NETWORK_ERROR:
                                        Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                                        break;


                                }
                            }
                        });
                    }
                });

                builder.setNegativeButton("No", null);
                builder.show();

            }
        });

//        Firebase.getDefaultConfig().setPersistenceEnabled(true);
//        firebase.keepSynced(true);


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
    public void checked(final int position, final boolean check) {

        firebase.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if (i == position) {
                        Firebase ref = dataSnapshot1.getRef();
                        ref.child("check").setValue(!check);
                    }
                    i++;
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
