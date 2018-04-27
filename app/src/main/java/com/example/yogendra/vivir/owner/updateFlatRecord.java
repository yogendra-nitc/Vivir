package com.example.yogendra.vivir.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.network.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class updateFlatRecord extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextaptName, editTextLocality,editTextCity, editTextType, editTextRent;
    private Button setAptButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_flat_record);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // method call for fetching Apt data
        getAptEdit();

        editTextaptName = findViewById(R.id.aptName);
        editTextLocality = findViewById(R.id.aptLocality);
        editTextCity = findViewById(R.id.aptCity);
        editTextType = findViewById(R.id.aptType);
        editTextRent = findViewById(R.id.rentAmt);
        setAptButton = (Button)findViewById(R.id.updateApt);
        setAptButton.setOnClickListener(this);
    }

    // METHOD FOR GETTING APARTMENT DETAILS

    private void getAptEdit(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        Intent in = getIntent();
        final String aptId =in.getStringExtra("aptId");
        progressDialog.setMessage("loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_GET_APT_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);

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
                params.put("aptId",aptId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    //METHOD FOR SETTING APARTMENT DETAILS
    private void setAptEdit(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        Intent in = getIntent();
        final String aptId =in.getStringExtra("aptId");
        final String aptName = editTextaptName.getText().toString().trim();
        final String aptLocality = editTextLocality.getText().toString().trim();
        final String aptCity = editTextCity.getText().toString().trim();
        final String aptType = editTextType.getText().toString().trim();
        final String rentAmt = editTextRent.getText().toString().trim();
        progressDialog.setMessage("loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_SET_APT_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),
                                    jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(getIntent());

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
                params.put("aptId",aptId);
                params.put("aptName",aptName);
                params.put("aptLocality",aptLocality);
                params.put("aptCity",aptCity);
                params.put("aptType",aptType);
                params.put("aptRent",rentAmt);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    @Override
    public void onClick(View v) {
        if(v == setAptButton){
            setAptEdit();
        }
    }
}
