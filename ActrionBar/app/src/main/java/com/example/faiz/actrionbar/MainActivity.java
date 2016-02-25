package com.example.faiz.actrionbar;

import android.app.SearchManager;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameText;
    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.editText);

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
        //  if (id == R.id.action_speech) {
        switch (id) {

            case R.id.action_call:
                Intent dialer= new Intent(Intent.ACTION_DIAL);
                startActivity(dialer);
                return true;
              //  break;
            case R.id.action_speech:
                Intent sp = new Intent(Intent.ACTION_VOICE_COMMAND);
                String term = nameText.getText().toString();
                sp.putExtra(SearchManager.QUERY, term);
                startActivity(sp);
                break;
            case R.id.action_contacts:
                Toast.makeText(this,"Contact clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this,"Setting clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_status:
                Toast.makeText(this,"Status clicked",Toast.LENGTH_SHORT).show();
                break;

            default:
               return super.onOptionsItemSelected(item);


        }
            return true;
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String voice_text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
            Toast.makeText(this, voice_text, Toast.LENGTH_LONG).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
      //  return super.onOptionsItemSelected(item);


