package com.seekho.live.firebase;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.seekho.live.R;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }
    public void showNotification(String title,String message)
    {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"seekho")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.seekho_logo)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat manager=NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());
    }
}
