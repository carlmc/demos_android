package com.example.cmunayll.prueba2tablayout;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

/**
 * Created by cmunayll on 03/01/2018.
 */

public class App extends Application {

    private static final String WIFI_STATE = "android.net.wifi.WIFI_STATE_CHANGED";

    @Override
    public void onCreate() {
        super.onCreate();
        registerForNetworkChangeEvents(this);
    }

    public static void registerForNetworkChangeEvents(final Context context) {
        WifiBroadcastReceiver wifiBroadcastReceiver = new WifiBroadcastReceiver();
        context.registerReceiver(wifiBroadcastReceiver, new IntentFilter(CONNECTIVITY_ACTION));
        context.registerReceiver(wifiBroadcastReceiver, new IntentFilter(WIFI_STATE));
    }
}
