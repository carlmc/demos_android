package com.example.cmunayll.prueba2tablayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by cmunayll on 20/12/2017.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable()) {
            Toast.makeText(context, "Wifi Disconnected", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(context, "Wifi Connected", Toast.LENGTH_SHORT).show();
        }

        /*ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo()!=null){
            Toast.makeText(context, "Connected to Internet", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(context, "Wifi Disconnected", Toast.LENGTH_SHORT).show();*/

    }
}
