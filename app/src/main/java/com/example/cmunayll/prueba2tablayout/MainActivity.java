package com.example.cmunayll.prueba2tablayout;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.cmunayll.prueba2tablayout.adapters.AdapterFragment;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import static com.example.cmunayll.prueba2tablayout.WifiBroadcastReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    private Tracker mTracker;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String name = "Analytics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        TabLayout.Tab uno = tabLayout.newTab();
        uno.setText("Cuentas");
        uno.setIcon(R.drawable.account);
        tabLayout.addTab(uno);

        TabLayout.Tab dos = tabLayout.newTab();
        dos.setText("Tarjetas");
        dos.setIcon(R.drawable.card);
        tabLayout.addTab(dos);

        TabLayout.Tab tres = tabLayout.newTab();
        tres.setText("Créditos");
        tres.setIcon(R.drawable.credit);
        tabLayout.addTab(tres);

        AdapterFragment adapterFragment = new AdapterFragment(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapterFragment);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Tracker t = ((AnalyticsApplication) getApplication()).getDefaultTracker();
                Log.i(TAG, "Setting screen name: "+name);
                mTracker.send(new HitBuilders.EventBuilder().setCategory("Action").setAction("Share").build());
                //mTracker.setScreenName(name);
                //mTracker.send(new HitBuilders.ScreenViewBuilder().build());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        IntentFilter intentFilter = new IntentFilter(WifiBroadcastReceiver.NETWORK_AVAILABLE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "conectado" : "desconectado";

                Snackbar.make(findViewById(R.id.activity_main), "Estado WIFI: "+networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Screen name: "+name);
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

}
