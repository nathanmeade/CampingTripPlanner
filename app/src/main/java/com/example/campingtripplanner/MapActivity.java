package com.example.campingtripplanner;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    private GoogleMap mMap;
    private LatLng latLngToSave;

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
       /* location = "0";*/

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
                String latitude = String.format("%.4f", latLngToSave.latitude);
                String longitude = String.format("%.4f", latLngToSave.longitude);
                location = latitude + "," + longitude;
                receivedBundle.putString("location", location);
                putTripIntentExtras(intent);
                /*putLocationIntentExtra(intent);*/
/*                intent.putExtras(receivedBundle);
                intent.putExtra("location", location);*/
                startActivity(intent);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                latLngToSave = latLng;

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });
        // Add a marker in Sydney and move the camera
        LatLng clemson = new LatLng(34.686440, -82.823330);
        mMap.addMarker(new MarkerOptions().position(clemson).title("Marker in Clemson"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(clemson));
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

    public void toast(View view) {
        Toast.makeText(this, "Toast pressed", Toast.LENGTH_SHORT);
        Log.d("nathanTest", "save pressed and: " + latLngToSave.latitude + " : " + latLngToSave.longitude);
        Intent intent = new Intent(this, CreateTripActivity.class);
        intent.putExtra("location", latLngToSave.latitude + "," + latLngToSave.longitude);
        startActivity(intent);
    }
}
