package com.example.yogendra.vivir.owner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yogendra.vivir.R;

import java.io.IOException;

public class AddImage extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imgButton1, imgButton2, imgButton3;
    private TextView txtButton1, txtButton2, txtButton3;
    private Button uploadBtn;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flat_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgButton1 = findViewById(R.id.image1);
        imgButton2 = findViewById(R.id.image2);
        imgButton3 = findViewById(R.id.image3);

        txtButton1 = findViewById(R.id.imgName1);
        txtButton2 = findViewById(R.id.imgName2);
        txtButton2 = findViewById(R.id.imgName3);

        uploadBtn = findViewById(R.id.uploadButton);

        imgButton1.setOnClickListener(this);
        imgButton2.setOnClickListener(this);
        imgButton3.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.image1:
                chooseImage();
                break;
            case R.id.image2:
                chooseImage();
                break;
            case R.id.image3:
                chooseImage();
                break;
        }
    }

    public void chooseImage()
    {
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(in.ACTION_GET_CONTENT);
        startActivityForResult(in,IMG_REQUEST);
    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode, Intent data )
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
