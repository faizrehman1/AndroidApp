package com.example.faiz.volleyexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private  ProgressDialog pDialog;
  private    RequestQueue queue;
  private   TextView textView;
    private Button btnJsonObj,btnJsonStr,btnJsonArray;
    private ArrayList<String> name;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.content);
        btnJsonObj = (Button)findViewById(R.id.jsobjreq);
        btnJsonStr = (Button)findViewById(R.id.jsstrreq);
        btnJsonArray = (Button)findViewById(R.id.jsarray);
        listView = (ListView)findViewById(R.id.list_item);
        queue = Volley.newRequestQueue(this);

        String tag_json_obj = "json_obj_req";


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");



        btnJsonObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.show();
                JsonObjectRequest();
            }
        });

        btnJsonStr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.show();
                JsonStringReq();
            }
        });

        btnJsonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.show();
                JsonArrayReq();
            }
        });



    }


    public void JsonObjectRequest(){

        listView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);

        String url = "http://api.androidhive.info/volley/person_object.json";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());

                        try {
                            String s = response.getString("name");
                            Log.d("Name",s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        textView.setText(response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        });


        queue.add(jsonObjReq);

    }

    public void JsonStringReq(){

        textView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        String url = "http://api.androidhive.info/volley/string_response.html";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());

                textView.setText(response.toString());
                pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        queue.add(strReq);
    }

    public void JsonArrayReq(){
        String url = "http://api.androidhive.info/volley/person_array.json";

        listView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);


        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", response.toString());

                        name = new ArrayList<>();
                        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,name);

                        listView.setAdapter(adapter);

                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                  //  JSONObject jsonObject = object.getJSONObject("name");
                                    String string = object.getString("name");
                                    Log.d("Array",string);
                                    name.add(string);
                                adapter.notifyDataSetChanged();
                                   // textView.setText(string.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                      //  textView.setText(response.toString());

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        queue.add(req);
    }
}
