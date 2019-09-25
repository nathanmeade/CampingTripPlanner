package com.example.campingtripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewTripActivity extends AppCompatActivity {

    private AppDatabase db;
    private Trip trip;

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
        int tid = intent.getIntExtra("tid", 0);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        trip = db.tripDao().findByTid(tid);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView dateRangeTextView = findViewById(R.id.dateRangeTextView);
        TextView tentTextView = findViewById(R.id.tentTextView);
        TextView bagTextView = findViewById(R.id.sleepingBagTextView);
        TextView eggsTextView = findViewById(R.id.eggsTextView);
        TextView baconTextView = findViewById(R.id.baconTextView);

        nameTextView.setText(trip.name);
        locationTextView.setText(trip.location);
        dateRangeTextView.setText(trip.arrival + " - " + trip.departure);
        tentTextView.setText(trip.tent + "x Tent");
        bagTextView.setText(trip.bag + "x Sleeping Bag");
        eggsTextView.setText(trip.eggs + "x Eggs");
        baconTextView.setText(trip.bacon + "x Bacon");

        ImageView viewLocationButton = findViewById(R.id.viewLocationButton);
        viewLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:" + trip.location);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        String location = trip.location;
        String coordinates[] = location.split(",");
        String lat = "34.0767";
        String lon = "-82.8547";
        final TextView weatherTextView = findViewById(R.id.weatherTextView);
        String appid = BuildConfig.openweatherkey;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
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
                weatherTextView.setText(Double.toString(temp));
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

    }

    public void editTrip(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", trip.name);
        bundle.putString("location", trip.location);
        bundle.putString("arrival", trip.arrival);
        bundle.putString("departure", trip.departure);
        bundle.putString("tent", trip.tent);
        bundle.putString("bag", trip.bag);
        bundle.putString("eggs", trip.eggs);
        bundle.putString("bacon", trip.bacon);
        Intent intent = new Intent(this, CreateTripActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void deleteTrip(View view) {
        db.tripDao().deleteByTid(trip.tid);
        Intent intent = new Intent(this, SelectTripActivity.class);
        startActivity(intent);
    }
}
