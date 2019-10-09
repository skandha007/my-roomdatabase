package com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class alertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            Notificationhelper notificationhelper= new Notificationhelper(context);
        NotificationCompat.Builder nb= notificationhelper.getChannelNotification();
            notificationhelper.getManager().notify(1,nb.build());


    }
}
