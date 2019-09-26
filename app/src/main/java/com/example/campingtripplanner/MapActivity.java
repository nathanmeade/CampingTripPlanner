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
    private String location;
    private Bundle receivedBundle;
    private Intent receivedIntent;

    private GoogleMap mMap;
    private LatLng latLngToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();
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
                receivedBundle.putString(getString(R.string.location_lowercase), location);
                putTripIntentExtras(intent);
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
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);
            }
        });
        LatLng clemson = new LatLng(34.686440, -82.823330);
        mMap.addMarker(new MarkerOptions().position(clemson).title(getString(R.string.original_marker_title)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(clemson));
    }

    public void putTripIntentExtras(Intent intent){
        intent.putExtras(receivedBundle);
    }
}
