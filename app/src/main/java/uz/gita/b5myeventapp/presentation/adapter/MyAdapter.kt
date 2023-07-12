package uz.gita.b5myeventapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.b5myeventapp.data.EventData
import uz.gita.b5myeventapp.databinding.ItemEventBinding


class MyAdapter : ListAdapter<EventData, MyAdapter.EventHolder>(EventDiffUtil) {

    private var clickListener: ((Boolean, EventData) -> Unit)? = null

    object EventDiffUtil : DiffUtil.ItemCallback<EventData>() {
        override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventData, newItem: EventData): Boolean {
            return oldItem == newItem
        }

    }

    inner class EventHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
                clickListener?.invoke(isChecked, getItem(adapterPosition))
            }

        }

        fun bind() {
            getItem(adapterPosition).apply {
                binding.name.text = this.name
                binding.switchBtn.isChecked = this.state
                binding.icon.setImageResource(this.icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder = EventHolder(
        ItemEventBinding.inflate(
            android.view.LayoutInflater.from(parent.context), parent, false
        )
    )


    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind()
    }

    fun setClickListener(block: (Boolean, EventData) -> Unit) {
        clickListener = block
    }

}