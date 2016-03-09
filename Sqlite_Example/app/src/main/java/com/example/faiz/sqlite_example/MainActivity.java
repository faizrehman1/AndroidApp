package com.example.faiz.sqlite_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText edt;
    TextView text;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.editText);
        text = (TextView) findViewById(R.id.RecievetextView);
        db = new Database(this, null, null, 1);

        printDatabase();

        Button addbtn = (Button) findViewById(R.id.addbutton);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddButton();
            }
        });

        Button dlt = (Button) findViewById(R.id.button2);
        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDb();
            }
        });

    }

    public void AddButton() {
        MyProducts pro = new MyProducts(edt.getText().toString());
        db.Addvalue(pro);
      //  printDatabase();
    }


    public void printDatabase() {
        String db_string = db.databaseTostring();
        text.setText(db_string);
        AddButton();

    }
    public void DeleteDb(){

        String db_string =edt.getText().toString();
        db.DeleteProduct(db_string);
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
