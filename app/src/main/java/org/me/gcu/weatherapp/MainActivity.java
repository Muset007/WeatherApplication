package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MenuItemAdapter.OnItemClickListener {

    private long backPressedTime;
    private MenuItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    final ArrayList<AppMenuItem> menuItems = new ArrayList<>();
    public static final String EXTRA_NUMBER = "com.example.new_activity.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Retrieve the city name passed from cityFinder
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("City");
        if (cityName != null) {
            TextView cityNameTextView = findViewById(R.id.cityName);
            cityNameTextView.setText(cityName);
            // Optionally, update other UI elements or fetch weather data here
        }

        TextView checkCity = findViewById(R.id.checkCity);
        checkCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cityFinder.class);
                startActivity(intent);
            }
        });

        SharedPreferences sPrefs = getSharedPreferences("sPrefs", MODE_PRIVATE);
        boolean firstStart = sPrefs.getBoolean("firstStart", true);
        if (firstStart) {
            showStartDialog();
        }

        createMenuList();
        buildRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra(EXTRA_NUMBER, position);
        startActivity(intent);
    }

    public void createMenuList() {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        menuItems.add(new AppMenuItem(R.drawable.ic_location,"Glasgow", currentDate));
        menuItems.add(new AppMenuItem(R.drawable.ic_location, "London", currentDate));
        menuItems.add(new AppMenuItem(R.drawable.ic_location, "New York", currentDate));
        menuItems.add(new AppMenuItem(R.drawable.ic_location, "Oman", currentDate));
        menuItems.add(new AppMenuItem(R.drawable.ic_location, "Mauritius", currentDate));
        menuItems.add(new AppMenuItem(R.drawable.ic_location, "Bangladesh", currentDate));
        mAdapter = new MenuItemAdapter(menuItems, this);
    }

    public void buildRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void showStartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome to my app!")
                .setMessage("Select a City to see a 3 day forecast")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences sPrefs = getSharedPreferences("sPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}
