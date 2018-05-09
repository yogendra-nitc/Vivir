package com.example.yogendra.vivir.tenant;

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

public class leaveApt extends AppCompatActivity implements View.OnClickListener{
    private Button leaveFlat;
    private ProgressDialog progressDialog;
    private EditText aptId, userEmail;
    SharedPrefManager sharedPrefManager_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_apt);
        // adding back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());

        aptId = findViewById(R.id.apt_id);
        userEmail = findViewById(R.id.user_email);
        leaveFlat = findViewById(R.id.leave_apt);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing...");

        leaveFlat.setOnClickListener(this);
    }

    // Get Request Details
    private void leaveApt()
    {
        final String aptid = aptId.getText().toString().trim();
        final String email = userEmail.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_LEAVE_FLAT,
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
                                finish();
                                startActivity(getIntent());

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
                params.put("aptId",aptid);
                params.put("userEmail",email);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == leaveFlat){
            String userId = sharedPrefManager_obj.getKeyEmail();
            String useremail =  userEmail.getText().toString().trim();
            if(useremail.equals(userId))
                leaveApt();
            else
            {
                Toast.makeText(getApplicationContext(),"Entered email is incorrect",Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());
            }
        }
    }
}
