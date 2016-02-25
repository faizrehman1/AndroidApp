package com.example.faiz.listview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String a[] = {"moosa", "faiz", "adnan", "kamran"};
    private ListView b;
    private ArrayList<Message> myMessage = new ArrayList<>();
    EditText text, text1;
    MyAdapter adapter = new MyAdapter(myMessage, MainActivity.this);
    private static final int browse_image = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        b = (ListView) findViewById(R.id.mylistview); //cast
        //   b.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, a)); //view
        myMessage.add(new Message("Main uni mai hun", "imran", true));
        myMessage.add(new Message("Firebase ho gaya", "adnan", false));
        myMessage.add(new Message("Match khelny chalien", "Samad", true));
        myMessage.add(new Message("Aj me nahi aaraha", "Kami", false));
        myMessage.add(new Message("Bike kharab ho gai", "Bilal", false));


        b.setAdapter(adapter);
        b.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //anonymous class
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete");
                builder.setIcon(android.R.drawable.stat_sys_warning);
                builder.setMessage("You want to Delete.?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMessage.remove(position);

                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Return", null);
                builder.show();
            }
        });
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View viewDialog = layoutInflater.inflate(R.layout.myview1, null);
                text = (EditText) viewDialog.findViewById(R.id.edit);
                text1 = (EditText) viewDialog.findViewById(R.id.edit1);
                final CheckBox checkBox = (CheckBox) viewDialog.findViewById(R.id.checkboxDialog);
                builder.setView(viewDialog);
                builder.setTitle("Add Member");
                builder.setMessage("Add Name and Message");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMessage.add(new Message(text1.getText().toString(), text.getText().toString(),checkBox.isChecked()));
                        adapter.notifyDataSetChanged();
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

    //abi masla btao
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
