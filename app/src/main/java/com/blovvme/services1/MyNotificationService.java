package com.blovvme.services1;

//import android.app.PendingIntent;
//import android.app.Notification;
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.support.annotation.IntDef;
//import android.support.annotation.Nullable;
//import android.support.v7.app.NotificationCompat;
//
///**
// * Created by Blovvme on 8/16/17.
// */
//
//public class MyNotificationService  extends Service {
//
//    public MyNotificationService() {
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        switch (intent.getAction()) {
////            case "startForeground":
////                Intent stopIntent = new Intent(this, ForegroundService.class);
////                stopIntent.setAction("stopForeground");
////                PendingIntent pendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);
////                Notification notification = new NotificationCompat.Builder(this).setContentTitle("Music Player")
////                        .setContentText("Music playing in foreground").setSmallIcon(R.drawable.mp).setOngoing(true)
////                        .addAction(R.drawable.ic_stop, "STOP MUSIC", pendingIntent).build();
////                startForeground(1, notification);
////                break;
////            case "stopForeground":
////                stopForeground(true);
////                stopService(new Intent(this, MyNormalService.class));
////                break;
////        }
////        if (intent.getAction().equals("startAction")) {
//
//        Intent intent1 = new Intent(this, MyNotificationService.class);
//        intent1.setAction("stop");
//        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent1, 0);
//        intent1.setAction("stopMusic");
//        Notification mBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.stop)
//                .setContentTitle("My notification")
//                .addAction(R.drawable.stop, "STOP MUSIC", pendingIntent).build();
//
//        startForeground(1, mBuilder);
//
////    } else {
////        stopForeground(true);
////        stopService(new Intent(this, MyService.class));
////
////    }
//        return START_STICKY;
//}
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        return null;
//        // throw new UnsupportedOperationException("Not yet implemented");
//    }
//}
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class MyNotificationService extends Service {
    private static final String TAG = "ForegroundService";

    public MyNotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case "startAction":
                Intent stopIntent = new Intent(this, MyNotificationService.class);
                stopIntent.setAction("stopForeground");
                PendingIntent pendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);
                Notification mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.stop)
                .setContentTitle("My notification")
                .addAction(R.drawable.stop, "STOP MUSIC", pendingIntent).build();
                startForeground(1, mBuilder);
                break;
            case "stopAction":
                stopForeground(true);
                stopService(new Intent(this, MyNotificationService.class));
                break;
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
        // throw new UnsupportedOperationException("Not yet implemented");
    }
}