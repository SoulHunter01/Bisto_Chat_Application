package com.example.abdullahihsani181654__i180543;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class calling extends AppCompatActivity {
    ImageView image;
    TextView contact_name;
    ImageButton end_call;

    private static final String CHANNEL_ID = "CHANNEL_1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        Intent intent=getIntent();
        image=findViewById(R.id.myimageview);
        contact_name=findViewById(R.id.contact_name);
        end_call=findViewById(R.id.end_call);

        String get_status=intent.getStringExtra("STATUS_S");
        String get_name=intent.getStringExtra("NAME_S");
        String get_phoneNo=intent.getStringExtra("PHONE_NO");

        contact_name.setText(get_name);

        end_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(calling.this,SpecificChat.class);
                startActivity(intent);
            }
        });


        Intent newintent=new Intent(this,LoginPage.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,newintent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setContentTitle(contact_name.getText().toString())
                .setContentText("Calling "+contact_name.getText().toString()+" In Background")
                .setSmallIcon(R.drawable.gallery_icon);

        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"This Is My First Notification",NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(0,builder.build());



    }
}