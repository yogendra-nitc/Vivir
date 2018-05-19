package com.example.yogendra.vivir.user;

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


public class writeComplain extends AppCompatActivity implements View.OnClickListener {

    private EditText receiverEmail, content;
    private Button submitContent;
    private ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        receiverEmail = findViewById(R.id.receiver_email);
        content = findViewById(R.id.content);

        submitContent = (Button)findViewById(R.id.submit_content);

        progressDialog = new ProgressDialog(this);

        submitContent.setOnClickListener(this);

    }

    public void writeComplain(){
        final String receiver = receiverEmail.getText().toString().trim();
        final String Content = content.getText().toString().trim();

        sharedPrefManager_obj = SharedPrefManager.getInstance(getApplicationContext());
        final String senderEmail = sharedPrefManager_obj.getKeyEmail();

        progressDialog.setMessage("processing...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_Write_Complain,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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
                params.put("sid",senderEmail);
                params.put("rid",receiver);
                params.put("content",Content);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    @Override
    public void onClick(View v) {
        if(v == submitContent)
        {
            writeComplain();
        }
    }
}



