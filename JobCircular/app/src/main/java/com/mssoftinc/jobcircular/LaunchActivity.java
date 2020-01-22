package com.mssoftinc.jobcircular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonPrimitive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LaunchActivity extends AppCompatActivity {
String URL="http://10.0.2.2/job/getdata.php";
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);


                //creating a string array for listview
                String[] heroes = new String[jsonArray.length()];

                //looping through all the elements in json array
                for (int i = 0; i < jsonArray.length(); i++) {

                    //getting json object from the json array
                    JSONObject obj = jsonArray.getJSONObject(i);

                    //getting the name from the json object and putting it inside string array
                    heroes[i] = obj.getString("name");
                    Tab.list.add(obj.getString("name"));
                }
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                    
                } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);
    }
    }

