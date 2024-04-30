package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cityFinder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);

        EditText searchCity = findViewById(R.id.searchCity);
        ImageView backButton = findViewById(R.id.backButton);

        // Set up the listener for the EditText to respond to the "Go" button in the keyboard
        searchCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // When the "Go" button is pressed
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String city = v.getText().toString().trim();
                    if (!city.isEmpty()) {
                        // Create an intent to start FetchDataActivity
                        Intent intent = new Intent(cityFinder.this, FetchDataActivity.class);
                        // Pass the city name to FetchDataActivity
                        intent.putExtra("City", city);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });

        // Set up the click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(cityFinder.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
