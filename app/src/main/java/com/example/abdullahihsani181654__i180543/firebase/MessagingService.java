package com.example.abdullahihsani181654__i180543.firebase;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Toast.makeText(MessagingService.this,"Token"+token,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Toast.makeText(MessagingService.this,"Message"+remoteMessage.getNotification().getBody(),Toast.LENGTH_LONG).show();

    }
}
