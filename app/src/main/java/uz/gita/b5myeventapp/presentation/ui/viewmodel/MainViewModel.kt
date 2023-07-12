package uz.gita.b5myeventapp.presentation.ui.viewmodel

import uz.gita.b5myeventapp.data.EventData

interface MainViewModel {

    fun getEvents(): List<EventData>

    fun enableEvent(id: Int)
    fun disableEvent(id: Int)
}