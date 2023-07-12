package uz.gita.b5myeventapp.service

import uz.gita.b5myeventapp.MyReceiver
import android.app.*
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import uz.gita.b5myeventapp.R


class EventService : Service() {
    private val receiver = MyReceiver()
    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        val CHANNEL_ID = "MY_CHANNEL"
        val STOP_SERVICE = "SERVICE"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        Log.d("PPP", "onCreate: ")
    }

    private fun registerIntents() {
        registerReceiver(receiver, IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_SHUTDOWN)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_BATTERY_OKAY)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_DATE_CHANGED)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        })
    }

    private fun myStartService() {
        val stopIntent = Intent(this, EventService::class.java)
        stopIntent.putExtra(STOP_SERVICE, true)

        val stopPendingIntent = PendingIntent
            .getService(
                this,
                0,
                stopIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.bell)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentText("If you want stop events sounds, You will click cancel!")
            .addAction(R.drawable.bell, "Cancel", stopPendingIntent)
            .setAutoCancel(false)
            .setOngoing(true)

        startForeground(1, notification.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, "EVENT", NotificationManager.IMPORTANCE_DEFAULT)

            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val logic = intent?.extras?.getBoolean(STOP_SERVICE)
        Log.d("PPP", "$logic")

        if (logic == true) {
            unregisterReceiver(receiver)
            ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
        } else {
            myStartService()
            registerIntents()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}