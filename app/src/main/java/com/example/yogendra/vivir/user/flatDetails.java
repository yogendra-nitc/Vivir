package com.example.yogendra.vivir.user;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.yogendra.vivir.owner.updateFlatRecord;
import com.example.yogendra.vivir.tenant.RegUserSearch;
import com.example.yogendra.vivir.tenant.user_dashboard;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class flatDetails extends AppCompatActivity implements View.OnClickListener {

    //ViewPager viewPager;
    private ImageButton editAptBtn,delAptBtn;
    private Button bookAptBtn;
    private ProgressDialog progressDialog;
    private TextView aptAdd,AptOwner,ownerEmail,ownerContact,rentAmt;
    private ImageView flatImage;
    SharedPrefManager sharedPrefManager_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_details);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());
        //Hiding the owner specific buttons
       if(sharedPrefManager_obj.getKeyUtype().equals("tenant"))
        {
            LinearLayout delete_layout=(LinearLayout)this.findViewById(R.id.deleteLayout);
            delete_layout.setVisibility(LinearLayout.GONE);
        }
         else
        {
            LinearLayout request_layout=(LinearLayout)this.findViewById(R.id.requestLayout);
            request_layout.setVisibility(LinearLayout.GONE);

        }

       // viewPager = (ViewPager)findViewById(R.id.viewPager);

        //ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
       // viewPager.setAdapter(viewPagerAdapter);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        myApartment();
        aptAdd = (TextView)findViewById(R.id.aptAdd);
        AptOwner = (TextView)findViewById(R.id.aptOwner);
        ownerEmail = (TextView)findViewById(R.id.ownerEmail);
        ownerContact = (TextView)findViewById(R.id.ownerContact);
        rentAmt = (TextView)findViewById(R.id.aptRent);
        flatImage = findViewById(R.id.flat_image);

        // BUTTON FOR ACTIVATING ACTIVITIES
        editAptBtn = findViewById(R.id.editApt);
        delAptBtn = findViewById(R.id.deleteApt);
        bookAptBtn = findViewById(R.id.bookingRequest);

        // METHOD CALLING USING SET ONCLICK LISTENER
        editAptBtn.setOnClickListener(this);
        delAptBtn.setOnClickListener(this);
        bookAptBtn.setOnClickListener(this);

    }

    // Apartment Details Display
    private void myApartment()
    {
        Intent in = getIntent();
        final String aptId =in.getStringExtra("flatId");
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
                                Picasso.with(getApplicationContext()).load(obj.getString("imageUrl")).into(flatImage);

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

    // FLAT BOOKING REQUEST
    public void bookApartment()
    {

        final String email = sharedPrefManager_obj.getKeyEmail();
        Intent in = getIntent();
        final String aptId =in.getStringExtra("flatId");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_BOOK_APT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                params.put("email",email);
                params.put("aptId",aptId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


   // METHOD FOR REDIRECTING TO EDIT FLAT DETAILS ACTIVITY
    public void editApartment()
    {
        Intent in = new Intent(flatDetails.this, updateFlatRecord.class);
        Intent intent = getIntent();
        in.putExtra("aptId",intent.getStringExtra("flatId"));
        startActivity(in);
    }


    //METHOD FOR DELETING FLAT RECORD

    public void deleteApt()
    {
        Intent in = getIntent();
        final String aptId =in.getStringExtra("flatId");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_DELETE_APT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), RegUserSearch.class);
                            startActivity(in);
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
                params.put("aptId",aptId);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

// TAKING CONFIRMATION BEFORE DELETION - ALERT DIALOG BOX
    public void delApartment()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(flatDetails.this);
        builder.setMessage("Click OK to confirm deletion...").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteApt();
            }
        }).setNegativeButton("Cancel",null);

        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bookingRequest:
                bookApartment();
                break;
            case R.id.editApt:
                editApartment();
                break;
            case R.id.deleteApt:
                delApartment();
                break;
        }
    }
}