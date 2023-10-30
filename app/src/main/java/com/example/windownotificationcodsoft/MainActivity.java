package com.example.windownotificationcodsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Message Channel";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQ_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bNotify = findViewById(R.id.btn);

        bNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Message Channel", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }

                Intent iNotify = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, REQ_CODE, iNotify, PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setContentTitle("Notification Alert!!!");
                builder.setContentText("Welcome to CodSoft App Development Internship");
                builder.setSmallIcon(R.drawable.baseline_circle_notifications_24);
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setContentIntent(pi);


                NotificationManagerCompat nmCompat = NotificationManagerCompat.from(MainActivity.this);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.VIBRATE}, REQ_CODE);
                }

                nmCompat.notify(NOTIFICATION_ID, builder.build());
            }
        });
    }
}