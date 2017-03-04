package com.example.samar.imagechangefirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivImage;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btnUpload,btnImage;
    String imgArray[]={"https://res.cloudinary.com/dvi1cncba/image/upload/w_450/v1443165254/qpsc8ymetvwov1tybhzw.jpg",
                       "https://res.cloudinary.com/dvi1cncba/image/upload/w_450/v1475677134/fjg3nnc3inyvubhcf5bt.jpg",
            "https://res.cloudinary.com/dvi1cncba/image/upload/w_450/v1484313524/vunvjnsu29oxedqy14hz.jpg",
            "https://res.cloudinary.com/dvi1cncba/image/upload/w_450/v1484125126/hfbtmcxm3slbmzv79quc.jpg"
    };

     int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnImage=(Button)findViewById(R.id.btnRefresh);
        btnUpload.setOnClickListener(this);
        btnImage.setOnClickListener(this);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        //
        myRef = database.getReference("Image");




    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnUpload) {
            if(count<imgArray.length) {
                String imgLink = imgArray[count];
                count++;
                myRef.setValue(imgLink);
            }
        }
        if(view.getId() == R.id.btnRefresh)
        {
            Intent i = new Intent(MainActivity.this, ImageLoad.class);
            startActivity(i);
        }
    }
}
