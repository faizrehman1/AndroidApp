package com.example.faiz.fragmentexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


          getSupportFragmentManager().beginTransaction().replace(R.id.main, new BlankFragment1()).commit();

    }

    public void process(View view) {
//        Intent i = null, chooser = null;
//        if (view.getId() == R.id.btnmap) {
//
//             i = new Intent(android.content.Intent.ACTION_VIEW);
//          //  i.setData(Uri.parse("http://maps.google.com/maps?daddr=" + "40.714728f" + "," + "-73.998672f"));
//            startActivity(i);
//          //  startActivity(intent);
//
//        }
//        if (view.getId() == R.id.btnMarket) {
//             i = new Intent(android.content.Intent.ACTION_VIEW);
//            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.example.faiz.fragmentexample "));
//            startActivity(i);
//
//        }
//        if(view.getId()==R.id.btnEmail){
//            i=new Intent(android.content.Intent.ACTION_SEND);
//            i.setData(Uri.parse("mailto:"));
//            String to [] ={"faizkhanabid@yahoo.com","faizdk101@gmai.com"};
//            i.putExtra(Intent.EXTRA_EMAIL,to);
//            i.putExtra(Intent.EXTRA_SUBJECT,"try To send");
//            i.putExtra(Intent.EXTRA_TEXT,"Or BHai kia haal Hain");
//            i.setType("message/rfc822");
//            chooser = Intent.createChooser(i,"Send Email");
//            startActivity(chooser);
//
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//play store kholna hy click py han ruko
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
