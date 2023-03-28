package com.camp.campingtripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewTripActivity extends AppCompatActivity {

    private AppDatabase db;
    private Trip trip;

    TextView nameTextView;
    TextView locationTextView;
    TextView dateRangeTextView;
    TextView tentTextView;
    TextView bagTextView;
    TextView eggsTextView;
    TextView baconTextView;

    String name;
    String location;
    String dateRange;
    String tent;
    String bag;
    String eggs;
    String bacon;

    ImageView viewLocationButton;

    TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectTripActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        int tid = intent.getIntExtra(getString(R.string.tid), 0);
        nameTextView = findViewById(R.id.nameTextView);
        locationTextView = findViewById(R.id.locationTextView);
        dateRangeTextView = findViewById(R.id.dateRangeTextView);
        tentTextView = findViewById(R.id.tentTextView);
        bagTextView = findViewById(R.id.sleepingBagTextView);
        eggsTextView = findViewById(R.id.eggsTextView);
        baconTextView = findViewById(R.id.baconTextView);
        viewLocationButton = findViewById(R.id.viewLocationButton);
        db = AppDatabase.getInstance(getApplicationContext());
        ViewTripViewModelFactory viewTripViewModelFactory = new ViewTripViewModelFactory(db, tid);
        final ViewTripViewModel viewTripViewModel = new ViewModelProvider(this, viewTripViewModelFactory).get(ViewTripViewModel.class);
        viewTripViewModel.getTrip().observe(this, new Observer<Trip>() {
            @Override
            public void onChanged(Trip trip) {
                viewTripViewModel.getTrip().removeObserver(this);
                setTripVariable(trip);
                setTextViews(trip);
                setLocationButton(trip);
                setWeatherView(trip);
            }
        });

    }

    public void setTripVariable(Trip mTrip){
        trip = mTrip;
    }

    public void setLocationButton(final Trip mTrip){
        viewLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:" + mTrip.location + "?q=" + mTrip.location + "(Campsite)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage(getString(R.string.google_maps_package));
                startActivity(mapIntent);
            }
        });
    }

    public void setWeatherView(Trip trip){
        String location = trip.location;
        String coordinates[] = location.split(",");
        weatherTextView = findViewById(R.id.weatherTextView);
        String appid = BuildConfig.OPEN_WEATHER_API_KEY;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.open_weather_map_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Weather> call = jsonPlaceHolderApi.getWeather(coordinates[0], coordinates[1], appid);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (!response.isSuccessful()){
                    return;
                }
                Weather weather = response.body();
                Main main = weather.getMain();
                double temp = main.getTemp();
                temp = (((temp - 273) * 9/5) + 32);
                String formattedTemp = String.format("%.0f", temp);
                weatherTextView.setText(getString(R.string.currently) + formattedTemp + getString(R.string.degrees_fahrenheit));
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }

    public void setTextViews(Trip trip){
        nameTextView.setText(trip.name);
        locationTextView.setText(trip.location);
        dateRangeTextView.setText(trip.arrival + " - " + trip.departure);
        tentTextView.setText(trip.tent + getString(R.string.tent_quantity));
        bagTextView.setText(trip.bag + getString(R.string.bag_quantity));
        eggsTextView.setText(trip.eggs + getString(R.string.eggs_quantity));
        baconTextView.setText(trip.bacon + getString(R.string.bacon_quantity));
    }

    private void setShareableString(){
        name = nameTextView.getText().toString();
        location = locationTextView.getText().toString();
        dateRange = dateRangeTextView.getText().toString();
        tent = tentTextView.getText().toString();
        bag = bagTextView.getText().toString();
        eggs = eggsTextView.getText().toString();
        bacon = baconTextView.getText().toString();
    }

    private String getShareableString(){
        setShareableString();
        String shareableString = "Camping trip:\n\n" + name + "\nhttp://maps.google.com/?q=" + location + "\n" + dateRange + "\n" + tent + "\n" + bag + "\n" + eggs + "\n" + bacon;
        return shareableString;
    }

    public void editTrip(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.name_lowercase), trip.name);
        bundle.putString(getString(R.string.location_lowercase), trip.location);
        bundle.putString(getString(R.string.arrival_lowercase), trip.arrival);
        bundle.putString(getString(R.string.departure_lowercase), trip.departure);
        bundle.putString(getString(R.string.tent_lowercase), trip.tent);
        bundle.putString(getString(R.string.bag_lowercase), trip.bag);
        bundle.putString(getString(R.string.eggs_lowercase), trip.eggs);
        bundle.putString(getString(R.string.bacon_lowercase), trip.bacon);
        Intent intent = new Intent(this, CreateTripActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void deleteTrip(View view) {
        final Context context = this;
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.tripDao().deleteByTid(trip.tid);
                Intent intent = new Intent(context, SelectTripActivity.class);
                startActivity(intent);
            }
        });

    }

    public void shareTrip(View view) {
        String tripDetails;
        /*tripDetails =    */
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Camping Trip:");
        intent.putExtra(Intent.EXTRA_TEXT, getShareableString());
        startActivity(intent);
    }
}
