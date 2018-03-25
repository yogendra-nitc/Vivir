package com.example.yogendra.vivir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton signupBtnMove;
        signupBtnMove = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        signupBtnMove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(signup.this , home.class);
                startActivity(in);
            }
        });
    }
}
