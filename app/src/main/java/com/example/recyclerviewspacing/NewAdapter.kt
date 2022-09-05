package com.example.recyclerviewspacing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewAdapter : RecyclerView.Adapter<NewAdapter.NewViewHolder>() {
    private var data: MutableList<Int> = IntArray(50).toMutableList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        return NewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_normal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return data.size
    }

    class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTextView: TextView = itemView.findViewById(R.id.tv)
    }
}