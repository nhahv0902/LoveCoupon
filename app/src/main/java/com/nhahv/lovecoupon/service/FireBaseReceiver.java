package com.nhahv.lovecoupon.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class FireBaseReceiver extends WakefulBroadcastReceiver {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
       /* if (intent.getExtras() != null) {
            for (String key : intent.getExtras().keySet()) {
                String value = intent.getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        String title = intent.getExtras().getString("title");
        String body = intent.getExtras().getString("body");
        InfoCustomer infoCustomer = new Gson()
            .fromJson(PreferenceUtils.getInstance(context).getStringPreference(Applications
                    .BUNDLE_INFORMATION_CUSTOMER),
                InfoCustomer.class);
        if (infoCustomer != null && infoCustomer.getId() != null)
            DataRemote.newsOfCustomer(infoCustomer.getId());
        sendNotification(context, body, title);*/
    }

    private void sendNotification(Context context, String messageBody, String title) {
       /* Intent intent = new Intent(context, FirstActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_NOTIFICATION, Applications.NOTIFICATION);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
            | Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
            PendingIntent.getActivity(context, 0 *//* Request code *//*, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        int color = ContextCompat.getColor(context, R.color.colorNotificatioin);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setColor(color)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        long id = System.currentTimeMillis();
        notificationManager.notify((int) id, notificationBuilder.build());*/
    }
}
