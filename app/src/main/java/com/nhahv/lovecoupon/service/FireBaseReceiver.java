package com.nhahv.lovecoupon.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.nhahv.lovecoupon.R;
import com.nhahv.lovecoupon.ui.firstscreen.FirstScreenActivity;

public class FireBaseReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null) return;
        String title = intent.getExtras().getString("title");
        String body = intent.getExtras().getString("body");
        sendNotification(context, body, title);
    }

    private void sendNotification(Context context, String message, String title) {
        Intent intent = new Intent(context, FirstScreenActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(FirstScreenActivity.class).addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent =
            stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, R.color.color_pink))
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }
}
