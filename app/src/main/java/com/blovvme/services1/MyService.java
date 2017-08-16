package com.blovvme.services1;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {
    public MyService() {
    }

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case "StartAction":
                mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
                Intent stopIntent = new Intent(this, MyService.class);
               stopIntent.setAction("stopAction");
              PendingIntent pendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);
                Notification mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.stop)
                        .setContentTitle("My notification")
                        .addAction(R.drawable.stop, "STOP MUSIC", pendingIntent).build();
                startForeground(1, mBuilder);
                break;
            case "stopAction":
                stopForeground(true);
                stopService(new Intent(this, MyService.class));
                break;
        }

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }
}
