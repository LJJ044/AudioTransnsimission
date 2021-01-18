package com.example.audiotransmission;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MediaTransService extends Service {
    private Notification notification;
    private NotificationManager nm;
    private Context mContext;
    private static final String NITIFICATION_CHANEL_ID = "TransService";
    public static final int NOTIFICATION_ID = 100;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) this.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(NITIFICATION_CHANEL_ID, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
        channel.setSound(null, null);
        nm.createNotificationChannel(channel);
        notification = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("StreamTransmitting")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher))
                .setChannelId(NITIFICATION_CHANEL_ID)
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(100,notification);
        startService(new Intent(this,NoticeCancelService.class));
        Log.i("前台服务","开启中。。。");
        return START_STICKY;
    }
    public void start(Context ctx, Intent intent){
        ctx.startService(intent);
    }
}
