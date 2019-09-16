package com.example.campingtripplanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateTripActivity extends AppCompatActivity {

    private ImageView selectMapImageView;
    private Intent receivedIntent;
    private Bundle receivedBundle;
    private String name;
    private String location;
    private String arrival;
    private String departure;
    private String tent;
    private String bag;
    private String eggs;
    private String bacon;
    private TextView locationTextView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        locationTextView = findViewById(R.id.locationValueTextView);
        location = "1";
        locationTextView.setText(location);
        //Create Bundle
        final Bundle bundle = new Bundle();
        name = "Outer Banks";
        bundle.putString("name", name);
        bundle.putString("location", location);
        bundle.putString("arrival", "arrival");
        bundle.putString("departure", "departure");
        bundle.putString("tent", "tent");
        bundle.putString("bag", "bag");
        bundle.putString("eggs", "eggs");
        bundle.putString("bacon", "bacon");
        selectMapImageView = findViewById(R.id.mapButton);
        selectMapImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();
        if (receivedBundle != null){
            name = receivedBundle.getString("name");
            location = receivedBundle.getString("location");
            arrival = receivedBundle.getString("arrival");
            departure = receivedBundle.getString("departure");
            tent = receivedBundle.getString("tent");
            bag = receivedBundle.getString("bag");
            eggs = receivedBundle.getString("eggs");
            bacon = receivedBundle.getString("bacon");
            locationTextView.setText(location);
        }

    }


}
