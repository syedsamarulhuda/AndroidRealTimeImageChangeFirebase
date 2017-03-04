package com.example.samar.imagechangefirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ImageLoad extends AppCompatActivity {
    ImageView ivImage;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btnUpload,btnImage;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        btnUpload = (Button) findViewById(R.id.btnUpload);


        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Image");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(String.class);
                Picasso.with(ImageLoad.this)
                        .load(value)
                        .into(ivImage);
                Log.d("##VALUE", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("##FAIL", "Failed to read value.", error.toException());
            }
        });
    }
}
