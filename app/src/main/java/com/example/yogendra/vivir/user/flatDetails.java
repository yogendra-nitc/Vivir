package com.example.yogendra.vivir.user;

        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;
        import com.example.yogendra.vivir.R;
        import com.example.yogendra.vivir.adapter.ViewPagerAdapter;
        import com.example.yogendra.vivir.database.defConstant;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.List;


public class flatDetails extends AppCompatActivity {

    ViewPager viewPager;
    RequestQueue rq;
    List<SliderUtils>sliderImg;
    ViewPagerAdapter viewPagerAdapter;

    String request_url = defConstant.URL_FlatDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_details);

        rq = Volley.newRequestQueue(this);
        sliderImg = new ArrayList<>();

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        sendRequest();

    }

    public void sendRequest()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JsonArrayRequest.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i< response.length();i++)
                {
                    SliderUtils sliderUtils = new SliderUtils();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        sliderUtils.setSliderImageUrl(jsonObject.getString("imageUrl"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    sliderImg.add(sliderUtils);

                }

                viewPagerAdapter = new ViewPagerAdapter(sliderImg,flatDetails.this);
                viewPager.setAdapter(viewPagerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jsonArrayRequest);
    }
}
