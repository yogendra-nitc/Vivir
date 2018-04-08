package com.example.yogendra.vivir.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yogendra.vivir.R;

public class requestDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        // adding back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
