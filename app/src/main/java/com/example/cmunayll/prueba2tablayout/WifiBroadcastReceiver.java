package com.example.cmunayll.prueba2tablayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by cmunayll on 20/12/2017.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {

    private boolean isConnect = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
        isConnect = false;
    }
}
