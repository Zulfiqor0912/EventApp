package uz.gita.b5myeventapp

import android.content.Context
import android.content.SharedPreferences

class LocalStorage(context: Context) {

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        preferences = context.getSharedPreferences("EXAM3", Context.MODE_PRIVATE)
        editor = preferences?.edit()
    }


    companion object {
        private var localStorage: LocalStorage? = null

        fun getInstance(): LocalStorage? {
            return localStorage
        }

        fun init(context: Context) {
            if (localStorage == null) localStorage = LocalStorage(context)
        }

    }

    fun saveFirstLogic(str: String, b: Boolean) {
        editor?.putBoolean(str, b)?.apply()
    }

    fun getFirstLogic(str: String): Boolean? {
        return preferences?.getBoolean(str, false)
    }
}