package uz.gita.b5myeventapp

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import uz.gita.b5myeventapp.LocalStorage
import uz.gita.b5myeventapp.R

class MyReceiver : BroadcastReceiver() {
    private val localStorage = LocalStorage.getInstance()!!
    private lateinit var media1: MediaPlayer
    private lateinit var media2: MediaPlayer
    private lateinit var media3: MediaPlayer
    private lateinit var media4_1: MediaPlayer
    private lateinit var media4_2: MediaPlayer
    private lateinit var media5: MediaPlayer
    private lateinit var media6: MediaPlayer
    private lateinit var media7: MediaPlayer
    private lateinit var media8: MediaPlayer
    private lateinit var media9: MediaPlayer
    private lateinit var media10: MediaPlayer
    private lateinit var media11: MediaPlayer
    private lateinit var media12: MediaPlayer

    override fun onReceive(context: Context, intent: Intent) {
        media1  = MediaPlayer.create(context, R.raw.airplane)
        media2 = MediaPlayer.create(context, R.raw.screen_on)
        media3 = MediaPlayer.create(context, R.raw.screen_off)
        media4_1 = MediaPlayer.create(context, R.raw.bluetooth_on)
        media4_2 = MediaPlayer.create(context, R.raw.bluetooth_off)
        media5 = MediaPlayer.create(context, R.raw.charging_on)
        media6 = MediaPlayer.create(context, R.raw.charging_off)
        media7 = MediaPlayer.create(context, R.raw.shutdown)
        media8 = MediaPlayer.create(context, R.raw.battery_full)
        media9 = MediaPlayer.create(context, R.raw.battery_low)
        media10 = MediaPlayer.create(context, R.raw.time_changed)
        media11 = MediaPlayer.create(context, R.raw.time_zone)
        media12 = MediaPlayer.create(context, R.raw.data_changed)

        Log.d("TTT", "${intent.action}")

        when (intent.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {

                Log.d("TTT", "${localStorage.getFirstLogic("1")}")

                if (localStorage.getFirstLogic("1")!!) {
                    media1.start()
                }
            }
            Intent.ACTION_SCREEN_ON -> {
                if (localStorage.getFirstLogic("2")!!) {
                    media2.start()
                }
            }
            Intent.ACTION_SCREEN_OFF -> {
                if (localStorage.getFirstLogic("3")!!) {
                    media3.start()
                }
            }
            BluetoothAdapter.ACTION_STATE_CHANGED -> {
                when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {
                    BluetoothAdapter.STATE_ON -> {
                        if (localStorage.getFirstLogic("4")!!) {
                            media4_1.start()
                        }
                    }

                    BluetoothAdapter.STATE_OFF -> {
                        if (localStorage.getFirstLogic("4")!!) {
                            media4_2.start()
                        }
                    }
                }
            }
            Intent.ACTION_POWER_CONNECTED -> {
                if (localStorage.getFirstLogic("5")!!) {
                    media5.start()
                }
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                if (localStorage.getFirstLogic("6")!!) {
                    media6.start()
                }
            }
            Intent.ACTION_SHUTDOWN -> {
                if (localStorage.getFirstLogic("7")!!) {
                    media7.start()
                }
            }
            Intent.ACTION_BATTERY_OKAY -> {
                if (localStorage.getFirstLogic("8")!!) {
                    media8.start()
                }
            }
            Intent.ACTION_BATTERY_LOW -> {
                if (localStorage.getFirstLogic("9")!!) {
                    media9.start()
                }
            }
            Intent.ACTION_TIME_CHANGED -> {
                if (localStorage.getFirstLogic("10")!!) {
                    media10.start()
                }
            }
            Intent.ACTION_TIMEZONE_CHANGED -> {
                if (localStorage.getFirstLogic("11")!!) {
                    media11.start()
                }
            }
            Intent.ACTION_DATE_CHANGED -> {
                if (localStorage.getFirstLogic("12")!!) {
                    media12.start()
                }
            }
            else -> {

            }

        }
    }
}