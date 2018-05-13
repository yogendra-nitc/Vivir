package com.example.yogendra.vivir.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.adapter.complainAdapter;
import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.network.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class complain extends AppCompatActivity {
    SharedPrefManager sharedPrefManager_obj;
    RecyclerView complainResult;
    List<complainItem> complainList = new ArrayList<>();

    complainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager_obj = SharedPrefManager.getInstance(this);
        setContentView(R.layout.activity_complain);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        complainResult = findViewById(R.id.complainResult);
        complainResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        complainResult.setLayoutManager(linearLayoutManager);
        getcomplainList();
    }

// METHOD FOR GETTING ALL APARTMENTS LIST

    private void getcomplainList(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data...");
        final String userId = sharedPrefManager_obj.getKeyEmail();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_ALL_COMPLAIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("AllComplain");

                            for(int i=0; i<array.length(); i++)
                            {
                                JSONObject o = array.getJSONObject(i);
                                complainItem item = new complainItem(
                                        o.getString("cid"),
                                        o.getString("sid"),
                                        o.getString("rid"),
                                        o.getString("cdate")
                                );
                                complainList.add(item);
                            }

                            adapter = new complainAdapter(complainList,getApplicationContext());
                            complainResult.setAdapter(adapter);

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
                params.put("userId",userId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    // Method for adding apartment
    public void writeComplain(View v)
    {
        Intent in = new Intent(complain.this, writeComplain.class);
        startActivity(in);
    }
}
