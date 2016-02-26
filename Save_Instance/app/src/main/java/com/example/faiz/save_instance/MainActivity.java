package com.example.faiz.save_instance;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView txt;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.text1);
        edt = (EditText) findViewById(R.id.edt1);


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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Model model=savedInstanceState.getParcelable("ghg");
        String ab = model.getName();
        int abb=model.getId();
        Log.d("value",ab+" "+abb);

        String faiz = savedInstanceState.getString("save_state");

        if (faiz == null) {
            Toast.makeText(MainActivity.this, "OnRestoreInstanceState : No save \n" + faiz, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "OnRestoreInstanceState : save \n" + faiz, Toast.LENGTH_SHORT).show();
        }

        txt.setText(faiz);
        edt.setText(faiz);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("ghg",new Model(9,"kjh"));

        String adnan = edt.getText().toString();
        outState.putString("save_state", adnan);

        Toast.makeText(MainActivity.this, "OnSaveInstanceState: - save \n" + adnan, Toast.LENGTH_SHORT).show();

    }
}
