package uz.gita.b5myeventapp.presentation.ui.viewmodel.impl

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.lifecycle.ViewModel
import uz.gita.b5myeventapp.LocalStorage
import uz.gita.b5myeventapp.R
import uz.gita.b5myeventapp.data.EventData
import uz.gita.b5myeventapp.presentation.ui.viewmodel.MainViewModel

class MainViewModelImpl : MainViewModel, ViewModel() {
    private val localStorage = LocalStorage.getInstance()

    private val list = listOf(
        EventData(
            id = 1,
            name = "Airplane Mode",
            action = Intent.ACTION_AIRPLANE_MODE_CHANGED,
            icon = R.drawable.airplane,
            state = localStorage?.getFirstLogic("1")!!
        ),

        EventData(
            id = 2,
            name = "Screen On",
            action = Intent.ACTION_SCREEN_ON,
            icon = R.drawable.ic_screen_on,
            state = localStorage.getFirstLogic("2")!!
        ),

        EventData(
            id = 3,
            name = "Screen Off",
            action = Intent.ACTION_SCREEN_OFF,
            icon = R.drawable.ic_screen_off,
            state = localStorage.getFirstLogic("3")!!
        ),

        EventData(
            id = 4,
            name = "Bluetooth",
            action = BluetoothAdapter.ACTION_STATE_CHANGED,
            icon = R.drawable.bluetooth,
            state = localStorage.getFirstLogic("4")!!
        ),

        EventData(
            id = 5,
            name = "Battery Charging On",
            action = Intent.ACTION_POWER_CONNECTED,
            icon = R.drawable.chargingon,
            state = localStorage.getFirstLogic("5")!!
        ),

        EventData(
            id = 6,
            name = "Battery Charging Off",
            action = Intent.ACTION_POWER_DISCONNECTED,
            icon = R.drawable.ic_disconnected,
            state = localStorage.getFirstLogic("6")!!
        ),

        EventData(
            id = 7,
            name = "Shut Down",
            action = Intent.ACTION_SHUTDOWN,
            icon = R.drawable.shut_down,
            state = localStorage.getFirstLogic("7")!!
        ),

        EventData(
            id = 8,
            name = "Battery Full",
            action = Intent.ACTION_BATTERY_OKAY,
            icon = R.drawable.full,
            state = localStorage.getFirstLogic("8")!!
        ),

        EventData(
            id = 9,
            name = "Battery Low",
            action = Intent.ACTION_BATTERY_LOW,
            icon = R.drawable.off,
            state = localStorage.getFirstLogic("9")!!
        ),

        EventData(
            id = 10,
            name = "Time Changed",
            action = Intent.ACTION_TIME_CHANGED,
            icon = R.drawable.time_changed,
            state = localStorage.getFirstLogic("10")!!
        ),

        EventData(
            id = 11,
            name = "Time Zone Changed",
            action = Intent.ACTION_TIMEZONE_CHANGED,
            icon = R.drawable.time_zona,
            state = localStorage.getFirstLogic("11")!!
        ),

        EventData(
            id = 12,
            name = "Date Changed",
            action = Intent.ACTION_DATE_CHANGED,
            icon = R.drawable.data_changed,
            state = localStorage.getFirstLogic("12")!!
        )
    ).shuffled()

    override fun getEvents(): List<EventData> = list

    override fun enableEvent(id: Int) {
        list.filter {
            it.id == id
        }.map {
            it.state = true
        }
    }

    override fun disableEvent(id: Int) {
        list.filter {
            it.id == id
        }.map {
            it.state = false
        }
    }

}