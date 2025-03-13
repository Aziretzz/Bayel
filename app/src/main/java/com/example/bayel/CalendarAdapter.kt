package com.example.bayel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(
    private val daysOfMonth: ArrayList<String>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams

        // Постепенное вычисление высоты, если parent уже измерен
        layoutParams.height = (parent.resources.displayMetrics.heightPixels / 7)

        return CalendarViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val dayText = daysOfMonth[position]
        holder.dayOfMonth.text = dayText

        // Если день пустой (например, для предыдущего/следующего месяца), скрываем текст
        holder.dayOfMonth.visibility = if (dayText.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    override fun getItemCount(): Int = daysOfMonth.size

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String)
    }
}

class CalendarViewHolder(
    itemView: View,
    private val onItemListener: CalendarAdapter.OnItemListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val dayOfMonth: TextView = itemView.findViewById(R.id.txtDay)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val dayText = dayOfMonth.text.toString()
        if (dayText.isNotEmpty()) {
            onItemListener.onItemClick(adapterPosition, dayText)
        }
    }
}