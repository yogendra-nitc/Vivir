package com.example.yogendra.vivir.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.main.MainActivity;
import com.example.yogendra.vivir.network.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class setProfile extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName,editTextCity,editTextState,editTextContact;
    private Button submitButton;
    SharedPrefManager sharedPrefManager_obj;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        editTextName = (EditText)findViewById(R.id.name);
        editTextCity = (EditText)findViewById(R.id.city);
        editTextState = (EditText)findViewById(R.id.state);
        editTextContact = (EditText)findViewById(R.id.contact);
        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());

        submitButton = (Button)findViewById(R.id.changeDetails);
        progressDialog = new ProgressDialog(this);

        submitButton.setOnClickListener(this);
    }

    public void registerUser(){
        final String Name = editTextName.getText().toString().trim();
        final String City = editTextCity.getText().toString().trim();
        final String State = editTextState.getText().toString().trim();
        final String Contact = editTextContact.getText().toString().trim();

        progressDialog.setMessage("Updating details...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_SET_PROFILE,
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
                params.put("email",sharedPrefManager_obj.getKeyEmail());
                params.put("name",Name);
                params.put("city",City);
                params.put("state",State);
                params.put("contact",Contact);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == submitButton){
            registerUser();
        }
    }

}
