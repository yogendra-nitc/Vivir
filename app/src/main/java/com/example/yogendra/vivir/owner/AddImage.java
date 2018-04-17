package com.example.yogendra.vivir.owner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yogendra.vivir.R;

public class AddImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
