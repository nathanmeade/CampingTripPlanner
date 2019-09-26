package com.example.campingtripplanner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

public class CreateTripActivity extends AppCompatActivity {

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
    private Bundle sendingBundle;

    private EditText nameEditText;
    private TextView locationTextView;
    private TextView arrivalTextView;
    private TextView departureTextView;
    private TextView tentTextView;
    private TextView sleepingBagTextView;
    private TextView eggsTextView;
    private TextView baconTextView;
    private ImageView selectMapImageView;
    private ImageView arrivalImageView;
    private ImageView departureImageView;
    private ImageView tentDecrementImageView;
    private ImageView tentIncrementImageView;
    private ImageView sleepingBagDecrementImageView;
    private ImageView sleepingBagIncrementImageView;
    private ImageView eggsDecrementImageView;
    private ImageView eggsIncrementImageView;
    private ImageView baconDecrementImageView;
    private ImageView baconIncrementImageView;

    private DatePickerDialog.OnDateSetListener arrivalDateSetListener;
    private DatePickerDialog.OnDateSetListener departureDateSetListener;

    private int tentCount;
    private int bagCount;
    private int eggsCount;
    private int baconCount;

    private AppDatabase db;
    private int value;
    private Random rand;

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
        initializeViews();
        sendingBundle = new Bundle();
        selectMapImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                getCurrentTripValues();
                createBundle(sendingBundle);
                intent.putExtras(sendingBundle);
                startActivity(intent);
            }
        });
        receivedIntent = getIntent();
        receivedBundle = receivedIntent.getExtras();
        initializeCounts();
        if (receivedBundle != null){
            getBundleValues(receivedBundle);
            setViewsFromBundleValues();
            setCountsFromBundle();
        }
        final Context context = this;
        arrivalImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                    context,
                    AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                    arrivalDateSetListener,
                    year,
                    month,
                    day);
                datePickerDialog.show();
            }
        });
        departureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        context,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                        departureDateSetListener,
                        year,
                        month,
                        day);
                datePickerDialog.show();
            }
        });

        arrivalDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int actualMonth = month+1;
                arrivalTextView.setText("" + actualMonth + "/" + dayOfMonth + "/" + year);
            }
        };

        departureDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int actualMonth = month+1;
                departureTextView.setText("" + actualMonth + "/" + dayOfMonth + "/" + year);
            }
        };
        tentDecrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentCount--;
                tentTextView.setText(""+tentCount);
            }
        });
        tentIncrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentCount++;
                tentTextView.setText(""+tentCount);
            }
        });
        sleepingBagDecrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bagCount--;
                sleepingBagTextView.setText(""+bagCount);
            }
        });
        sleepingBagIncrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bagCount++;
                sleepingBagTextView.setText(""+bagCount);
            }
        });
        eggsDecrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eggsCount--;
                eggsTextView.setText(""+eggsCount);
            }
        });
        eggsIncrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eggsCount++;
                eggsTextView.setText(""+eggsCount);
            }
        });
        baconDecrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baconCount--;
                baconTextView.setText(""+baconCount);
            }
        });
        baconIncrementImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baconCount++;
                baconTextView.setText(""+baconCount);
            }
        });
        rand = new Random();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    public void saveTrip(View view) {
        value = rand.nextInt(500);
        getCurrentTripValues();
        Trip trip = new Trip();
        trip.tid = value;
        trip.name = name;
        trip.location = location;
        trip.arrival = arrival;
        trip.departure = departure;
        trip.tent = tent;
        trip.bag = bag;
        trip.eggs = eggs;
        trip.bacon = bacon;
        db.tripDao().insertAll(trip);
        Intent intent = new Intent(this, ViewTripActivity.class);
        intent.putExtra("tid", value);
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        getBundleValues(savedInstanceState);
        setViewsFromBundleValues();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        getCurrentTripValues();
        createBundle(outState);
        super.onSaveInstanceState(outState);
    }

    public void initializeViews(){
        nameEditText = findViewById(R.id.nameEditText);
        locationTextView = findViewById(R.id.locationValueTextView);
        arrivalTextView = findViewById(R.id.arrivalValueTextView);
        departureTextView = findViewById(R.id.departureValueTextView);
        tentTextView = findViewById(R.id.tentEditText);
        sleepingBagTextView = findViewById(R.id.sleepingBagEditText);
        eggsTextView = findViewById(R.id.eggsEditText);
        baconTextView = findViewById(R.id.baconEditText);
        selectMapImageView = findViewById(R.id.mapButton);
        arrivalImageView = findViewById(R.id.arrivalDateButton);
        departureImageView = findViewById(R.id.departureDateButton);
        tentDecrementImageView = findViewById(R.id.tentDecrementButton);
        tentIncrementImageView = findViewById(R.id.tentIncrementButton);
        sleepingBagDecrementImageView = findViewById(R.id.sleepingBagDecrementButton);
        sleepingBagIncrementImageView = findViewById(R.id.sleepingBagIncrementButton);
        eggsDecrementImageView = findViewById(R.id.eggsDecrementButton);
        eggsIncrementImageView = findViewById(R.id.eggsIncrementButton);
        baconDecrementImageView = findViewById(R.id.baconDecrementButton);
        baconIncrementImageView = findViewById(R.id.baconIncrementButton);
    }

    public void getCurrentTripValues(){
        name = nameEditText.getText().toString();
        location = locationTextView.getText().toString();
        arrival = arrivalTextView.getText().toString();
        departure = departureTextView.getText().toString();
        tent = tentTextView.getText().toString();
        bag = sleepingBagTextView.getText().toString();
        eggs = eggsTextView.getText().toString();
        bacon = baconTextView.getText().toString();
    }

    public void createBundle(Bundle bundle){
        bundle.putString(getString(R.string.name_lowercase), name);
        bundle.putString(getString(R.string.location_lowercase), location);
        bundle.putString(getString(R.string.arrival_lowercase), arrival);
        bundle.putString(getString(R.string.departure_lowercase), departure);
        bundle.putString(getString(R.string.tent_lowercase), tent);
        bundle.putString(getString(R.string.bag_lowercase), bag);
        bundle.putString(getString(R.string.eggs_lowercase), eggs);
        bundle.putString(getString(R.string.bacon_lowercase), bacon);
    }

    public void getBundleValues(Bundle bundle){
        name = bundle.getString(getString(R.string.name_lowercase));
        location = bundle.getString(getString(R.string.location_lowercase));
        arrival = bundle.getString(getString(R.string.arrival_lowercase));
        departure = bundle.getString(getString(R.string.departure_lowercase));
        tent = bundle.getString(getString(R.string.tent_lowercase));
        bag = bundle.getString(getString(R.string.bag_lowercase));
        eggs = bundle.getString(getString(R.string.eggs_lowercase));
        bacon = bundle.getString(getString(R.string.bacon_lowercase));
    }

    public void setViewsFromBundleValues(){
        nameEditText.setText(name);
        locationTextView.setText(location);
        arrivalTextView.setText(arrival);
        departureTextView.setText(departure);
        tentTextView.setText(tent);
        sleepingBagTextView.setText(bag);
        eggsTextView.setText(eggs);
        baconTextView.setText(bacon);
    }

    public void initializeCounts(){
        tentCount = 0;
        bagCount = 0;
        eggsCount = 0;
        baconCount = 0;
    }

    public void setCountsFromBundle(){
        tentCount = Integer.parseInt(tent);
        bagCount = Integer.parseInt(bag);
        eggsCount = Integer.parseInt(eggs);
        baconCount = Integer.parseInt(bacon);
    }
}
