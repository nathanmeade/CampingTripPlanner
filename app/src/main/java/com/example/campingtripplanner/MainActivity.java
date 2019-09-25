package com.example.campingtripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
    }

    public void selectTrip(View view) {
        Intent intent = new Intent(this, DateConversionTestActivity.class);
        startActivity(intent);
    }

/*    public void viewTrip(View view) {
        Intent intent = new Intent(this, ViewTripActivity.class);
        startActivity(intent);
    }*/

}
