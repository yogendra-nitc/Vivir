package com.example.yogendra.vivir.owner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class requestDetails extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog progressDialog;
    private TextView specificRequest,tenantName,aptName,rDate,Dues,paymentAmount,paymentDate;
    private Button acceptRequest, rejectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        // adding back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String rtype =intent.getStringExtra("rtype");

        //Hiding the request specific buttons
        if(rtype.equals("booking"))
        {
            LinearLayout duesLayout=(LinearLayout)this.findViewById(R.id.dues_layout);
            duesLayout.setVisibility(LinearLayout.GONE);

            LinearLayout paymentAmountLayout=(LinearLayout)this.findViewById(R.id.payment_amount_layout);
            paymentAmountLayout.setVisibility(LinearLayout.GONE);

            LinearLayout paymentDateLayout=(LinearLayout)this.findViewById(R.id.payment_date_layout);
            paymentDateLayout.setVisibility(LinearLayout.GONE);

            //Setting top title
            specificRequest = (TextView)findViewById(R.id.specific_request);
            specificRequest.setText("Booking Request");
        }
        else if(rtype.equals("leaving"))
        {
            LinearLayout profileLayout=(LinearLayout)this.findViewById(R.id.profile_layout);
            profileLayout.setVisibility(LinearLayout.GONE);

            LinearLayout paymentAmountLayout=(LinearLayout)this.findViewById(R.id.payment_amount_layout);
            paymentAmountLayout.setVisibility(LinearLayout.GONE);

            LinearLayout paymentDateLayout=(LinearLayout)this.findViewById(R.id.payment_date_layout);
            paymentDateLayout.setVisibility(LinearLayout.GONE);

            //Setting top title
            specificRequest = (TextView)findViewById(R.id.specific_request);
            specificRequest.setText("Leaving Request");

        }
        else
        {
            LinearLayout profileLayout=(LinearLayout)this.findViewById(R.id.profile_layout);
            profileLayout.setVisibility(LinearLayout.GONE);

            LinearLayout duesLayout=(LinearLayout)this.findViewById(R.id.dues_layout);
            duesLayout.setVisibility(LinearLayout.GONE);

            //Setting top title
            specificRequest = (TextView)findViewById(R.id.specific_request);
            specificRequest.setText("Rent Approval");
        }

        acceptRequest = findViewById(R.id.accept_request);
        rejectRequest = findViewById(R.id.reject_request);

        tenantName = (TextView)findViewById(R.id.tenant_name);
        aptName = (TextView)findViewById(R.id.apt_name);
        rDate = (TextView) findViewById(R.id.rdate);
        Dues = findViewById(R.id.dues_text_view);
        paymentAmount = findViewById(R.id.payment_amount);
        paymentDate = findViewById(R.id.payment_date);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        requestDetails();
        acceptRequest.setOnClickListener(this);
        rejectRequest.setOnClickListener(this);

    }

    // Get Request Details
    private void requestDetails()
    {
        Intent in = getIntent();
        final String rid =in.getStringExtra("rid");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_getRequestDetails,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                tenantName.setText(obj.getString("tenantName"));
                                aptName.setText(obj.getString("aptName"));
                                rDate.setText(obj.getString("rDate"));

                                if((obj.getString("rtype")).equals("leaving"))
                                    Dues.setText(obj.getString("Dues"));
                                else if((obj.getString("rtype")).equals("rent")) {
                                    paymentAmount.setText("Rs." + obj.getString("paymentAmount"));
                                    paymentDate.setText(obj.getString("paymentDate"));
                                }
                            }
                            else
                            {
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map <String, String> params = new HashMap<>();
                params.put("rid",rid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

// METHOD FOR ACCEPTING REQUEST
    public void acceptRequest()
    {
        Intent in = getIntent();
        final String rid =in.getStringExtra("rid");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_ACCEPT_REQUEST,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(
                                    getApplicationContext(),
                                    obj.getString("message"),
                                    Toast.LENGTH_LONG
                            ).show();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map <String, String> params = new HashMap<>();
                params.put("rid",rid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

// METHOD FOR REJECTING REQUEST
    public void rejectRequest()
    {
        Intent in = getIntent();
        final String rid =in.getStringExtra("rid");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_REJECT_REQUEST,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                              Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map <String, String> params = new HashMap<>();
                params.put("rid",rid);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void viewProfile(View view) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.accept_request:
                acceptRequest();
                break;
            case R.id.reject_request:
                rejectRequest();
                break;
        }
    }
}
