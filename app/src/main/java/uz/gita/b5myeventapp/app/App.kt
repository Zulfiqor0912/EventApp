package uz.gita.b5myeventapp.app

import android.app.Application
import uz.gita.b5myeventapp.LocalStorage

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }

}