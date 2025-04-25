package com.example.cityexplorer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView cityImage;
    TextView cityName;
    Button openMapButton;

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        cityImage = findViewById(R.id.cityImage);
        cityName = findViewById(R.id.cityName);
        openMapButton = findViewById(R.id.openMapButton);

        // Get data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("cityName");
        int image = intent.getIntExtra("cityImage", 0);

        // Set data
        cityName.setText(name);
        cityImage.setImageResource(image);

        // Handle button click to open Google Maps
        openMapButton.setOnClickListener(v -> {
            String geoUri = "geo:0,0?q=" + Uri.encode(name);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
            mapIntent.setPackage("com.google.android.apps.maps");

            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        });
    }
}
