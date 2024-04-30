package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // Set up the click listener for the TextView that acts as a button
        TextView checkNewCityButton = findViewById(R.id.checkNewCity);
        checkNewCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start cityFinder Activity
                Intent intent = new Intent(Activity3.this, cityFinder.class);
                startActivity(intent);
            }
        });

        // Initialize additional UI components if there are any
        initUI();
    }

    private void initUI() {
        String cityName = getIntent().getStringExtra("City");
        if (cityName != null && !cityName.isEmpty()) {
            displayCityWeather(cityName);
        }
    }

    private void displayCityWeather(String cityName) {
        // Method to update UI with weather data
    }
}
