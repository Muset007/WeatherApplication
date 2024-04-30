package org.me.gcu.weatherapp;
/*
Mussie Teferi s2038680
 */

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BaseActivity extends AppCompatActivity {

    private final String glasgowURLSource =    "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579";
    private final String londonURLSource =     "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643743";
    private final String newYorkURLSource =    "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/5128581";
    private final String omanURLSource =       "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/287286";
    private final String mauritiusURLSource =  "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/934154";
    private final String bangladeshURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1185241";

    private RSSItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.rv_Description);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        int ID = getIntent().getIntExtra(MainActivity.EXTRA_NUMBER, -1);

        switch (ID) {
            case 0: //glasgow
                new RSSDownloader(BaseActivity.this, glasgowURLSource, recyclerView).execute();
                break;
            case 1: //london
                new RSSDownloader(BaseActivity.this, londonURLSource, recyclerView).execute();
                break;
            case 2: //new york
                new RSSDownloader(BaseActivity.this, newYorkURLSource, recyclerView).execute();
                break;
            case 3: //oman
                new RSSDownloader(BaseActivity.this, omanURLSource, recyclerView).execute();
                break;
            case 4: //mauritius
                new RSSDownloader(BaseActivity.this, mauritiusURLSource, recyclerView).execute();
                break;
            case 5: //bangladesh
                new RSSDownloader(BaseActivity.this, bangladeshURLSource, recyclerView).execute();
                break;
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Downloader(Activity2.this, manchesterURLSource, recyclerView).execute();
            }
        });
    }

}
