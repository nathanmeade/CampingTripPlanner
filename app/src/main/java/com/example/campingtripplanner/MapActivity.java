package com.example.campingtripplanner;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private Button cancelButton;
    private Button saveButton;
    /*private String name;*/
    private String location;
/*    private String arrival;
    private String departure;
    private String tent;
    private String bag;
    private String eggs;
    private String bacon;*/
    private Bundle receivedBundle;
    private Intent receivedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        receivedIntent = getIntent();
        //Use a getExtra Bundle instead of these individual values
        /*name = receivedIntent.getStringExtra("name");
        arrival = receivedIntent.getStringExtra("arrival");
        departure = receivedIntent.getStringExtra("departure");
        tent = receivedIntent.getStringExtra("tent");
        bag = receivedIntent.getStringExtra("bag");
        eggs = receivedIntent.getStringExtra("eggs");
        bacon = receivedIntent.getStringExtra("bacon");*/
        /*Bundle bundle = receivedIntent.getBundleExtra("bundle");*/
        receivedBundle = receivedIntent.getExtras();
        location = "0";
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateTripActivity.class);
                putTripIntentExtras(intent);
                startActivity(intent);
            }
        });
        saveButton = findViewById(R.id.select_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateTripActivity.class);
                receivedBundle.putString("location", location);
                putTripIntentExtras(intent);
                /*putLocationIntentExtra(intent);*/
/*                intent.putExtras(receivedBundle);
                intent.putExtra("location", location);*/
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public void putTripIntentExtras(Intent intent){
/*        intent.putExtra("name", name);
        intent.putExtra("arrival", arrival);
        intent.putExtra("departure", departure);
        intent.putExtra("tent", tent);
        intent.putExtra("bag", bag);
        intent.putExtra("eggs", eggs);
        intent.putExtra("bacon", bacon);*/
        intent.putExtras(receivedBundle);
    }

    public void putLocationIntentExtra(Intent intent){
        intent.putExtra("location", location);
    }
}
