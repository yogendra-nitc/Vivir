package com.example.yogendra.vivir.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.adapter.SearchActivityAdapter;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.network.RequestHandler;
import com.example.yogendra.vivir.tenant.SearchItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFlats extends AppCompatActivity {
    RecyclerView searchResult;
    List<SearchItem> flatList = new ArrayList<>();

    SearchActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_flats);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchResult = findViewById(R.id.searchResult);
        searchResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        searchResult.setLayoutManager(linearLayoutManager);
        //adapter = new SearchActivityAdapter(flatList, RegUserSearch.this);
        //searchResult.setAdapter(adapter);
        getFlatData();
    }

    private void getFlatData(){

        Intent in = getIntent();
        final String ownerId = in.getStringExtra("ownerId");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_myApartment,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("AllFlat");

                            for(int i=0; i<array.length(); i++)
                            {
                                JSONObject o = array.getJSONObject(i);
                                SearchItem item = new SearchItem(
                                        o.getString("aptId"),
                                        o.getString("aptName"),
                                        o.getString("locality"),
                                        o.getString("city"),
                                        o.getString("rentAmt"),
                                        o.getString("aptType"),
                                        o.getString("img1")
                                );
                                flatList.add(item);
                            }

                            adapter = new SearchActivityAdapter(flatList,getApplicationContext());
                            searchResult.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map <String,String> params = new HashMap<>();
                params.put("ownerId",ownerId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
