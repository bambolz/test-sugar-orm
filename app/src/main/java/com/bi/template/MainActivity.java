package com.bi.template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bi.template.model.MockObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button button = (Button) findViewById(R.id.buttonShow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(MainActivity.this, TitleListActivity.class);
                startActivity(listIntent);
            }
        });
        button.setVisibility(View.GONE);

        // basic simply volley request queue
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://jsonplaceholder.typicode.com/posts";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DEBUG", "onResponse: "+response);
                        // using gson to parse json into objects
                        Type listType = new TypeToken<ArrayList<MockObject>>() {
                        }.getType();
                        List<MockObject> mockObjects = new Gson().fromJson(response, listType);
                        for (MockObject mock : mockObjects) {
                            //saving into sqlite
                            mock.save();
                        }
                        button.setVisibility(View.VISIBLE);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "failed to load", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onDestroy() {
        //delete all data
        MockObject.deleteAll(MockObject.class);
        super.onDestroy();
    }
}
