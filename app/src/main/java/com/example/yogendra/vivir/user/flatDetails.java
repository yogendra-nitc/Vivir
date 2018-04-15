package com.example.yogendra.vivir.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.adapter.ViewPagerAdapter;
import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.network.RequestHandler;
import com.example.yogendra.vivir.owner.OwnerDashboard;
import com.example.yogendra.vivir.tenant.user_dashboard;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class flatDetails extends AppCompatActivity {

    ViewPager viewPager;
    private ProgressDialog progressDialog;
    private TextView aptAdd,AptOwner,ownerEmail,ownerContact,rentAmt;
    private Button bookingRequest,paymentRequest;
    SharedPrefManager sharedPrefManager_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_details);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        myApartment();
        aptAdd = (TextView)findViewById(R.id.aptAdd);
        AptOwner = (TextView)findViewById(R.id.aptOwner);
        ownerEmail = (TextView)findViewById(R.id.ownerEmail);
        ownerContact = (TextView)findViewById(R.id.ownerContact);
        rentAmt = (TextView)findViewById(R.id.aptRent);
    }

    // Apartment Details Display
    private void myApartment()
    {
        Intent in = getIntent();
        final String aptId = in.getStringExtra("flatId");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_getApt,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                aptAdd.setText(obj.getString("aptAdd"));
                                AptOwner.setText(obj.getString("ownerName"));
                                ownerEmail.setText(obj.getString("email"));
                                ownerContact.setText(obj.getString("contact"));
                                rentAmt.setText("Rs. "+obj.getString("rentAmt")+"/month"+" , "+
                                        obj.getString("aptType")+" BHK");

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
                params.put("aptId",aptId);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    public void sendRequest()
    {
        // Method for sending request
        /* bookingRequest = (Button) findViewById(R.id.play);
        bookingRequest.setVisibility(View.VISIBLE);
        bookingRequest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //when play is clicked show stop button and hide play button
                playButton.setVisibility(View.GONE);
                paymentRequest.setVisibility(View.VISIBLE);
            }
        });*/
    }
}