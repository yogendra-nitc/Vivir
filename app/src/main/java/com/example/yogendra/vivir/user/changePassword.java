package com.example.yogendra.vivir.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class changePassword extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextOld, editTextNew,editTextCnfm;
    private Button changePassBtn;
    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());

        editTextOld = findViewById(R.id.oldPassword);
        editTextNew = (EditText)findViewById(R.id.password);
        editTextCnfm = (EditText)findViewById(R.id.confirmPassword);
        changePassBtn = (Button)findViewById(R.id.change_password_btn);

        progressDialog = new ProgressDialog(this);

        changePassBtn.setOnClickListener(this);

    }

    public void changePassword(){
        final String oldPassword = editTextOld.getText().toString().trim();
        final String newPassword = editTextNew.getText().toString().trim();
        final String email = sharedPrefManager_obj.getKeyEmail();

        progressDialog.setMessage("Processing...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_CHANGE_PASSWORD,
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
                        finish();
                        startActivity(getIntent());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("oldPwd",oldPassword);
                params.put("newPwd",newPassword);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view){
        if(view == changePassBtn){
          String  newPwd = editTextNew.getText().toString().trim();
          String confirmPwd = editTextCnfm.getText().toString().trim();
            if(newPwd.equals(confirmPwd) && newPwd.length()>=5)
            {
                changePassword();
            }
            else
            {
                if(newPwd.equals(confirmPwd) && newPwd.length()<5)
                    Toast.makeText(getApplicationContext(),"Password is too short", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Password is not matching", Toast.LENGTH_LONG).show();
            }
        }
    }
}
