package com.example.yogendra.vivir.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.yogendra.vivir.R;


public class splashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=2500;
    private ProgressBar progressBar;
    private int progressStatus=0;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in=new Intent(splashScreen.this,MainActivity.class);
                startActivity(in);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
