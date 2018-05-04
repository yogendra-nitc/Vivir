package com.example.yogendra.vivir.user;

        import android.content.Intent;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import com.example.yogendra.vivir.R;

        import android.app.ProgressDialog;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.example.yogendra.vivir.adapter.NotificationAdapter;
        import com.example.yogendra.vivir.database.SharedPrefManager;
        import com.example.yogendra.vivir.database.defConstant;
        import com.example.yogendra.vivir.network.RequestHandler;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class notification extends AppCompatActivity {
    SharedPrefManager sharedPrefManager_obj;
    private String ntype="few";
    RecyclerView notificationResult;
    List<NotificationItem> notificationList = new ArrayList<>();
    private FloatingActionButton allNotification;

    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManager_obj = SharedPrefManager.getInstance(this);
        setContentView(R.layout.activity_notification);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        notificationResult = findViewById(R.id.notificationResult);
        notificationResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        notificationResult.setLayoutManager(linearLayoutManager);
        getNotification();

    }

// METHOD FOR GETTING ALL APARTMENTS LIST

    private void getNotification(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data...");
        final String userId = sharedPrefManager_obj.getKeyEmail();
        final String limit = ntype;
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                defConstant.URL_getNotifications,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("AllNotification");

                            for(int i=0; i<array.length(); i++)
                            {
                                JSONObject o = array.getJSONObject(i);

                                NotificationItem item = new NotificationItem(
                                        o.getString("nid"),
                                        o.getString("content"),
                                        o.getString("ndate"),
                                        //R.drawable.ic_notifications_black_24dp
                                        R.drawable.notification
                                );
                                notificationList.add(item);
                            }

                            adapter = new NotificationAdapter(notificationList,getApplicationContext());
                            notificationResult.setAdapter(adapter);

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
                params.put("userEmail",userId);
                params.put("limit",limit);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void setLimit(View v)
    {
        ntype="all";
        getNotification();
    }
}
