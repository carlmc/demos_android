package com.example.cmunayll.prueba2tablayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cmunayll on 20/12/2017.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {

    //private boolean isConnect = false;

    public static final String NETWORK_AVAILABLE = "com.example.cmunayll.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        
        /*ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i=0;i<info.length;i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        if (!isConnect) {
                            Toast.makeText(context, "Wifi available", Toast.LENGTH_SHORT).show();
                            isConnect = true;
                        }
                    }
                }
            }
        }
        Toast.makeText(context, "Wifi NOT available", Toast.LENGTH_SHORT).show();
        isConnect = false;*/
        //String status = Utils.getConnectivityStatusString(context);
        //Toast.makeText(context, status, Toast.LENGTH_SHORT).show();

        Intent networkIntent = new Intent(NETWORK_AVAILABLE);
        networkIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkIntent);
    }

    private boolean isConnectedToInternet (Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            Log.e(WifiBroadcastReceiver.class.getName(), e.getMessage());
            return false;
        }
    }
}
