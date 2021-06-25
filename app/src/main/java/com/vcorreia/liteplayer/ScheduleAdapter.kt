package com.vcorreia.liteplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ScheduleAdapter(private val onClick: (ScheduleEvent) -> Unit) :
        ListAdapter<ScheduleEvent, ScheduleAdapter.ScheduleViewHolder>(ScheduleEventDiffCallback) {

    /* ViewHolder to be used, takes in the inflated view and the onClick behavior. */
    class ScheduleViewHolder(itemView: View, val onClick: (ScheduleEvent) -> Unit) :
            RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        private var currentScheduleEvent: ScheduleEvent? = null

        init {
            itemView.setOnClickListener {
                currentScheduleEvent?.let {
                    onClick(it)
                }
            }
        }

        fun bind(scheduleEvent: ScheduleEvent) {
            currentScheduleEvent = scheduleEvent

            titleTextView.text = scheduleEvent.title
        }
    }

    /* Creates and inflates view and return ViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.schedule_item, parent, false)
        return ScheduleViewHolder(view, onClick)
    }

    /* Gets current event and uses it to bind view. */
    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val scheduleEvent = getItem(position)
        holder.bind(scheduleEvent)

    }
}

object ScheduleEventDiffCallback : DiffUtil.ItemCallback<ScheduleEvent>() {
    override fun areItemsTheSame(oldItem: ScheduleEvent, newItem: ScheduleEvent): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ScheduleEvent, newItem: ScheduleEvent): Boolean {
        return oldItem.title == newItem.title
    }
}
