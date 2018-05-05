package com.example.yogendra.vivir.tenant;

import android.app.ProgressDialog;
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

public class uploadRentDetails extends AppCompatActivity implements View.OnClickListener {
    private EditText aptId,amtPaid,paymentDate;
    private Button fillRentButton;
    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_rent_details);
        //Adding back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());
        aptId = findViewById(R.id.apt_id);
        amtPaid = findViewById(R.id.amt_paid);
        paymentDate = findViewById(R.id.payment_date);
        fillRentButton = findViewById(R.id.rent_submit);

        progressDialog = new ProgressDialog(this);

        fillRentButton.setOnClickListener(this);

    }

    public void rentDetails(){
        final String apartmentId = aptId.getText().toString().trim();
        final String amountPaid = amtPaid.getText().toString().trim();
        final String pdate = paymentDate.getText().toString().trim();
        final String userId = sharedPrefManager_obj.getKeyEmail();
        progressDialog.setMessage("waiting...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_FILL_RENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("aptId",apartmentId);
                params.put("amtPaid",amountPaid);
                params.put("userEmail",userId);
                params.put("pdate",pdate);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == fillRentButton){
            rentDetails();
        }
    }
}
