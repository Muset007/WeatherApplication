package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        TextView footerTextView = findViewById(R.id.splash_footer);
        footerTextView.setTextColor(Color.BLACK); // Example of setting text color programmatically

        // Handler to start MainActivity and close this Splash-Screen after some seconds.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close this activity
            }
        }, 5000); // wait for 5 seconds
    }
}
