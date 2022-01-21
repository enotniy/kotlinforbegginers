package ru.ekitselyuk.kotlinforbegginers.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.ekitselyuk.kotlinforbegginers.R

const val TAG = "MyFirebaseMessaging"
const val NOTIFICATION_ID = 42
const val CHANNEL_ID = "Default2"

class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "onMessageReceived $message")
        super.onMessageReceived(message)

        val data = message.data

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_history_24)
            .setContentTitle("Новое сообщение")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setStyle(NotificationCompat.BigTextStyle().bigText("BIG TEXT"))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Тут будут всякие сообщение"
                }
            )
            notificationBuilder.setChannelId(CHANNEL_ID)
        }

        notificationManager.notify(42, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "onNewToken $token")
        super.onNewToken(token)
    }
}