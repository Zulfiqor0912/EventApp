package uz.gita.b5myeventapp.data

data class EventData(
    val id: Int,
    val name: String,
    val action: String,
    val icon: Int,
    var state: Boolean = false
)