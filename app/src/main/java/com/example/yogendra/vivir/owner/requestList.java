package com.example.yogendra.vivir.owner;
        import android.app.ProgressDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.example.yogendra.vivir.R;
        import com.example.yogendra.vivir.adapter.RequestAdapter;
        import com.example.yogendra.vivir.database.defConstant;
        import com.example.yogendra.vivir.network.RequestHandler;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

public class requestList extends AppCompatActivity {
    RecyclerView requestResult;
    List<RequestItem> requestList = new ArrayList<>();

    RequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_request_list);
        //Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestResult = findViewById(R.id.requestResult);
        requestResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        requestResult.setLayoutManager(linearLayoutManager);
            getRequestList();
    }

// METHOD FOR GETTING ALL APARTMENTS LIST

    private void getRequestList(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                defConstant.URL_allRequest,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("AllFlat");

                            for(int i=0; i<array.length(); i++)
                            {
                                JSONObject o = array.getJSONObject(i);
                                RequestItem item = new RequestItem(
                                        o.getString("aptId"),
                                        o.getString("aptName"),
                                        o.getString("locality"),
                                        o.getString("city"),
                                        o.getString("rentAmt"),
                                        o.getString("aptType"),
                                        o.getString("img1")
                                );
                                requestList.add(item);
                            }

                            adapter = new RequestAdapter(requestList,getApplicationContext());
                            requestResult.setAdapter(adapter);

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
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
