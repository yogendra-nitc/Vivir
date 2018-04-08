package com.example.yogendra.vivir.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yogendra.vivir.R;

public class uploadRentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_rent_details);
        //Adding back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
