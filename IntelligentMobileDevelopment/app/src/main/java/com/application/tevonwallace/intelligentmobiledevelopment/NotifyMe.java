package com.application.tevonwallace.intelligentmobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotifyMe extends AppCompatActivity {
    private Button notifyButton;
    private Button cancelButton;
    private Button updateButton;
    private NotificationManager mNotifyManager;

    private NotificationReceiver mReceiver = new NotificationReceiver();

    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.application.tevonwallace.notifyme.ACTION_UPDATE_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_me);

        this.configureProperties();

        this.setNotificationButtonState(true, false, false);
    }

    // MARK: - Actions
    private void configureProperties() {
        this.notifyButton = this.findViewById(R.id.notify);

        this.notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        this.updateButton = this.findViewById(R.id.update);
        this.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });

        this.cancelButton = this.findViewById(R.id.cancel);
        this.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

        this.registerReceiver(this.mReceiver,new IntentFilter(ACTION_UPDATE_NOTIFICATION));
    }

    public void sendNotification() {
        this.createNotificationChannel();

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        this.mNotifyManager.notify(NotifyMe.NOTIFICATION_ID, notifyBuilder.build());

        this.setNotificationButtonState(false, true, true);
    }

    public void createNotificationChannel() {
        this.mNotifyManager = (NotificationManager) getSystemService(NotifyMe.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");

            this.mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, NotifyMe.class);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NotifyMe.NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, NotifyMe.PRIMARY_CHANNEL_ID)
                .setContentTitle("You've been notified!").setContentText("This is your notification text.").setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(notificationPendingIntent).setAutoCancel(true);

        return notifyBuilder;
    }

    public void updateNotification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        Bitmap androidImage = BitmapFactory.decodeResource(getResources(),R.drawable.mascot_1);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        notifyBuilder.addAction(R.drawable.update, "Update Notification", updatePendingIntent);

        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(androidImage).setBigContentTitle("Notification Updated!"));

        this.mNotifyManager.notify(NotifyMe.NOTIFICATION_ID, notifyBuilder.build());

        this.setNotificationButtonState(false, false, true);
    }

    public void cancelNotification() {
        this.mNotifyManager.cancel(NotifyMe.NOTIFICATION_ID);

        this.setNotificationButtonState(true, false, false);
    }

    private void setNotificationButtonState(Boolean isNotifyEnabled, Boolean isUpdateEnabled, Boolean isCancelEnabled) {
        this.notifyButton.setEnabled(isNotifyEnabled);
        this.updateButton.setEnabled(isUpdateEnabled);
        this.cancelButton.setEnabled(isCancelEnabled);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(this.mReceiver);
        super.onDestroy();
    }

    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // Update the notification
        }
    }
}
