package com.example.cmunayll.prueba2tablayout;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cmunayll on 21/12/2017.
 */

public class MiIntentService extends IntentService {

    public MiIntentService() {
        super(MiIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if ("com.example.cmunayll.prueba2tablayout.action.RUN_SERVICE".equals(action)) {
                actionRun();
            }
        }
    }

    private void actionRun() {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.stat_sys_download_done).setContentTitle("Servicio en 2do Plano").setContentText("Process ...");

            for (int i=1; i<=10; i++){
                Log.d(MiIntentService.class.getName(), i + "");

                builder.setProgress(10, i, false);
                startForeground(1, builder.build());

                Intent localIntent = new Intent("com.example.cmunayll.prueba2tablayout.action.RUN_INTENT_SERVICE").putExtra("com.example.cmunayll.prueba2tablayout.extra.PROGRESS",i);

                LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

                Thread.sleep(1000);
            }

            stopForeground(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service destroy", Toast.LENGTH_SHORT).show();
        Intent localIntent = new Intent("com.example.cmunayll.prueba2tablayout.action.PROGRESS_EXIT");
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
