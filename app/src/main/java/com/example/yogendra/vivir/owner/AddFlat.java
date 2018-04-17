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
import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.network.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddFlat extends AppCompatActivity implements View.OnClickListener {

    private EditText APT_NAME, APT_CITY,APT_LOCALITY, APT_TYPE,RENT_AMT;
    private Button addAptButton;
    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager_obj;
    private String aptId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        APT_NAME = findViewById(R.id.aptName);
        APT_LOCALITY = findViewById(R.id.aptLocality);
        APT_CITY = findViewById(R.id.aptCity);
        APT_TYPE = findViewById(R.id.aptType);
        RENT_AMT = findViewById(R.id.rentAmt);

        addAptButton = (Button)findViewById(R.id.addApt);

        progressDialog = new ProgressDialog(this);

        addAptButton.setOnClickListener(this);

    }

    public void addApt(){
        final String aptName = APT_NAME.getText().toString().trim();
        final String aptLocality = APT_LOCALITY.getText().toString().trim();
        final String aptCity = APT_CITY.getText().toString().trim();
        final String aptType = APT_TYPE.getText().toString().trim();
        final String rentAmt = RENT_AMT.getText().toString().trim();

        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());
        final String ownerEmail = sharedPrefManager_obj.getKeyEmail();

        progressDialog.setMessage("processing...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_ADD_APT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")) {
                                aptId = jsonObject.getString("aptId");
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                Intent in = new Intent(AddFlat.this, AddImage.class);
                                in.putExtra("aptId", aptId);
                                startActivity(in);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ownerEmail",ownerEmail);
                params.put("aptName",aptName);
                params.put("aptLocality",aptLocality);
                params.put("aptCity",aptCity);
                params.put("aptType",aptType);
                params.put("rentAmt",rentAmt);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if(v == addAptButton)
        {
            addApt();
        }
    }
}
